<script setup>
import ProductSliderList from '../ProductSliderList.vue';


// state
const props = defineProps({
    productId: String
});

console.log(props.productId);
</script>

<template>
    <section class="related">
        <!-- TODO 서버에서 같은 카테고리 상품 불러오기 -->
        <ProductSliderList sliderTitle="관련 eBook 추천" />
    </section>
</template>

<style scoped>
.related {
    padding: 1.6rem;
    border: 1px solid #ccc;
}
</style>