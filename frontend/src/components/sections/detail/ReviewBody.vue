<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { formatYYYYMMDDKr } from '@/helper/format';
import { useMemberStore } from '@/store/memberStore';
import { useTokenStore } from '@/store/tokenStore';
import { PhPencil, PhPencilSimpleLine, PhStar, PhTrash } from '@phosphor-icons/vue';
import { computed, onMounted } from 'vue';

// state
const props = defineProps({
    review: Object
})
const store = useTokenStore();
const memberStore = useMemberStore();

// actions
const handleEdit = () => {
    console.log("handleEdit");
};
const handleDelete = () => {
    console.log("handleDelete");
};

const isOwner = computed(() => {
    return memberStore.getMemberEmail === props.review.writerEmail;
})
</script>

<template>
    <div class="review-body">
        <div class="review-header">
            <strong class="review-header__writer">{{ review.writerEmail }}</strong>
            <!-- TODO: v-if writer == member -->
            <div class="review-header__controls" v-if="isOwner">
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