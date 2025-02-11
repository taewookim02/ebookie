<script setup>
import HeroSection from '@/components/sections/detail/HeroSection.vue';
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
    
    <!-- 상세 네비게이션 -->

    <!-- 도서정보 -->
        <!-- 소개 -->
        <!-- 목차 -->
        <!-- 이미지 -->
        <!-- 저자소개 -->
        <!-- 만든이 -->
        <!-- 추천평 -->    
    <!-- 리뷰 -->
        <!-- 헤더 -->
        <!-- 별점 UI -->
        <!-- 리뷰   -->
</template>

<style scoped></style>