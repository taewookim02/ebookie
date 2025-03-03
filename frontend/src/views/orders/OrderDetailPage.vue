<script setup>
import PortOne from '@portone/browser-sdk/v2';
import OrderPricesSection from '@/components/domain/order/OrderPricesSection.vue';
import ActionButton from '@/components/common/ActionButton.vue';
import { formatDiscountAmount, formatSellingPrice } from '@/helper/format';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import OrderPaymentSection from '@/components/domain/order/OrderPaymentSection.vue';
import { useToast } from 'vue-toastification';
import { useMemberStore } from '@/store/memberStore';
import { PhExclamationMark } from '@phosphor-icons/vue';

const route = useRoute();
const router = useRouter()
const toast = useToast();
const memberStore = useMemberStore();

// state
const orderId = route.params.id; // url에서 주문번호 구하기
const dtoList = ref([]);
const pgMethod = ref("kakao");
const paymentStatus = ref({ status: "PENDING" });
const orderStatus = ref("PENDING");
const isLoading = ref(true);
const hasError = ref(false);

// computed
const totalOriginalPrice = computed(() => {
    return dtoList.value.reduce((sum, dto) => sum + dto.originalPrice, 0);
});

const totalDiscountAmount = computed(() => {
    return dtoList.value.reduce((sum, dto) => {
        const discountAmount = dto.originalPrice * dto.discountRatePercentage / 100;
        return sum + discountAmount
    }, 0);
})

const totalFinalPrice = computed(() => {
    return dtoList.value.reduce((sum, dto) => {
        const discountedPrice = dto.originalPrice - (dto.originalPrice * dto.discountRatePercentage / 100);
        return sum + discountedPrice;
    }, 0);
})

const channelKey = computed(() => {
    switch (pgMethod.value) {
        case "kakao":
            // return import.meta.env.VITE_PORTONE_CHANNEL_KEY_KAKAO;
            return "channel-key-bdb9666b-9eba-4cc3-bc3e-cca13a1ff2f9";
        case "toss":
            return "channel-key-4ab490c4-6acf-48d8-9439-c326b47656e3";
        case "kg":
            return "channel-key-0325f0f6-2588-4f62-993d-81e7ec9a6171";
        default:
            return import.meta.env.VITE_PORTONE_CHANNEL_KEY_KAKAO;
    }
})

// 주문번호 생성
const randomId = () => {
    return [...crypto.getRandomValues(new Uint32Array(2))]
    .map((word) => word.toString(16).padStart(8, "0"))
    .join("");
}

