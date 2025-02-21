<script setup>
import { useRoute } from 'vue-router';
import { onMounted, ref } from 'vue';
import { customAxios } from '@/plugins/axios';

// state
const productsDto = ref([]);

// get query param from url
const route = useRoute()
const query = route.query;
console.log(query);

// Replace the manual query string building with URLSearchParams
const paginationQueryString = new URLSearchParams(query).toString();
console.log(paginationQueryString);

const fetchProducts = async () => {
    const res = await customAxios.get(`/api/v1/products?${paginationQueryString}`);
    console.log(res);
};

onMounted(() => {
    fetchProducts();
});

</script>

<template>
    
</template>

<style scoped>

</style>