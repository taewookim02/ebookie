<script setup>
import { customAxios } from '@/plugins/axios';
import { useRoute } from 'vue-router';

const route = useRoute();

// get orderid and fetch
const orderId = route.params.id;
const fetchOrderDetail = async (orderId) => {
    const res = await customAxios.get(`/api/v1/orders/${orderId}`);
    console.log(res);
}

fetchOrderDetail(orderId);
</script>

<template>
    asdf
</template>

<style scoped>
</style>