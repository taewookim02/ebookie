<script setup>
import { ref } from 'vue';
import { customAxios } from '@/plugins/axios';
import Pagination from '@/components/common/Pagination.vue';

// state
const orderPageDto = ref({
    orders: [],
    totalPages: 0,
    totalElements: 0,
    currentPage: 0
});
const currentPage = ref(0);
const pageSize = ref(10);
const pageSizeOptions = [5, 10, 20, 50];

// actions
const fetchOrders = async (page = 0) => {
    try {
        const res = await customAxios.get(`/api/v1/orders?page=${page}&size=${pageSize.value}`);
        orderPageDto.value = res.data;
        currentPage.value = res.data.currentPage;
    } catch (err) {
        console.log("fetchOrders() err:", err);
    }
}

const handlePageChange = (page) => {
    fetchOrders(page - 1);
}

const handlePageSizeChange = () => {
    currentPage.value = 0;
    fetchOrders(0);
}

const orderStatusToKorean = status => {
    switch (status) {
        case "PENDING":
            return "결제대기";
        case "PAID":
            return "결제완료";
        case "CANCELLED":
            return "주문취소";
        case "REFUNDED":
            return "환불완료";
        default:
            return "";
    }
}

const getStatusColor = status => {
    switch (status) {
        case "PENDING":
            return "#ff4444"; // red
        case "PAID":
            return "#00C851"; // green
        case "CANCELLED":
            return "#ff8800"; // orange
        case "REFUNDED":
            return "#666666"; // gray
        default:
            return "#000000";
    }
}

fetchOrders();

</script>

<template>
    <div>
        <h1 class="text-center">주문 목록</h1>
        <div class="page-controls d-flex justify-content-end">
            <select v-model="pageSize" @change="handlePageSizeChange">
                <option v-for="size in pageSizeOptions" :key="size" :value="size">
                    {{ size }}개씩 보기
                </option>
            </select>
        </div>
        <div v-if="orderPageDto.orders.length === 0" class="order-empty text-muted">
            주문 내역이 없습니다.
        </div>
        <div v-else>
            <div v-for="order in orderPageDto.orders" :key="order.id" class="order-item">
                <RouterLink :to="`/orders/${order.id}`">
                    <div class="order-header">
                        <span>주문번호: {{ order.id }}</span>
                        <span>주문일: {{ new Date(order.createdAt).toLocaleDateString() }}</span>
                    </div>
                    <div class="order-content">
                        <span>총 금액: {{ order.totalPrice.toLocaleString() }}원</span>
                        <span class="order-status" :style="{ color: getStatusColor(order.orderStatus) }">
                            상태: {{ orderStatusToKorean(order.orderStatus) }}
                        </span>
                    </div>
                    <div class="order-products">
                        <div v-for="product in order.products" :key="product.productId">
                            {{ product.productName }}
                        </div>
                    </div>
                </RouterLink>
            </div>

            <Pagination
                v-if="orderPageDto.totalPages > 1"
                :current-page="currentPage + 1"
                :total-pages="orderPageDto.totalPages"
                @page-change="handlePageChange"
            />
        </div>
    </div>
</template>

<style scoped>
.page-controls {
    margin-bottom: 1rem;
}

.page-controls select {
    padding: 0.5rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    background: white;
}

.order-empty {
    text-align: center;
    padding: 3.2rem;
    font-size: 1.4rem;
}

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
    margin-bottom: 10px;
}

.order-status {
    font-weight: bold;
}

.order-products {
    font-size: 0.9em;
    color: #666;
}
</style>
