<script setup>
import AuthorDetailsSection from '@/components/sections/detail/AuthorDetailsSection.vue';
import BookDetailsSection from '@/components/sections/detail/BookDetailsSection.vue';
import DeliveryAndRefundSection from '@/components/sections/detail/DeliveryAndRefundSection.vue';
// import DetailNavSection from '@/components/sections/detail/DetailNavSection.vue';
import HeroSection from '@/components/sections/detail/HeroSection.vue';
import ProductDetailsSection from '@/components/sections/detail/ProductDetailsSection.vue';
import RelatedProductsSection from '@/components/sections/detail/RelatedProductsSection.vue';
import ReviewSection from '@/components/sections/detail/ReviewSection.vue';
import LoadingSpinner from '@/components/shared/LoadingSpinner.vue';
import { customAxios } from '@/plugins/axios';
import { onMounted, ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

// 상품id 가져오기
const route = useRoute();
const router = useRouter();

// state
const reviewSection = ref(null);
const detailDto = ref({});

// actions
const scrollToReview = () => {
    reviewSection.value.$el.scrollIntoView({ behavior: "smooth" });
}

const fetchProductDetail = async (id) => {
    try {
        const res = await customAxios.get(`/api/v1/products/${id}`);
        detailDto.value = res.data;
    } catch (err) {
        console.log("err:", err);
    }
}

// url path variable watch
watch(
    () => route.params.id,
    (newId) => {
        if (newId) {
            fetchProductDetail(newId);
        }
    },
    {immediate: true}
)


const handleSave = async () => {
    try {
        const productId = route.params.id;
        await customAxios.post(`/api/v1/save/${productId}`);
        detailDto.value.isSaved = !detailDto.value.isSaved;
    } catch (err) {
        console.log("handleSave() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login") 
        }
    }
}

const handleLike = async () => {
    try {
        const productId = route.params.id;
        await customAxios.post(`/api/v1/like/${productId}`);
        detailDto.value.isLiked = !detailDto.value.isLiked;
    } catch (err) {
        console.log("handleLike() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login") 
        }
    }
}

const handleCart = async () => {
    try {
        const productId = route.params.id;
        await customAxios.post(`/api/v1/cart/${productId}`);
        detailDto.value.isInCart = !detailDto.value.isInCart;
    } catch (err) {
        console.log("handleLike() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login") 
        }
    }
}

</script>

<template>
    <template v-if="detailDto">
        <!-- 히어로 -->
        <!-- 히어로__이미지 -->
        <!-- 히어로__정보 -->
        <!-- 정보 -->
        <!-- 액션 -->
        <HeroSection :detail-dto="detailDto"  @scroll-to-review="scrollToReview" @save="handleSave" @like="handleLike" @cart="handleCart" />

        <!-- 관련상품 -->
        <!-- 슬라이더 -->
        <RelatedProductsSection :detail-dto="detailDto"  />

        <!-- _상세 네비게이션 -->
        <!-- <DetailNavSection /> -->

        <!-- 도서정보 -->
        <ProductDetailsSection :detail-dto="detailDto" />

        <!-- 도서소개 -->
        <!-- 목차 -->
        <!-- 이미지 -->
        <BookDetailsSection :detail-dto="detailDto"/>

        <!-- 저자소개 -->
        <!-- 만든이 -->
        <!-- 추천평 -->
        <AuthorDetailsSection :detail-dto="detailDto" />

        <!-- 리뷰 -->
        <!-- 헤더 -->
        <!-- 별점 UI -->
        <!-- 리뷰   -->
        <ReviewSection ref="reviewSection" :detail-dto="detailDto" />

        <!-- 관련상품 -->
        <RelatedProductsSection :detail-dto="detailDto" />

        <!-- 배송/반품 안내 -->
        <DeliveryAndRefundSection />
    </template>
    <LoadingSpinner v-else />
    
</template>

<style scoped></style>