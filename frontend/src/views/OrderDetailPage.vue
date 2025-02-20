<script setup>
import PortOne from '@portone/browser-sdk/v2';
import OrderPricesSection from '@/components/sections/order/OrderPricesSection.vue';
import ActionButton from '@/components/shared/ActionButton.vue';
import { formatDiscountAmount, formatSellingPrice } from '@/helper/format';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import OrderPaymentSection from '@/components/sections/order/OrderPaymentSection.vue';
import { useToast } from 'vue-toastification';
import { useMemberStore } from '@/store/memberStore';

const route = useRoute();
const router = useRouter()
const toast = useToast();
const memberStore = useMemberStore();

// get orderid and fetch
const orderId = route.params.id;
const dtoList = ref([]);
const pgMethod = ref("toss");
const paymentStatus = ref({ status: "PENDING" });

// initial fetch
const fetchOrderDetail = async (orderId) => {
    try {
        const res = await customAxios.get(`/api/v1/orders/${orderId}`);
        dtoList.value = res.data.orderDetailDtos;
    } catch (error) {
        toast.error("로드 실패!");
    }
}


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
        // case "toss":
        //     return import.meta.env.VITE_PORTONE_CHANNEL_KEY_TOSS;
        case "kakao":
            return import.meta.env.VITE_PORTONE_CHANNEL_KEY_KAKAO;
        // case "payco":
        //     return import.meta.env.VITE_PORTONE_CHANNEL_KEY_PAYCO;
        default:
            return import.meta.env.VITE_PORTONE_CHANNEL_KEY_KAKAO;
    }
})

const randomId = () => {
    return [...crypto.getRandomValues(new Uint32Array(2))]
    .map((word) => word.toString(16).padStart(8, "0"))
    .join("");
}
// console.log(memberStore.get);
// actions
const handlePayment = async () => {
    if (!confirm("구매 하시겠습니까?") || totalFinalPrice.value <= 0) {
        return;
    }

    try {
        const paymentId = randomId();
        
        // 서버에 결제 테이블 insert
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

        console.log(import.meta.env.VITE_PORTONE_STORE_ID);
        console.log(channelKey.value);

        // Continue with PortOne payment flow
        paymentStatus.value = ({ status: "PENDING" });
        const payment = await PortOne.requestPayment({
            storeId: import.meta.env.VITE_PORTONE_STORE_ID,
            channelKey: channelKey.value,
            paymentId,
            orderName: "ebookie 상품구매", // TODO: order name
            totalAmount: totalFinalPrice.value,
            currency: "KRW",
            payMethod: "EASY_PAY",
            // redirectUrl: `/payments/${paymentId}`,
            productType: "PRODUCT_TYPE_DIGITAL",
            // products: [{id, name, code, amount, quantity, tag}],
            isCulturalExpense: true,
            // noticeUrls: ["https://abe1-112-186-26-198.ngrok-free.app/api/v1/payments/webhook"],
            customer: {
                email: memberStore.getMemberEmail
            },
            customData: {
                item: paymentId
            },
        });

        if (payment.code !== undefined) {
            paymentStatus.value = { status: "FAILED" };
            toast.error("결제에 실패했습니다. 다시 시도해주세요.");
            return;
        }

        const completeRes = await customAxios.post("/api/v1/payments/complete", {
            paymentId: payment.paymentId
        });
        paymentStatus.value.status = completeRes.data.paymentStatus;
        
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
        
        if (error.response?.status === 409) {
            toast.error("이미 처리 중인 결제가 있습니다. 잠시 후 다시 시도해주세요.");
        } else {
            toast.error("결제 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
        return;
    }
}

// lifecycle hooks
onMounted(() => {
    fetchOrderDetail(orderId);

})
</script>

<template>
    <div class="order">
        <section class="order__info">
            <h3>주문/결제</h3>
            <table class="table order__info--table">
                <colgroup>
                    <col width="10%" />
                    <col width="45%" />
                    <col width="10%" />
                    <col width="5%" />
                    <col width="10%" />
                    <col width="10%" />
                    <col width="10%" />
                </colgroup>
                <thead class="text-center">
                    <tr>
                        <th colspan="2">상품명</th>
                        <th>정가</th>
                        <th>수량</th>
                        <th>할인금액</th>
                        <th>합계</th>
                        <th>배송일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="dto in dtoList">
                        <td>
                            <img :src="getImageFromServer(dto.thumbnail)" alt="상품 이미지">
                        </td>
                        <td>{{ dto.name }}</td>
                        <td class="text-center align-middle">
                            {{ dto.originalPrice.toLocaleString() }}원
                        </td>
                        <td class="text-center align-middle">
                            {{ dto.quantity }}
                        </td>
                        <td class="text-center align-middle">
                            {{ formatDiscountAmount(dto.originalPrice, dto.discountRatePercentage) }}원
                        </td>
                        <td class="text-center align-middle">
                            {{ formatSellingPrice(dto.originalPrice, dto.discountRatePercentage) }}원
                        </td>
                        <td class="text-center align-middle">
                            무배송 (결제 후 다운로드)
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>

        <OrderPricesSection :total-original-price="totalOriginalPrice" :total-discount-amount="totalDiscountAmount"
            :total-final-price="totalFinalPrice" />

        <OrderPaymentSection :total-final-price="totalFinalPrice" v-model:pg-method="pgMethod" />

        <div class="payment__action">
            <ActionButton class="w-100 payment__action--button" @action="handlePayment">결제하기</ActionButton>
        </div>
    </div>
</template>

<style scoped>
.order {
    display: flex;
    flex-direction: column;
    gap: 6.4rem;
}

.order__info--table {
    word-break: keep-all;
}

.payment__action--button {
    max-width: 100%;
}
</style>