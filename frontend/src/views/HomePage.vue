<script setup>
import ProductSliderList from '@/components/sections/ProductSliderList.vue';
import HeroSection2 from '@/components/sections/HeroSection2.vue';
import TimedBestSeller from '@/components/sections/TimedBestSeller.vue';
import { customAxios } from '@/plugins/axios';
import { ref } from 'vue';

const categoryProductsDto = ref([]);

const fetchHomeDto = async () => {
    try {
        const res = await customAxios.get(`/api/v1/products/home`);
        categoryProductsDto.value = res.data.categoryProducts;
        // console.log(categoryProductsDto.value);
    } catch (err) {
        console.log(err);
    }
}

fetchHomeDto();


</script>

<template>
    <HeroSection2 />
    <template v-for="dto in categoryProductsDto">
        <ProductSliderList :slider-title="`${dto.name} 관련도서`" :products="dto.products" :category-id="dto.id" />
    </template>
    <!-- <TimedBestSeller /> -->
</template>

<style scoped></style>