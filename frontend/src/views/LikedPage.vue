<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { customAxios } from '@/plugins/axios';
import { ref } from 'vue';

const likedDto = ref({});

const fetchLikedProducts = async () => {
    try {
        const res = await customAxios.get(`/api/v1/liked`);
        likedDto.value = res.data;
    } catch (err) {
        console.log("fetchLikedProducts() err:", err);
    }
}
fetchLikedProducts();

</script>


<template>u
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
                <tr>
                    <td><input type="checkbox" name="" id=""></td>
                    <td>
                        <RouterLink to="#"><img class="img-product" src="https://placehold.co/400" alt="상품 이미지"></RouterLink>
                    </td>
                    <td>
                        <RouterLink to="#" class="link-dark">[제목] Lorem ipsum, dolor sit amet consectetur adipisicing
                            elit. Nobis, odit?</RouterLink>
                        <div>10,200원</div>
                    </td>
                    <td class="td-action">
                        <ActionButton class="w-100 mb-2">장바구니 담기</ActionButton>
                        <ActionButton class="w-100">삭제</ActionButton>
                    </td>
                </tr>
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

.btn-select-delete {
    margin-left: 1.6rem;
}
</style>