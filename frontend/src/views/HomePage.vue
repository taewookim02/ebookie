<script setup>
import HeroSection from '@/components/sections/HeroSection.vue';
import ProductList from '@/components/sections/ProductList.vue';
import ExampleSection from '@/components/sections/ExampleSection.vue';
import CtaSection from '@/components/sections/CtaSection.vue';
import ProductSliderList from '@/components/sections/ProductSliderList.vue';
import HeroSection2 from '@/components/sections/HeroSection2.vue';

// TODO: dtoList for each ProductSliderList

// TODO: categoryList for ProductSliderList

</script>

<template>
  <!-- <HeroSection /> -->
  <!-- <ProductList /> -->
   <HeroSection2 />
  <ProductSliderList slider-title="자격증 추천도서" />
  <ProductSliderList slider-title="데이터 관련도서" />
  <ExampleSection />
  <CtaSection />
</template>

<style scoped></style>