// actions
const handlePayment = async () => {
    if (!confirm("구매 하시겠습니까?") || totalFinalPrice.value <= 0) {
        return;
    }

    try {
        const paymentId = randomId();
        
        if (!orderId) {
            toast.error("주문 정보가 올바르지 않습니다.");
            return;
        }

        const res = await customAxios.post("/api/v1/payments", {
            paymentId,
            orderId,
            paidPrice: totalFinalPrice.value,
            paymentStatus: "PENDING"
        });

        if (!res.data) {
            toast.error("결제 생성에 실패했습니다. 다시 시도해주세요.");
            return;
        }

        paymentStatus.value = ({ status: "PENDING" });

        const payment = await PortOne.requestPayment({
            storeId: import.meta.env.VITE_PORTONE_STORE_ID, // .env 변수
            channelKey: channelKey.value,
            paymentId: res.data.paymentId,
            orderName: "ebookie 상품구매",
            totalAmount: totalFinalPrice.value,
            currency: "KRW",
            payMethod: "EASY_PAY", // 간편결제
            productType: "PRODUCT_TYPE_DIGITAL",
            isCulturalExpense: true,
            customer: {
                email: memberStore.getMemberEmail, 
            },
            customData: {
                item: res.data.paymentId
            },
        });

        // 결제 실패 
        if (payment.code !== undefined || !payment.paymentId) {
            paymentStatus.value = { status: "FAILED" };
            toast.error(`결제에 실패했습니다: ${payment.message || '알 수 없는 오류'}`);
            return;
        }

        // 서버에서 결제내역 확인 요청청
        const completeRes = await customAxios.post("/api/v1/payments/complete", {
            paymentId: payment.paymentId
        });
        
        if (!completeRes.data.orderStatus) {
            throw new Error("Server response missing orderStatus");
        }
        
        // 결제 상태 업데이트
        paymentStatus.value.status = completeRes.data.paymentStatus;
        orderStatus.value = completeRes.data.orderStatus;
        
        // 유저 피드백
        switch (paymentStatus.value.status) {
            case "PAID":
                toast.success("결제가 완료되었습니다!");
                router.push("/library"); 
                break;
            case "FAILED":
                toast.error("결제 처리 중 오류가 발생했습니다.");
                break;
            case "CANCELLED":
                toast.info("결제가 취소되었습니다.");
                break;
            default:
                toast.warning("알 수 없는 결제 상태입니다.");
        }
    } catch (error) {
        console.error("Payment creation error:", error);
        paymentStatus.value = { status: "FAILED" }; 
        
        if (error.response?.status === 409) {
            toast.error("이미 처리 중인 결제가 있습니다. 잠시 후 다시 시도해주세요.");
        } else {
            toast.error("결제 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
        return;
    }
}

const fetchOrderDetail = async (orderId) => {
    try {
        isLoading.value = true;
        hasError.value = false;
        
        const res = await customAxios.get(`/api/v1/orders/${orderId}`);
        
        if (!res.data) {
            throw new Error("주문을 찾을 수 없습니다.");
        }

        if (!res.data.orderStatus) {
            throw new Error("Server response missing orderStatus");
        }

        dtoList.value = res.data.orderDetailDtos;
        orderStatus.value = res.data.orderStatus;

    } catch (error) {
        console.error("Order detail fetch error:", error);
        hasError.value = true;
        
        if (error.response?.status === 403) {
            toast.error("접근 권한이 없는 주문입니다.");
            router.push('/orders');
        } else if (error.response?.status === 404) {
            toast.error("존재하지 않는 주문입니다.");
            router.push('/orders');
        } else {
            toast.error("주문 정보 로드에 실패했습니다!");
        }
    } finally {
        isLoading.value = false;
    }
}

// Lifecycle hooks
onMounted(() => {
    fetchOrderDetail(orderId);
})
</script>

<template>
    <div v-if="isLoading" class="loading">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>
    <div v-else-if="hasError" class="empty-state">
        <PhExclamationMark :size="40" weight="fill" color="var(--muted-color)" />
        <h3 class="mt-4">주문 정보를 불러올 수 없습니다</h3>
        <p class="text-muted">잠시 후 다시 시도해주세요.</p>
    </div>
    <div v-else-if="!dtoList.length" class="empty-state">
        <h3 class="mt-4">주문 내역이 없습니다</h3>
        <p class="text-muted">주문하신 상품이 없습니다.</p>
        <ActionButton class="mt-3" @action="router.push('/products')">상품 보러가기</ActionButton>
    </div>
    <div v-else class="order">
        <section class="order__info">
            <h3>주문/결제</h3>
            <div class="table-responsive">
                <table class="table order__info--table">
                    <colgroup class="d-none d-md-table-column-group">
                        <col width="10%">
                        <col width="45%">
                        <col width="10%">
                        <col width="5%">
                        <col width="10%">
                        <col width="10%">
                        <col width="10%">
                    </colgroup>
                    <thead class="text-center">
                        <tr>
                            <th colspan="2">상품명</th>
                            <th class="d-none d-md-table-cell">정가</th>
                            <th class="d-none d-md-table-cell">수량</th>
                            <th class="d-none d-md-table-cell">할인금액</th>
                            <th class="d-none d-md-table-cell">합계</th>
                            <th class="d-none d-md-table-cell">배송일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="dto in dtoList">
                            <td>
                                <img :src="getImageFromServer(dto.thumbnail)" alt="상품 이미지" class="product-image">
                            </td>
                            <td>
                                <div class="product-info">
                                    <span class="product-name">{{ dto.name }}</span>
                                    <div class="d-md-none mobile-details">
                                        <div>정가: {{ dto.originalPrice.toLocaleString() }}원</div>
                                        <div>수량: {{ dto.quantity }}</div>
                                        <div>할인: {{ formatDiscountAmount(dto.originalPrice, dto.discountRatePercentage) }}원</div>
                                        <div>합계: {{ formatSellingPrice(dto.originalPrice, dto.discountRatePercentage) }}원</div>
                                        <div>배송: 무배송 (결제 후 다운로드)</div>
                                    </div>
                                </div>
                            </td>
                            <td class="text-center align-middle d-none d-md-table-cell">
                                {{ dto.originalPrice.toLocaleString() }}원
                            </td>
                            <td class="text-center align-middle d-none d-md-table-cell">
                                {{ dto.quantity }}
                            </td>
                            <td class="text-center align-middle d-none d-md-table-cell">
                                {{ formatDiscountAmount(dto.originalPrice, dto.discountRatePercentage) }}원
                            </td>
                            <td class="text-center align-middle d-none d-md-table-cell">
                                {{ formatSellingPrice(dto.originalPrice, dto.discountRatePercentage) }}원
                            </td>
                            <td class="text-center align-middle d-none d-md-table-cell">
                                무배송 (결제 후 다운로드)
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>

        <OrderPricesSection :total-original-price="totalOriginalPrice" :total-discount-amount="totalDiscountAmount"
            :total-final-price="totalFinalPrice" />

        <!-- Show payment section only if order is pending -->
        <template v-if="orderStatus === 'PENDING'">
            <OrderPaymentSection :total-final-price="totalFinalPrice" v-model:pg-method="pgMethod" />
            <div class="payment__action">
                <ActionButton class="w-100 payment__action--button" @action="handlePayment">결제하기</ActionButton>
            </div>
        </template>

        <!-- Show status message for other states -->
        <template v-else>
            <section class="order__status">
                <h3>주문 상태</h3>
                <div class="status-message" :class="orderStatus.toLowerCase()">
                    <p v-if="orderStatus === 'PAID'">결제가 완료된 주문입니다.</p>
                    <p v-else-if="orderStatus === 'CANCELLED'">취소된 주문입니다.</p>
                    <p v-else-if="orderStatus === 'REFUNDED'">환불된 주문입니다.</p>
                    <p v-else>알 수 없는 주문 상태입니다.</p>
                </div>
            </section>
        </template>
    </div>
</template>

<style scoped>
.order {
    display: flex;
    flex-direction: column;
    gap: 3.2rem;
    color: var(--text-color);
    padding: 1rem;
}

.order__info--table {
    word-break: keep-all;
    border-color: var(--border-color);
    min-width: 280px;
}

.order__info--table thead {
    background-color: var(--surface-color);
}

.order__info--table td {
    vertical-align: middle;
    border-color: var(--border-color);
}

.product-image {
    max-width: 80px;
    height: auto;
}

.product-info {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
}

.product-name {
    font-weight: 500;
    font-size: 1.4rem;
}

.mobile-details {
    font-size: 1.4rem;
    color: var(--text-secondary-color);
}

.payment__action {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 1rem;
    background: var(--background-color);
    box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
    z-index: 100;
}

.payment__action--button {
    max-width: 100%;
    background-color: var(--primary-color);
    color: var(--background-color);
    transition: background-color 0.2s ease;
}

.payment__action--button:hover {
    background-color: var(--primary-hover);
}

.order__status {
    text-align: center;
    padding: 1rem;
    background-color: var(--surface-color);
    border-radius: 0.5rem;
}

.status-message {
    margin-top: 1rem;
    padding: 1rem;
    border-radius: 0.5rem;
    font-weight: 500;
}

.status-message.paid {
    background-color: var(--accent-color);
    color: var(--background-color);
}

.status-message.cancelled {
    background-color: #ff4444;
    color: var(--background-color);
}

.status-message.refunded {
    background-color: var(--text-secondary-color);
    color: var(--background-color);
}

.loading {
    text-align: center;
    padding: 2rem;
    color: var(--primary-color);
}

.empty-state {
    text-align: center;
    padding: 2rem;
    color: var(--text-secondary-color);
    background-color: var(--surface-color);
    border-radius: 0.5rem;
    margin: 1rem 0;
}

.empty-state h3 {
    color: var(--text-color);
    margin-bottom: 1rem;
}

.empty-state p {
    color: var(--text-secondary-color);
}

@media (min-width: 768px) {
    .order {
        gap: 6.4rem;
        padding: 2rem;
    }

    .product-image {
        max-width: 120px;
    }

    .product-name {
        font-size: 1.4rem;
    }

    .payment__action {
        position: static;
        padding: 0;
        box-shadow: none;
    }

    .order__status {
        padding: 2rem;
    }

    .status-message {
        padding: 1.5rem;
    }

    .loading {
        padding: 4rem;
    }
    
    .empty-state {
        padding: 4rem;
        margin: 2rem 0;
    }
}

</style>