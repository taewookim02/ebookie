<script setup>
import { useRoute, useRouter } from 'vue-router';
import { computed, onMounted, ref, watch } from 'vue';
import { customAxios } from '@/plugins/axios';
import { getImageFromServer } from '@/helper/imgPath';
import ActionButton from '@/components/common/ActionButton.vue';
import LikeButton from '@/components/common/LikeButton.vue';
import SaveButton from '@/components/common/SaveButton.vue';
import CartButton from '@/components/common/CartButton.vue';
import { useToast } from 'vue-toastification';
import { formatDateYYMMKr } from '@/helper/format';
import Pagination from '@/components/common/Pagination.vue';

// state
const productDtos = ref([]);
const totalPages = ref(0);
const totalElements = ref(0);
const currentPage = ref(0);
const isLoading = ref(false);
const pageSize = ref(20);

const route = useRoute();
const router = useRouter();
const toast = useToast();

// 쿼리 빌딩
const query = ref(route.query);
const paginationQueryString = ref(new URLSearchParams(query.value).toString());
console.log(paginationQueryString.value);

const fetchProducts = async () => {
    isLoading.value = true;
    try {
        const res = await customAxios.get(`/api/v1/products?${paginationQueryString.value}&size=${pageSize.value}`);
        productDtos.value = res.data.products;
        totalPages.value = res.data.totalPages;
        totalElements.value = res.data.totalElements;
        currentPage.value = res.data.currentPage;
    } catch (err) {
        console.error("Failed to fetch products:", err);
        productDtos.value = [];
    } finally {
        isLoading.value = false;
    }
};

// 마운트되면 페이지 사이즈 초기화
onMounted(() => {
    const urlSize = Number(route.query.size);
    if (urlSize) {
        pageSize.value = urlSize;
    }
    fetchProducts();
});

watch(route, (newValue, oldValue) => {
    query.value = newValue.query;
    paginationQueryString.value = new URLSearchParams(query.value).toString();

    // 쿼리 변경되면 페이지 사이즈 초기화
    const newSize = Number(newValue.query.size);
    if (newSize && newSize !== pageSize.value) {
        pageSize.value = newSize;
    }

    fetchProducts();
}, { deep: true })



// computed
const isBestSeller = computed(() => {
    // paginationQueryString에 sold 포함 내림차순
    return paginationQueryString.value.includes('sold') && paginationQueryString.value.includes('desc');
})

const isNew = computed(() => {
    return paginationQueryString.value.includes('publishedDate') && paginationQueryString.value.includes('desc');
})

const isSale = computed(() => {
    return paginationQueryString.value.includes('discountRate') && paginationQueryString.value.includes('desc');
})


// actions
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

const handleBuy = async (productId) => {
    try {
        console.log("handleBuy");
        const productIds = [productId];
        const res = await customAxios.post(`/api/v1/orders`, { productIds });
        const orderId = res.data.orderId;
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

const getUpdatedQueryString = (newPage) => {
    const params = new URLSearchParams(query.value);
    params.set('page', newPage);
    params.set('size', pageSize.value);
    return params.toString();
};

const handlePageChange = (page) => {
    router.push(`/products?${getUpdatedQueryString(page - 1)}`);
};

const handlePageSizeChange = () => {
    const currentQuery = router.currentRoute.value.query;

    router.push({
        path: '/products',
        query: {
            page: '0',
            size: pageSize.value,
            sort: currentQuery.sort || 'publishedDate,desc'
        }
    });
};

</script>

<template>
    <div class="product-list-page">
        <h3 v-if="isBestSeller">베스트셀러 순위</h3>
        <h3 v-if="isNew">신상품 순위</h3>
        <h3 v-if="isSale">세일 순위</h3>

        <!-- Page size selector -->
        <div class="d-flex justify-content-end mb-3">
            <select class="form-select" style="width: auto;" v-model="pageSize" @change="handlePageSizeChange">
                <option value="10">10개씩 보기</option>
                <option value="20">20개씩 보기</option>
                <option value="50">50개씩 보기</option>
            </select>
        </div>

        <!-- Loading state -->
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

        <!-- Product list -->
        <div v-else>
            <div class="product-item" v-for="(product, index) in productDtos" :key="product.id">
                <div class="product-rank">
                    <span>{{ currentPage * pageSize + index + 1 }}</span>
                </div>
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
                        <span class="product-price">{{ (product.price - product.price * product.discountRate /
                            100).toLocaleString()
                            }}원</span>
                        <span v-if="product.discountRate" class="badge text-bg-danger">{{ product.discountRate
                        }}%</span>
                    </div>
                    <div>
                        <span>판매량 {{ product.sold.toLocaleString() }}부</span>
                    </div>
                </div>
                <div class="product-actions">
                    <CartButton @cart="handleCartAdd(product.id)" :is-active="product.isInCart" />
                    <LikeButton @like="handleLike(product.id)" :is-active="product.isLiked" />
                    <SaveButton @save="handleSave(product.id)" :is-active="product.isSaved" />
                    <ActionButton @action="handleBuy(product.id)">구매하기</ActionButton>
                </div>
            </div>
        </div>

        <!-- 페이지네이션 -->
        <Pagination 
            v-if="totalPages > 0"
            :current-page="currentPage + 1"
            :total-pages="totalPages"
            @page-change="handlePageChange"
        />
    </div>
</template>

<style scoped>
.product-item {
    display: grid;
    grid-template-columns: auto 150px 1fr auto;
    gap: 1.6rem;
    padding: 1.6rem;
    border-bottom: 1px solid #eee;
    align-items: center;
}

.product-rank {
    width: 40px;
    height: 40px;
    display: grid;
    place-items: center;
    font-size: 2.4rem;
    font-weight: bolder;
    background: #f8f9fa;
    border-radius: 50%;
}

.product-image img {
    width: 150px;
    height: 200px;
    object-fit: cover;
}

.product-info {
    display: flex;
    flex-direction: column;
    gap: 0.8rem;
}

.product-name {
    font-size: 1.8rem;
    font-weight: bold;
}

.product-info>div {
    display: flex;
    gap: 0.4rem;
    align-items: center;
    color: #6c757d;
}

.product-actions {
    display: flex;
    flex-direction: column;
    gap: 0.8rem;
    align-items: stretch;
}

.product-price {
    font-size: 1.8rem;
    font-weight: bolder;
}

.empty-state {
    padding: 4rem;
    color: #6c757d;
}
</style>