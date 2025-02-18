<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { ref } from 'vue';

// state
const likedDto = ref([]);


// actions
const fetchLikedProducts = async () => {
    try {
        const res = await customAxios.get(`/api/v1/liked`);
        likedDto.value = res.data;
    } catch (err) {
        console.log("fetchLikedProducts() err:", err);
    }
}
fetchLikedProducts();

const handleCartClick = (likedId, productId) => {
    console.log("handleCartClick", likedId, productId);
    // add to cart
    // remove from likedd
};

const handleDeleteClick = (likedId) => {
    console.log("handleDeleteClick", likedId);
}

</script>


<template>
    <h1>좋아요한 상품</h1>
    <section class="liked">
        <table class="table">
            <colgroup>
                <col width="1%" />
                <col width="20%" />
                <col width="*" />
                <col width="20%" />
            </colgroup>
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" name="all" id="all">
                    </th>
                    <th colspan="2">
                        <label for="all">전체선택</label>
                        <ActionButton class="btn-select-delete">선택삭제</ActionButton>
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <template v-if="likedDto && likedDto.length > 0">
                    <tr v-for="dto in likedDto">
                        <td><input type="checkbox" name="" id=""></td>
                        <td>
                            <RouterLink :to="`/products/${dto.productId}`">
                                <img class="img-product" :src="getImageFromServer(dto.thumbnail)" alt="상품 이미지">
                            </RouterLink>
                        </td>
                        <td>
                            <RouterLink :to="`/products/${dto.productId}`" class="link-dark">{{ dto.name }}</RouterLink>
                            <div class="text-muted"><small>{{ dto.authorNames }}</small></div>
                        </td>
                        <td class="td-action">
                            <ActionButton class="w-100 mb-2" @action="handleCartClick(dto.id, dto.productId)">장바구니 담기</ActionButton>
                            <ActionButton class="w-100" @action="handleDeleteClick(dto.id)">삭제</ActionButton>
                        </td>
                    </tr>
                </template>
                <template v-else>
                    <tr>
                        <td colspan="4" class="td-empty text-muted"><small>좋아요 한 상품이 없습니다.</small></td>
                    </tr>
                </template>
            </tbody>
        </table>
    </section>
</template>

<style scoped>
.liked td {
    vertical-align: middle;
}

.img-product {
    width: 10.4rem;
    height: 10.4rem;
    object-fit: contain;

}

.td-action {
    vertical-align: middle;
}

.td-empty {
    text-align: center;
    padding: 1.6rem;
}

.btn-select-delete {
    margin-left: 1.6rem;
}
</style>