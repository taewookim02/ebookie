<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { PhPencil, PhPencilSimpleLine, PhStar, PhTrash } from '@phosphor-icons/vue';

// TODO: defineProps

const handleEdit = () => {
    console.log("handleEdit");
};

const handleDelete = () => {
    console.log("handleDelete");
};

</script>

<template>
    <div class="review-body">
        <div class="review-header">
            <strong class="review-header__writer">test@example.com</strong>
            <!-- TODO: v-if writer == member -->
            <div class="review-header__controls">
                <ActionButton @action="handleEdit">삭제</ActionButton>
                <ActionButton @action="handleDelete">수정</ActionButton>
            </div>
        </div>

        <div class="review-stars">
            <PhStar :size="16" color="blue" weight="fill" />
            <PhStar :size="16" color="blue" weight="fill" />
            <PhStar :size="16" color="blue" weight="fill" />
            <PhStar :size="16" color="blue" weight="fill" />
            <PhStar :size="16" color="blue" weight="regular" />
        </div>

        <div class="review-info">
            <small class="text-muted review-info__date">2024년 11월 2일 작성됨</small>
        </div>

        <div class="review-content">
            방대한 내용을 담으면서 쉽게 풀어쓴게 정말 잘 만든 책입니다
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