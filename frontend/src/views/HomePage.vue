<script setup>
import ProductSliderList from '@/components/domain/product/ProductSliderList.vue';
import HeroSection from '@/components/domain/home/HeroSection.vue';
import { customAxios } from '@/plugins/axios';
import { onMounted, ref } from 'vue';

// State
const categoryProductDtos = ref([]);
const topFiveProductDtos = ref([]);

// Actions
const fetchHomeDto = async () => {
    try {
        const res = await customAxios.get(`/api/v1/products/home`);
        categoryProductDtos.value = res.data.categoryProducts;
        topFiveProductDtos.value = res.data.topFiveSellingProducts;
        // console.log(categoryProductDtos.value);
    } catch (err) {
        console.log(err);
    }
}

// Lifecycle hooks
onMounted(() => {
    fetchHomeDto();
});
</script>

<template>
    <HeroSection :topFiveProductDtos="topFiveProductDtos" />
    <template v-for="dto in categoryProductDtos">
        <ProductSliderList :slider-title="`${dto.name} 관련도서`" :products="dto.products" :category-id="dto.id" />
    </template>
</template>

<style scoped></style>