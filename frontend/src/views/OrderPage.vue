<script setup>
import { ref } from 'vue';
import { customAxios } from '@/plugins/axios';

// state
const ordersDto = ref([]);

// actions
const fetchOrders = async () => {
    try {
        const res = await customAxios.get(`/api/v1/orders`);
        ordersDto.value = res.data;
    } catch (err) {
        console.log("fetchOrders() err:", err);
    }
}
fetchOrders();

</script>

<template>
    <div>
        <h1>Orders</h1>
        <div v-if="ordersDto.length === 0">
            주문 내역이 없습니다.
        </div>
        <div v-else>
            <div v-for="order in ordersDto" :key="order.id" class="order-item">
                <RouterLink :to="`/orders/${order.id}`">
                    <div class="order-header">
                        <span>주문번호: {{ order.id }}</span>
                        <span>주문일: {{ new Date(order.createdAt).toLocaleDateString() }}</span>
                    </div>
                    <div class="order-content">
                        <span>총 금액: {{ order.totalPrice.toLocaleString() }}원</span>
                        <span>상태: {{ order.status }}</span>
                    </div>
                </RouterLink>
            </div>
        </div>
    </div>
</template>

<style scoped>
.order-item {
    border: 1px solid #ddd;
    margin: 10px 0;
    padding: 15px;
    border-radius: 5px;
}

.order-item a {
    text-decoration: none;
    color: inherit;
}

.order-header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.order-content {
    display: flex;
    justify-content: space-between;
}
</style>
