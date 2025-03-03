<script setup>
import AuthorDetailsSection from '@/components/domain/product/detail/AuthorDetailsSection.vue';
import BookDetailsSection from '@/components/domain/product/detail/BookDetailsSection.vue';
import DeliveryAndRefundSection from '@/components/domain/product/detail/DeliveryAndRefundSection.vue';
import HeroSection from '@/components/domain/product/detail/HeroSection.vue';
import ProductDetailsSection from '@/components/domain/product/detail/ProductDetailsSection.vue';
import RelatedProductsSection from '@/components/domain/product/detail/RelatedProductsSection.vue';
import ReviewSection from '@/components/domain/product/detail/ReviewSection.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';
import ActionButton from '@/components/common/ActionButton.vue';
import { customAxios } from '@/plugins/axios';
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useToast } from 'vue-toastification';

// Use
const route = useRoute();
const router = useRouter();

// State
const reviewSection = ref(null);
const detailDto = ref({});
const toast = useToast();
const isLoading = ref(true);
const hasError = ref(false);

// Actions
const scrollToReview = () => {
    reviewSection.value.$el.scrollIntoView({ behavior: "smooth" });
}

const handleSave = async () => {
    try {
        const productId = route.params.id;
        await customAxios.post(`/api/v1/saved/${productId}`);
        detailDto.value.isSaved = !detailDto.value.isSaved;
        if (detailDto.value.isSaved) {
            toast.success("찜 목록에 저장 완료!");
        } else {
            toast.info("찜 목록에서 삭제 완료!");
        }
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
        await customAxios.post(`/api/v1/liked/${productId}`);
        detailDto.value.isLiked = !detailDto.value.isLiked;
        if (detailDto.value.isLiked) {
            toast.success("좋아요 목록에 저장 완료!");
        } else {
            toast.info("좋아요 목록에서 삭제 완료!");
        }
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
        if (detailDto.value.isInCart) {
            toast.success("장바구니 목록에 저장 완료!");
        } else {
            toast.info("장바구니 목록에서 삭제 완료!");
        }
    } catch (err) {
        console.log("handleLike() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login")
        }
    }
}

const handleBuy = async () => {
    try {
        console.log("handleBuy");
        const productIds = [route.params.id];
        const res = await customAxios.post(`/api/v1/orders`, { productIds });
        const orderId = res.data.id;
        router.push(`/orders/${orderId}`);
    }
    catch (err) {
        console.log("handleBuy() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login")
        }
    }
}


const fetchProductDetailDto = async (id) => {
    try {
        isLoading.value = true;
        hasError.value = false;
        const res = await customAxios.get(`/api/v1/products/${id}`);
        detailDto.value = res.data;
    } catch (err) {
        console.log("err:", err);
        hasError.value = true;
        if (err.response?.status === 404) {
            toast.error("존재하지 않는 상품입니다.");
            router.push('/products');
        } else {
            toast.error("상품 정보를 불러올 수 없습니다.");
        }
    } finally {
        isLoading.value = false;
    }
}

// url path variable watch
watch(
    () => route.params.id,
    (newId) => {
        if (newId) {
            fetchProductDetailDto(newId);
        }
    },
    { immediate: true }
)
</script>

<template>
    <div v-if="isLoading" class="loading">
        <LoadingSpinner />
    </div>
    
    <div v-else-if="hasError" class="empty-state">
        <i class="bi bi-exclamation-circle" style="font-size: 4rem;"></i>
        <h3 class="mt-4">상품 정보를 불러올 수 없습니다</h3>
        <p class="text-muted">잠시 후 다시 시도해주세요.</p>
        <ActionButton class="mt-3" @action="router.push('/products')">상품 목록으로</ActionButton>
    </div>

    <template v-else-if="detailDto">
        <!-- 히어로 -->
        <HeroSection :detail-dto="detailDto" @scroll-to-review="scrollToReview" @save="handleSave" @like="handleLike"
            @cart="handleCart" @buy="handleBuy" />

        <!-- 관련상품 슬라이더 -->
        <RelatedProductsSection :detail-dto="detailDto" :category-id="detailDto.categoryId" />

        <!-- 도서정보 -->
        <ProductDetailsSection :detail-dto="detailDto" />

        <!-- 도서소개 -->
        <BookDetailsSection :detail-dto="detailDto" />

        <!-- 저자소개 -->
        <AuthorDetailsSection :detail-dto="detailDto" />

        <!-- 리뷰 -->
        <ReviewSection ref="reviewSection" :detail-dto="detailDto" />

        <!-- 관련상품 -->
        <RelatedProductsSection :detail-dto="detailDto" :category-id="detailDto.categoryId" />

        <!-- 배송/반품 안내 -->
        <DeliveryAndRefundSection />
    </template>
</template>

<style scoped>
.loading,
.empty-state {
    min-height: 50vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    padding: 4rem;
    color: #6c757d;
}
</style>