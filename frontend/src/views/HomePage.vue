<script setup>
// import HeroSection from '@/components/sections/HeroSection.vue';
// import ProductList from '@/components/sections/ProductList.vue';
// import ExampleSection from '@/components/sections/ExampleSection.vue';
// import CtaSection from '@/components/sections/CtaSection.vue';
import ProductSliderList from '@/components/sections/ProductSliderList.vue';
import HeroSection2 from '@/components/sections/HeroSection2.vue';
import TimedBestSeller from '@/components/sections/TimedBestSeller.vue';

// TODO: dtoList for each ProductSliderList

// TODO: categoryList for ProductSliderList

</script>

<template>
    <!-- <HeroSection /> -->
    <!-- <ProductList /> -->
    <HeroSection2 />
    <ProductSliderList slider-title="자격증 추천도서" />
    <ProductSliderList slider-title="데이터 관련도서" />
    <ProductSliderList slider-title="백엔드 관련도서" />
    <ProductSliderList slider-title="프론트엔드 관련도서" />
    <ProductSliderList slider-title="게임개발 관련도서" />
    <ProductSliderList slider-title="AI 관련도서" />
    <TimedBestSeller />
    <!-- <ExampleSection />
  <CtaSection /> -->
</template>

<style scoped></style>