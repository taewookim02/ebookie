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
    <!-- <HeroSection /> -->
    <!-- 이번달 top5 List<ResponseDto>
            - 상품ID
            - 제목
            - 썸네일
            - 저자
        -->
    <HeroSection2 />
    <!-- 자격증 List<ProductRelatedResponseDto> -->
    <template v-for="dto in categoryProductsDto">
        <ProductSliderList :slider-title="`${dto.name} 관련도서`" :products="dto.products"  />
    </template>
    <!-- 데이터 List<ProductRelatedResponseDto> -->
    <!-- <ProductSliderList slider-title="데이터 관련도서" />
    <ProductSliderList slider-title="백엔드 관련도서" />
    <ProductSliderList slider-title="프론트엔드 관련도서" />
    <ProductSliderList slider-title="게임개발 관련도서" />
    <ProductSliderList slider-title="AI 관련도서" /> -->
    <TimedBestSeller />
    <!-- <ExampleSection />
  <CtaSection /> -->
</template>

<style scoped></style>