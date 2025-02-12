<script setup>
import AuthorDetailsSection from '@/components/sections/detail/AuthorDetailsSection.vue';
import BookDetailsSection from '@/components/sections/detail/BookDetailsSection.vue';
import DeliveryAndRefundSection from '@/components/sections/detail/DeliveryAndRefundSection.vue';
// import DetailNavSection from '@/components/sections/detail/DetailNavSection.vue';
import HeroSection from '@/components/sections/detail/HeroSection.vue';
import ProductDetailsSection from '@/components/sections/detail/ProductDetailsSection.vue';
import RelatedProductsSection from '@/components/sections/detail/RelatedProductsSection.vue';
import ReviewSection from '@/components/sections/detail/ReviewSection.vue';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

// 상품id 가져오기
const route = useRouter();
let productId = route.currentRoute.value.params.id;

// state
const reviewSection = ref(null);

// actions
const scrollToReview = () => {
    reviewSection.value.$el.scrollIntoView({ behavior: "smooth" });
}


// lifecycle
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
    <HeroSection :product-id="productId" @scroll-to-review="scrollToReview" />

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
    <AuthorDetailsSection />

    <!-- 리뷰 -->
    <!-- 헤더 -->
    <!-- 별점 UI -->
    <!-- 리뷰   -->
    <ReviewSection ref="reviewSection" />

    <!-- 관련상품 -->
    <RelatedProductsSection :product-id="productId" />

    <!-- 배송/반품 안내 -->
    <DeliveryAndRefundSection />

</template>

<style scoped></style>