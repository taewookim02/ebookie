<script setup>
import { PhShoppingBagOpen } from '@phosphor-icons/vue';

// State
const props = defineProps({
    size: {
        type: Number,
        default: 32
    },
    color: {
        type: String,
        default: "var(--text-color)",
    },
    weight: {
        type: String,
        default: "light"
    },
    isActive: Boolean
});

// Emit
const emit = defineEmits(['cart']);

// Actions
const handleCartClick = () => {
    emit('cart');
};
</script>

<template>
    <button class="btn-cart" @click="handleCartClick">
        <PhShoppingBagOpen v-if="!isActive" :size="size" :color="color" :weight="weight" />
        <PhShoppingBagOpen v-else :size="size" color="var(--primary-color)" weight="fill" />
    </button>
</template>

<style scoped>
.btn-cart {
    padding: .8rem;
    border-radius: .6rem;
    border: 1px solid var(--border-color);
    background-color: var(--background-color);
    cursor: pointer;
    transition: all 0.2s ease;
}

.btn-cart:hover {
    border-color: var(--primary-hover);
    background-color: var(--background-color);
}

.btn-cart:active {
    border-color: var(--primary-active);
    background-color: var(--surface-color);
}
</style>