<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref, computed, onMounted, watch } from 'vue';
import { customAxios } from '@/plugins/axios';
import { getImageFromServer } from '@/helper/imgPath';
import { formatDateYYMMKr } from '@/helper/format';
import CartButton from '@/components/common/CartButton.vue';
import LikeButton from '@/components/common/LikeButton.vue';
import SaveButton from '@/components/common/SaveButton.vue';
import { useToast } from 'vue-toastification';
import Pagination from '@/components/common/Pagination.vue';

const productDtos = ref([]);
const totalPages = ref(0);
const totalElements = ref(0);
const currentPage = ref(0);
const isLoading = ref(false);
const categoryName = ref('');
const pageSize = ref(2);

const route = useRoute();
const router = useRouter();
const toast = useToast();
const categoryId = computed(() => route.params.id);

const fetchProducts = async () => {
    isLoading.value = true;
    try {
        const res = await customAxios.get(`/api/v1/products/categories/${categoryId.value}?page=${currentPage.value}&size=${pageSize.value}`);
        productDtos.value = res.data.products;
        totalPages.value = res.data.totalPages;
        totalElements.value = res.data.totalElements;
        currentPage.value = res.data.currentPage;
        categoryName.value = res.data.categoryName;
    } catch (err) {
        console.error("Failed to fetch products:", err);
        productDtos.value = [];
    } finally {
        isLoading.value = false;
    }
}

onMounted(() => {
    const page = Number(route.query.page) || 0;
    currentPage.value = page;
    fetchProducts();
})

watch(
    () => route.params.id,
    (newId, oldId) => {
        currentPage.value = 0;
        fetchProducts();
    }
)

const handlePageChange = (page) => {
    currentPage.value = page - 1;
    router.push({
        query: { ...route.query, page: currentPage.value }
    });
    fetchProducts();
};

const handleSave = async (productId) => {
    try {
        await customAxios.post(`/api/v1/saved/${productId}`);
        const product = productDtos.value.find(p => p.id === productId);
        if (product) {
            product.isSaved = !product.isSaved;
            if (product.isSaved) {
                toast.success("찜 목록에 저장 완료!");
            } else {
                toast.info("찜 목록에서 삭제 완료!");
            }
        }
    } catch (err) {
        console.log("handleSave() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login")
        }
    }
}

const handleLike = async (productId) => {
    try {
        await customAxios.post(`/api/v1/liked/${productId}`);
        const product = productDtos.value.find(p => p.id === productId);
        if (product) {
            product.isLiked = !product.isLiked;
            if (product.isLiked) {
                toast.success("좋아요 목록에 저장 완료!");
            } else {
                toast.info("좋아요 목록에서 삭제 완료!");
            }
        }
    } catch (err) {
        console.log("handleLike() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login")
        }
    }
}

const handleCartAdd = async (productId) => {
    try {
        await customAxios.post(`/api/v1/cart/${productId}`);
        const product = productDtos.value.find(p => p.id === productId);
        if (product) {
            product.isInCart = !product.isInCart;
            if (product.isInCart) {
                toast.success("장바구니 목록에 저장 완료!");
            } else {
                toast.info("장바구니 목록에서 삭제 완료!");
            }
        }
    } catch (err) {
        console.log("handleCartAdd() err:", err);
        const isNotLoggedIn = err.status === 401;
        if (isNotLoggedIn) {
            router.push("/login")
        }
    }
}
</script>

<template>
    <div class="product-list-page">
        <h3>{{ categoryName }} 분야</h3>
        <h4>상품 ({{ totalElements.toLocaleString() }})</h4>

        <!-- 로딩 -->
        <div v-if="isLoading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>

        <!-- Empty state -->
        <div v-else-if="!productDtos.length" class="text-center py-5">
            <div class="empty-state">
                <i class="bi bi-book" style="font-size: 4rem;"></i>
                <h3 class="mt-4">상품이 없습니다</h3>
                <p class="text-muted">해당하는 상품을 찾을 수 없습니다.</p>
            </div>
        </div>

        <!-- 상품목록 -->
        <div v-else>
            <div class="product-grid">
                <div class="product-item" v-for="product in productDtos" :key="product.id">
                    <div class="product-image">
                        <RouterLink :to="`/products/${product.id}`">
                            <img :src="getImageFromServer(product.thumbnail.fileName)" alt="상품 이미지">
                        </RouterLink>
                    </div>
                    <div class="product-info">
                        <span class="product-name">
                            <RouterLink :to="`/products/${product.id}`" class="link-dark">{{ product.name }}</RouterLink>
                        </span>
                        <div>
                            <span>{{ product.authorNames }} 저</span>
                            <small>|</small>
                            <span>{{ product.publisherName }}</span>
                            <small>|</small>
                            <span>{{ formatDateYYMMKr(product.publishedDate) }}</span>
                        </div>
                        <div>
                            <small class="text-decoration-line-through">{{ product.price.toLocaleString() }}원</small>
                            <span class="product-price">{{ (product.price - product.price * product.discountRate / 100).toLocaleString() }}원</span>
                            <span v-if="product.discountRate" class="badge text-bg-danger">{{ product.discountRate }}%</span>
                        </div>
                        <div>
                            <span>판매량 {{ product.sold.toLocaleString() }}부</span>
                        </div>
                    </div>
                    <div class="product-actions">
                        <CartButton @cart="handleCartAdd(product.id)" :is-active="product.isInCart" />
                        <LikeButton @like="handleLike(product.id)" :is-active="product.isLiked" />
                        <SaveButton @save="handleSave(product.id)" :is-active="product.isSaved" />
                    </div>
                </div>
            </div>

            <!-- Pagination -->
            <Pagination 
                v-if="totalPages > 0"
                :current-page="currentPage + 1"
                :total-pages="totalPages"
                @page-change="handlePageChange"
            />
        </div>
    </div>
</template>

<style scoped>
.product-list-page {
    padding: 20px;
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
}

.product-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(20.8rem, 1fr));
    gap: 20px;
}

.product-item {
    border: 1px solid #ddd;
    padding: 15px;
    border-radius: 8px;
}

.product-image img {
    width: 100%;
    height: 200px;
    object-fit: contain;
    border-radius: 4px;
}

.product-info {
    margin-top: 10px;
}

.product-name {
    font-size: 1.1em;
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
}

.product-price {
    font-weight: bold;
    color: #e74c3c;
    margin-left: 5px;
}

.product-actions {
    display: flex;
    gap: 10px;
    margin-top: 10px;
}
</style>
