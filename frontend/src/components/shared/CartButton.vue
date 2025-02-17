<script setup>
import { PhShoppingBagOpen } from '@phosphor-icons/vue';

const props = defineProps({
    size: {
        type: Number,
        default: 32
    },
    color: {
        type: String,
        default: "#333333",
    },
    weight: {
        type: String,
        default: "light"
    },
    isActive: Boolean
});


const emit = defineEmits(['cart']);

const handleCartClick = () => {
    emit('cart');
};
</script>

<template>
    <button class="btn-cart" @click="handleCartClick">
        <PhShoppingBagOpen v-if="!isActive" :size="size" :color="color" :weight="weight"  />
        <PhShoppingBagOpen v-else :size="size" color="#333333" weight="fill"  />
    </button>
</template>

<style scoped>
.btn-cart {
    padding: .4rem;
    border-radius: .8rem;
    border: 1px solid #333333;
}
</style>