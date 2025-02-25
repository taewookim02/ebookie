<script setup>
import DeliveryAndRefundSection from '@/components/domain/product/detail/DeliveryAndRefundSection.vue';
import OrderPricesSection from '@/components/domain/order/OrderPricesSection.vue';
import ActionButton from '@/components/common/ActionButton.vue';
import { formatSellingPrice } from '@/helper/format';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { computed, ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useToast } from 'vue-toastification';

// state
const cartDto = ref([]);
const toast = useToast();
const checkedItems = ref(new Set());
const allChecked = ref(true);
const router = useRouter();

// computed
const totalOriginalPrice = computed(() => {
    return cartDto.value.filter(item => checkedItems.value.has(item.productId)).reduce((sum, item) => sum + item.originalPrice, 0);
});

const totalDiscountAmount = computed(() => {
    return cartDto.value.filter(item => checkedItems.value.has(item.productId)).reduce((sum, item) => {
        const discountAmount = item.originalPrice * item.discountRatePercentage / 100;
        return sum + discountAmount;
    }, 0);
})

const totalFinalPrice = computed(() => {
    return cartDto.value.filter(item => checkedItems.value.has(item.productId)).reduce((sum, item) => {
        const discountedPrice = item.originalPrice - (item.originalPrice * item.discountRatePercentage / 100)
        return sum + discountedPrice
    }, 0);
});

const isEmpty = computed(() => cartDto.value.length === 0);

// actions
const fetchCartProducts = async () => {
    try {
        const res = await customAxios.get(`/api/v1/cart`);
        cartDto.value = res.data;
        // Check all items by default
        checkedItems.value = new Set(cartDto.value.map(dto => dto.productId));
    } catch (err) {
        console.log("fetchCartProducts() err:", err);
    }
}
fetchCartProducts(); // get list on setup

const handleDeleteClick = async (productId) => {
    try {
        const res = await customAxios.delete(`/api/v1/cart/${productId}`);

        // UI상에서 productId 같은 것 없애기
        cartDto.value = cartDto.value.filter(dto => dto.productId !== productId);
        checkedItems.value.delete(productId);
        toast.info("장바구니 목록에서 삭제 완료!");
    } catch (err) {
        console.log("handleDeleteClick() err:", err);
        toast.error("삭제 중 에러 발생!")
    }
}

const toggleCheckAll = () => {
    if (allChecked.value) {
        checkedItems.value = new Set(cartDto.value.map(dto => dto.productId));
    } else {
        checkedItems.value.clear();
    }
}

const toggleCheck = (productId) => {
    if (checkedItems.value.has(productId)) {
        checkedItems.value.delete(productId);
        allChecked.value = false;
    } else {
        checkedItems.value.add(productId);
        allChecked.value = checkedItems.value.size === cartDto.value.length;
    }
}

const handleCheckedDelete = async () => {
    // 체크박스 체크 된 아이템들의 productId 구하기
    // 서버에 productId 리스트를 보내기
    // 서버에서 productId 일치하는 아이템들 삭제
    if (checkedItems.value.size === 0) {
        toast.warning("삭제할 상품을 선택해주세요");
        return;
    }
    try {
        const productIds = Array.from(checkedItems.value);

        await customAxios.delete(`/api/v1/cart/bulk`, {
            data: { productIds }
        });

        cartDto.value = cartDto.value.filter(
            dto => !checkedItems.value.has(dto.productId)
        );

        // hmm?
        checkedItems.value.clear();
        allChecked.value = false;
        toast.info(`${productIds.length}개 상품이 장바구니 목록에서 삭제되었습니다`);
    } catch (err) {
        console.log("handleCheckedDelete() err:", err);
        toast.error("선택한 상품 삭제 중 에러가 발생했습니다!");
    }
};

const handleCheckedOrders = async () => {
    try {
        // create order entity with the ids
        const productIds = Array.from(checkedItems.value);
        const res = await customAxios.post(`/api/v1/orders`, { productIds });

        // get back the order id
        const orderId = res.data.id;
        

        toast.success("주문 생성 성공!");

        // router push to orders?orderId=${orderId}
        router.push(`/orders/${orderId}`);
    } catch (error) {
        toast.error("주문 생성 실패!");
    }
}

const handleOrderClick = async (productId) => {
    console.log("구매하기", productId);
    try {
        const res = await customAxios.post(`/api/v1/orders`, { productIds: [productId] });

        const orderId = res.data.id;

        toast.success("주문 생성 성공!");

        router.push(`/orders/${orderId}`);
    } catch (error) {
        toast.error("주문 생성 실패!");
    }
    // router.push(`/orders?new=${productId}`)
}
</script>


