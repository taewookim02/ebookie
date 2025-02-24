<script setup>
import { useRoute, useRouter } from 'vue-router';
import { computed, onMounted, ref } from 'vue';
import { customAxios } from '@/plugins/axios';
import { getImageFromServer } from '@/helper/imgPath';
import ActionButton from '@/components/shared/ActionButton.vue';
import LikeButton from '@/components/shared/LikeButton.vue';
import SaveButton from '@/components/shared/SaveButton.vue';
import CartButton from '@/components/shared/CartButton.vue';
import { useToast } from 'vue-toastification';

// state
const productDtos = ref([]);
const totalPages = ref(0);
const totalElements = ref(0);
const currentPage = ref(0);

const route = useRoute();
const router = useRouter();
const toast = useToast();

const query = route.query;
console.log(query);

const paginationQueryString = new URLSearchParams(query).toString();
console.log(paginationQueryString);

const fetchProducts = async () => {
    const res = await customAxios.get(`/api/v1/products?${paginationQueryString}`);
    productDtos.value = res.data.products;
    totalPages.value = res.data.totalPages;
    totalElements.value = res.data.totalElements;
    currentPage.value = res.data.currentPage;
};

const isBestSeller = computed(() => {
    // if paginationQueryString includes sold 
    return paginationQueryString.includes('sold') && paginationQueryString.includes('desc');
})

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
    <div>
        <h1>베스트셀러 순위</h1>
        <div>
            <div class="product-item" v-for="(product, index) in productDtos" :key="product.id">
                <div class="product-rank">
                    <span>{{ index + 1 }}</span>
                </div>
                <div class="product-image">
                    <img :src="getImageFromServer(product.thumbnail.fileName)" alt="상품 이미지">
                </div>
                <div class="product-info">
                    <span>{{ product.name }}</span>
                    <div>
                        <span>{{ product.authorNames }} 저</span>
                        <small>|</small>
                        <span>{{ product.publisherName }}</span>
                        <small>|</small>
                        <span>{{ product.publishedDate }}</span>
                    </div>
                    <div>
                        <small class="text-decoration-line-through">{{ product.price }}원</small>
                        <span>{{ product.price - product.price * product.discountRate / 100 }}원</span>
                        <span>({{ product.discountRate }}% 할인)</span>
                    </div>
                    <div>
                        <span>판매량 {{ product.sold }}</span>
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

<style scoped></style>