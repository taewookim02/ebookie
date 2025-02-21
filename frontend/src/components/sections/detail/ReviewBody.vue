<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { formatYYYYMMDDKr } from '@/helper/format';
import { useMemberStore } from '@/store/memberStore';
import { useTokenStore } from '@/store/tokenStore';
import { PhStar } from '@phosphor-icons/vue';
import { computed } from 'vue';

// state
const props = defineProps({
    review: Object
})
const memberStore = useMemberStore();
const emit = defineEmits(['edit', 'delete']);

// actions
const handleEdit = () => {
    emit('edit', props.review.id);
};
const handleDelete = () => {
    emit('delete', props.review.id);
};

const isOwner = computed(() => {
    return memberStore.getMemberEmail === props.review.writerEmail;
})

const computedStars = computed(() => {
    const avg = parseFloat(props.review.rating);
    const stars = [];
    for (let i = 1; i <= 5; i++) {
        stars.push(i <= avg); // true if the star should be filled
    }
    return stars;
});
</script>

<template>
    <div class="review-body">
        <div class="review-header">
            <strong class="review-header__writer">{{ review.writerEmail }}</strong>
            <div class="review-header__controls" v-if="isOwner">
                <ActionButton @action="handleDelete">삭제</ActionButton>
                <ActionButton @action="handleEdit">수정</ActionButton>
            </div>
        </div>

        <div class="review-stars">
            <PhStar v-for="star in computedStars" :size="16" :color="star ? 'blue' : '#ccc'" :weight="'fill'" />
        </div>

        <div class="review-info">
            <small class="text-muted review-info__date">{{ formatYYYYMMDDKr(review.createdAt) }} 작성됨</small>
        </div>

        <div class="review-content">
            {{ review.content }}
        </div>
    </div>
</template>

<style scoped>
.review-body {
    padding: 0 2.4rem;
}

.review-header {
    display: flex;
    justify-content: space-between;
}

.review-header__controls {
    display: flex;
    gap: .8rem;
}

.review-stars {
    display: flex;
    align-items: center;
}

.review-content {
    padding: .8rem 0;
}
</style>