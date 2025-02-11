<script setup>
import BookDetailsSection from '@/components/sections/detail/BookDetailsSection.vue';
import DetailNavSection from '@/components/sections/detail/DetailNavSection.vue';
import HeroSection from '@/components/sections/detail/HeroSection.vue';
import ProductDetailsSection from '@/components/sections/detail/ProductDetailsSection.vue';
import RelatedProductsSection from '@/components/sections/detail/RelatedProductsSection.vue';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

// 상품id 가져오기
const route = useRouter();
let productId = route.currentRoute.value.params.id;

onMounted(() => {
    // productID로 서버에 상품 정보 요청
})

</script>

<template>
    
    <!-- 히어로 -->
        <!-- 히어로__이미지 -->
        <!-- 히어로__정보 -->
            <!-- 정보 -->
            <!-- 액션 -->
    <HeroSection :product-id="productId" />

    <!-- 관련상품 -->
        <!-- 슬라이더 -->
    <RelatedProductsSection :product-id="productId" />
    
    <!-- _상세 네비게이션 -->
    <!-- <DetailNavSection /> -->

    <!-- 도서정보 -->
    <ProductDetailsSection />

    <!-- 도서소개 -->
        <!-- 목차 -->
        <!-- 이미지 -->
    <BookDetailsSection />
    
    <!-- 저자소개 -->
        <!-- 만든이 -->
        <!-- 추천평 -->    
    <!-- 리뷰 -->
        <!-- 헤더 -->
        <!-- 별점 UI -->
        <!-- 리뷰   -->
</template>

<style scoped></style>