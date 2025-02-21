<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { formatYYYYMMDDKr } from '@/helper/format';
import { useMemberStore } from '@/store/memberStore';
import { useTokenStore } from '@/store/tokenStore';
import { PhStar } from '@phosphor-icons/vue';
import { computed, ref } from 'vue';

// state
const props = defineProps({
    review: Object,
    isEditing: Boolean
})
const memberStore = useMemberStore();
const emit = defineEmits(['edit', 'editOpen', 'editCancel', 'delete']);
const editContent = ref(props.review.content);

// actions
const handleEditSubmit = () => {
    emit('edit', props.review.id, editContent.value);
};
const handleDelete = () => {
    emit('delete', props.review.id);
};

const handleEdit = () => {
    // props.isEditing.valueo = true;
    emit('editOpen', props.review.id);
    // console.log("handleEdit");
}
const handleEditCancel = () => {
    // props.isEditing.value = false;
    console.log("handleEditCancel");

    editContent.value = props.review.content;
    emit('editCancel');
}

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

const charactersLeft = computed(() => {
    const char = editContent.value.length;
    const limit = 50;
    return `${char} / ${limit}`;
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

        <div class="review-content" v-if="!isEditing">
            {{ review.content }}
        </div>

        <div v-if="isEditing" class="review-form__container">
            <textarea 
                v-model="editContent" 
                class="review-content form-control w-100" 
                rows="3" 
                maxlength="50"
            ></textarea>
            <div>
                <ActionButton class="btn-action" @click="handleEditSubmit">저장</ActionButton>
                <ActionButton class="btn-action" @click="handleEditCancel">취소</ActionButton>
            </div>
            <small class="text-muted char-count">{{ charactersLeft }}</small>
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

.review-form__container {
    display: grid;
    grid-template-columns: 10fr 2fr;
    column-gap: .8rem;
}

.review-content {
    resize: none;
    font-size: 1.4rem;

    &:focus {
        outline: none;
        box-shadow: none;
        border: 1px solid #ccc;
    }
}

.char-count {
    text-align: end;
}
</style>