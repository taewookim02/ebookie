<script setup>
import OrderPricesSection from '@/components/sections/order/OrderPricesSection.vue';
import ActionButton from '@/components/shared/ActionButton.vue';
import { formatDiscountAmount, formatSellingPrice } from '@/helper/format';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';
import OrderPaymentSection from '@/components/sections/order/OrderPaymentSection.vue';
const route = useRoute();

// get orderid and fetch
const orderId = route.params.id;
const dtoList = ref([]);
const pgMethod = ref("toss");

// initial fetch
const fetchOrderDetail = async (orderId) => {
    const res = await customAxios.get(`/api/v1/orders/${orderId}`);
    dtoList.value = res.data.orderDetailDtos;
    console.log(dtoList);
}
fetchOrderDetail(orderId);


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

// actions
const handlePayment = () => {
    console.log("pgMethod:", pgMethod.value);
    console.log("totalFinalPrice:", totalFinalPrice.value);
}
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

        <OrderPaymentSection :total-final-price="totalFinalPrice" v-model:pg-method="pgMethod"  />

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