<template>
    <div class="cart-container">
        <section class="cart">
            <h1 class="mb-4">장바구니</h1>
            <div class="cart__control">
                <div>
                    <input type="checkbox" name="all" id="all" v-model="allChecked" @change="toggleCheckAll">
                </div>
                <div colspan="2">
                    <label for="all">전체선택</label>
                    <ActionButton @action="handleCheckedDelete" :disabled="checkedItems.size === 0" class="btn-select">
                        선택삭제
                    </ActionButton>
                    <ActionButton @action="handleCheckedOrders" :disabled="checkedItems.size === 0" class="btn-select">
                        선택주문
                    </ActionButton>
                </div>
            </div>

            <table class="table cart__table">
                <colgroup>
                    <col width="5%" />
                    <col width="10%" />
                    <col width="50%" />
                    <col width="5%" />
                    <col width="10%" />
                    <col width="20%" />
                </colgroup>
                <thead>
                    <tr v-if="cartDto && cartDto.length > 0" class="text-center">
                        <th></th>
                        <th colspan="2">상품정보</th>
                        <th>수량</th>
                        <th>상품금액</th>
                        <th>배송정보</th>
                        <th>주문</th>
                    </tr>
                </thead>
                <tbody>
                    <template v-if="cartDto && cartDto.length > 0">
                        <tr v-for="dto in cartDto">
                            <td class="text-center">
                                <input type="checkbox" :checked="checkedItems.has(dto.productId)"
                                    @change="toggleCheck(dto.productId)">
                            </td>
                            <td>
                                <RouterLink :to="`/products/${dto.productId}`">
                                    <img class="img-product" :src="getImageFromServer(dto.thumbnail)" alt="상품 이미지">
                                </RouterLink>
                            </td>
                            <td>
                                <RouterLink :to="`/products/${dto.productId}`" class="link-dark">{{ dto.name }}
                                </RouterLink>
                                <div class="text-muted prices">
                                    <small class="text-cross mr-2">{{ dto.originalPrice.toLocaleString() }}</small>
                                    <span>{{ formatSellingPrice(dto.originalPrice, dto.discountRatePercentage)
                                    }}원</span>
                                </div>
                            </td>
                            <td>
                                <!-- TODO: quantity -->
                                1
                            </td>
                            <td class="fw-bold">
                                {{ formatSellingPrice(dto.originalPrice, dto.discountRatePercentage) }}원
                            </td>
                            <td>구매 후 바로 다운로드</td>
                            <td class="td-action">
                                <ActionButton class="w-100" @action="handleDeleteClick(dto.productId)">삭제</ActionButton>
                                <ActionButton class="w-100" @action="handleOrderClick(dto.productId)">주문하기
                                </ActionButton>
                            </td>
                        </tr>
                    </template>
                    <template v-else>
                        <tr>
                            <td colspan="6" class="td-empty text-muted"><small>장바구니가 비었습니다.</small></td>
                        </tr>
                    </template>
                </tbody>
                <tfoot v-if="cartDto && cartDto.length > 0">
                    <tr>
                        <td colspan="7" class="text-end fw-bold">합계: {{ totalFinalPrice.toLocaleString() }}원</td>
                    </tr>
                </tfoot>
            </table>

        </section>

        <template v-if="!isEmpty">
            <OrderPricesSection :total-original-price="totalOriginalPrice" :total-discount-amount="totalDiscountAmount"
                :total-final-price="totalFinalPrice" />


            <section class="orders__action">
                <ActionButton @action="handleCheckedOrders" :disabled="checkedItems.size === 0">주문하기</ActionButton>
                <RouterLink to="/">
                    <ActionButton>쇼핑 계속하기</ActionButton>
                </RouterLink>
            </section>
        </template>


        <DeliveryAndRefundSection />
    </div>
</template>



<style scoped>
.cart-container {
    display: flex;
    flex-direction: column;
    gap: 3.2rem;
}


.cart__control {
    display: flex;
    align-items: center;
    gap: .8rem;
}

/* TABLE */

.cart td {
    vertical-align: middle;
}


.img-product {
    width: 10.4rem;
    height: 10.4rem;
    object-fit: contain;

}

.td-action {
    vertical-align: middle;
}

.td-empty {
    text-align: center;
    padding: 6.4rem;
}

.btn-select {
    margin-left: 1.6rem;
}

.text-cross {
    text-decoration: line-through;
}

.prices {
    display: flex;
    align-items: end;
    gap: .8rem;
}

.orders__action {
    padding: 3.2rem;
    display: flex;
    gap: 1.6rem;
    justify-content: center;
}
</style>