<script setup>
import { PhHeart } from '@phosphor-icons/vue';

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
const emit = defineEmits(['like']);

// Actions
const handleLikeClick = () => {
    emit('like');
};
</script>

<template>
    <button class="btn-like" @click="handleLikeClick">
        <PhHeart v-if="!isActive" :size="size" :color="color" :weight="weight" />
        <PhHeart v-else :size="size" color="var(--accent-color)" weight="fill" />
    </button>
</template>

<style scoped>
.btn-like {
    padding: .8rem;
    border-radius: .6rem;
    border: 1px solid var(--border-color);
    background-color: var(--background-color);
    cursor: pointer;
    transition: all 0.2s ease;
}

.btn-like:hover {
    border-color: var(--accent-hover);
    background-color: var(--background-color);
}

.btn-like:active {
    border-color: var(--accent-active);
    background-color: var(--surface-color);
}
</style>