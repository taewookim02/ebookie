<script setup>
import ProductSliderList from '@/components/domain/product/ProductSliderList.vue';
import HeroSection from '@/components/domain/home/HeroSection.vue';
import TimedBestSeller from '@/components/domain/product/TimedBestSeller.vue';
import { customAxios } from '@/plugins/axios';
import { ref } from 'vue';

const categoryProductDtos = ref([]);
const topFiveProductDtos = ref([]);

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

fetchHomeDto();


</script>

<template>
    <HeroSection :topFiveProductDtos="topFiveProductDtos" />
    <template v-for="dto in categoryProductDtos">
        <ProductSliderList :slider-title="`${dto.name} 관련도서`" :products="dto.products" :category-id="dto.id" />
    </template>
    <!-- <TimedBestSeller /> -->
</template>

<style scoped></style>