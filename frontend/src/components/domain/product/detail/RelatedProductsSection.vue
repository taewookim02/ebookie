<script setup>
import ProductSliderList from '@/components/domain/product/ProductSliderList.vue';


// State
const props = defineProps({
    productId: String,
    detailDto: Object,
    categoryId: Number
});
</script>


<template>
    <section class="related">
        <ProductSliderList :products="props.detailDto.relatedProducts" sliderTitle="관련 eBook 추천" :category-id="props.categoryId" />
    </section>
</template>

<style scoped>
.related {
    padding: 1.6rem;
    border: 1px solid #ccc;
}
</style>