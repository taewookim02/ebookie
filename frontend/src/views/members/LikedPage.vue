<script setup>
import ActionButton from '@/components/common/ActionButton.vue';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { onMounted, ref } from 'vue';
import { useToast } from 'vue-toastification';

// state
const likedDto = ref([]);
const toast = useToast();
const checkedItems = ref(new Set());
const allChecked = ref(false);


// actions
const handleCartClick = async (productId) => {
    try {
        // add to cart
        const res = await customAxios.post(`/api/v1/cart`, { productId });
        toast.success("장바구니 추가 완료!");
    } catch (err) {
        console.log("handleCartClick() err:", err);
        toast.error("장바구니 추가 중 에러 발생!")
    }

};

const handleDeleteClick = async (productId) => {
    try {
        const res = await customAxios.delete(`/api/v1/liked/${productId}`);

        // UI상에서 productId 같은 것 없애기
        likedDto.value = likedDto.value.filter(dto => dto.productId !== productId);
        checkedItems.value.delete(productId);
        toast.info("좋아요 목록에서 삭제 완료!");
    } catch (err) {
        console.log("handleDeleteClick() err:", err);
        toast.error("삭제 중 에러 발생!")
    }
}

const toggleCheckAll = () => {
    if (allChecked.value) {
        checkedItems.value = new Set(likedDto.value.map(dto => dto.productId));
    } else {
        checkedItems.value.clear();
    }
}

const toggleCheck = (productId) => {
    if (checkedItems.value.has(productId)) {
        checkedItems.value.delete(productId);
        allChecked.value = false;
    } else {
        checkedItems.value.add(productId);
        allChecked.value = checkedItems.value.size === likedDto.value.length;
    }
}

const handleCheckedDelete = async () => {
    // 체크박스 체크 된 아이템들의 productId 구하기
    // 서버에 productId 리스트를 보내기
    // 서버에서 productId 일치하는 아이템들 삭제
    if (checkedItems.value.size === 0) {
        toast.warning("삭제할 상품을 선택해주세요");
        return;
    }
    try {
        const productIds = Array.from(checkedItems.value);

        await customAxios.delete(`/api/v1/liked/bulk`, {
            data: { productIds }
        });

        likedDto.value = likedDto.value.filter(
            dto => !checkedItems.value.has(dto.productId)
        );

        // hmm?
        checkedItems.value.clear();
        allChecked.value = false;
        toast.info(`${productIds.length}개 상품이 좋아요 목록에서 삭제되었습니다`);
    } catch (err) {
        console.log("handleCheckedDelete() err:", err);
        toast.error("선택한 상품 삭제 중 에러가 발생했습니다!");
    }
};

const fetchLikedProducts = async () => {
    try {
        const res = await customAxios.get(`/api/v1/liked`);
        likedDto.value = res.data;
    } catch (err) {
        console.log("fetchLikedProducts() err:", err);
    }
}

// Lifecycle hooks
onMounted(() => {
    fetchLikedProducts();
});
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
                        <input type="checkbox" name="all" id="all" v-model="allChecked" @change="toggleCheckAll">
                    </th>
                    <th colspan="2">
                        <label for="all">전체선택</label>
                        <ActionButton @action="handleCheckedDelete" :disabled="checkedItems.size === 0" class="btn-select-delete">선택삭제</ActionButton>
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <template v-if="likedDto && likedDto.length > 0">
                    <tr v-for="dto in likedDto">
                        <td>
                            <input type="checkbox" :checked="checkedItems.has(dto.productId)" @change="toggleCheck(dto.productId)">
                        </td>
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
                            <ActionButton class="w-100 mb-2" @action="handleCartClick(dto.productId)">장바구니 담기
                            </ActionButton>
                            <ActionButton class="w-100" @action="handleDeleteClick(dto.productId)">삭제</ActionButton>
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