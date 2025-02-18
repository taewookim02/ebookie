<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { getImageFromServer } from '@/helper/imgPath';
import { customAxios } from '@/plugins/axios';
import { ref } from 'vue';
import { useToast } from 'vue-toastification';

// state
const cartDto = ref([]);
const toast = useToast();
const checkedItems = ref(new Set());
const allChecked = ref(false);


// actions
const fetchcartProducts = async () => {
    try {
        const res = await customAxios.get(`/api/v1/cart`);
        cartDto.value = res.data;
    } catch (err) {
        console.log("fetchcartProducts() err:", err);
    }
}
fetchcartProducts();

const handleDeleteClick = async (productId) => {
    try {
        const res = await customAxios.delete(`/api/v1/cart/${productId}`);

        // UI상에서 productId 같은 것 없애기
        cartDto.value = cartDto.value.filter(dto => dto.productId !== productId);
        checkedItems.value.delete(productId);
        toast.info("장바구니 목록에서 삭제 완료!");
    } catch (err) {
        console.log("handleDeleteClick() err:", err);
        toast.error("삭제 중 에러 발생!")
    }
}

const handlePurchaseClick = () => {
    console.log("구매하기");
}

const toggleCheckAll = () => {
    if (allChecked.value) {
        checkedItems.value = new Set(cartDto.value.map(dto => dto.productId));
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
        allChecked.value = checkedItems.value.size === cartDto.value.length;
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

        await customAxios.delete(`/api/v1/cart/bulk`, {
            data: { productIds }
        });

        cartDto.value = cartDto.value.filter(
            dto => !checkedItems.value.has(dto.productId)
        );

        // hmm?
        checkedItems.value.clear();
        allChecked.value = false;
        toast.info(`${productIds.length}개 상품이 장바구니 목록에서 삭제되었습니다`);
    } catch (err) {
        console.log("handleCheckedDelete() err:", err);
        toast.error("선택한 상품 삭제 중 에러가 발생했습니다!");
    }
};

</script>


<template>
    <h1>장바구니</h1>
    <section class="cart">
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
                <template v-if="cartDto && cartDto.length > 0">
                    <tr v-for="dto in cartDto">
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
                            <ActionButton class="w-100" @action="handleDeleteClick(dto.productId)">삭제</ActionButton>
                            <ActionButton class="w-100" @action="handlePurchaseClick(dto.productId)">구매하기</ActionButton>
                        </td>
                    </tr>
                </template>
                <template v-else>
                    <tr>
                        <td colspan="4" class="td-empty text-muted"><small>장바구니가 비었습니다.</small></td>
                    </tr>
                </template>
            </tbody>
        </table>
    </section>
</template>

<style scoped>
.cart td {
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