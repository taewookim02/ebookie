<script setup>
import { useRoute, useRouter } from 'vue-router';
import { computed, onMounted, ref, watch } from 'vue';
import { customAxios } from '@/plugins/axios';
import { getImageFromServer } from '@/helper/imgPath';
import ActionButton from '@/components/shared/ActionButton.vue';
import LikeButton from '@/components/shared/LikeButton.vue';
import SaveButton from '@/components/shared/SaveButton.vue';
import CartButton from '@/components/shared/CartButton.vue';
import { useToast } from 'vue-toastification';
import { formatDateYYMMKr } from '@/helper/format';
// state
const productDtos = ref([]);
const totalPages = ref(0);
const totalElements = ref(0);
const currentPage = ref(0);

const route = useRoute();
const router = useRouter();
const toast = useToast();

// 쿼리 빌딩
const query = ref(route.query);
const paginationQueryString = ref(new URLSearchParams(query.value).toString());

// on query change
watch(route, (newValue, oldValue) => {
    query.value = newValue.query;
    paginationQueryString.value = new URLSearchParams(query.value).toString();
    fetchProducts();
}, { deep: true })

const fetchProducts = async () => {
    const res = await customAxios.get(`/api/v1/products?${paginationQueryString.value}`);
    productDtos.value = res.data.products;
    totalPages.value = res.data.totalPages;
    totalElements.value = res.data.totalElements;
    currentPage.value = res.data.currentPage;
};

// computed
const isBestSeller = computed(() => {
    // if paginationQueryString includes sold 
    return paginationQueryString.value.includes('sold') && paginationQueryString.value.includes('desc');
})

const isNew = computed(() => {
    return paginationQueryString.value.includes('publishedDate') && paginationQueryString.value.includes('desc');
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

onMounted(() => {
    fetchProducts();
});

</script>

<template>
    <div class="product-list-page">
        <h1 v-if="isBestSeller">베스트셀러 순위</h1>
        <h1 v-if="isNew">신상품 순위</h1>
        <div>
            <div class="product-item" v-for="(product, index) in productDtos" :key="product.id">
                <div class="product-rank">
                    <span>{{ index + 1 }}</span>
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
                        <span class="product-price">{{ (product.price - product.price * product.discountRate / 100).toLocaleString()
                        }}원</span>
                        <!-- <span>({{ product.discountRate }}% 할인)</span> -->
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
</style>