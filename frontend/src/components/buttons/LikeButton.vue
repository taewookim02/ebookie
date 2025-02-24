<script setup>
import { PhHeart } from '@phosphor-icons/vue';

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


const emit = defineEmits(['like']);

const handleLikeClick = () => {
    emit('like');
};
</script>

<template>
    <button class="btn-like" @click="handleLikeClick">
        <PhHeart v-if="!isActive" :size="size" :color="color" :weight="weight"  />
        <PhHeart v-else :size="size" color="#FE4D4D" weight="fill"  />
    </button>
</template>

<style scoped>
.btn-like {
    padding: .4rem;
    border-radius: .8rem;
    border: 1px solid #333333;

}
</style>