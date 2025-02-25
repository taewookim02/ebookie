<script setup>
import { PhBookmarkSimple } from '@phosphor-icons/vue';

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


const emit = defineEmits(['save']);

const handleSaveClick = () => {
    emit('save');
};
</script>

<template>
    <button class="btn-save" @click="handleSaveClick">
        <PhBookmarkSimple v-if="!isActive" :size="size" :color="color" :weight="weight" />
        <PhBookmarkSimple v-else :size="size" color="var(--primary-color)" weight="fill" />
    </button>
</template>

<style scoped>
.btn-save {
    padding: .8rem;
    border-radius: .6rem;
    border: 1px solid var(--border-color);
    background-color: var(--background-color);
    cursor: pointer;
    transition: all 0.2s ease;
}

.btn-save:hover {
    border-color: var(--primary-hover);
    background-color: var(--background-color);
}

.btn-save:active {
    border-color: var(--primary-active);
    background-color: var(--surface-color);
}
</style>