<script setup>
import { PhBookmarkSimple } from '@phosphor-icons/vue';

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


const emit = defineEmits(['save']);

const handleSaveClick = () => {
    emit('save');
};
</script>

<template>
    <button class="btn-save" @click="handleSaveClick">
        <PhBookmarkSimple v-if="!isActive" :size="size" :color="color" :weight="weight"  />
        <PhBookmarkSimple v-else :size="size" color="#333333" weight="fill"  />
    </button>
</template>

<style scoped>
.btn-save {
    padding: .4rem;
    border-radius: .8rem;
    border: 1px solid #333333;
}
</style>