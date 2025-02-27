<script setup>
import ActionButton from '@/components/common/ActionButton.vue';
import { computed, ref, defineProps } from 'vue';
import { PhStar } from '@phosphor-icons/vue';

// State
const props = defineProps({
    detailDto: Object
});
const rawContent = ref("");
const rating = ref(0);

// Emit
const emit = defineEmits(["save"]);


// Actions
const handleSave = () => {
    emit("save", {
        content: reviewContent.value,
        rating: rating.value
    });
};

const handleStarClick = (starIndex) => {
    rating.value = starIndex;
};

// Computed
const reviewContent = computed({
    get: () => rawContent.value,
    set: (value) => {
        rawContent.value = value.substring(0, 50);
    }
});
const charactersLeft = computed(() => {
    const char = rawContent.value.length;
    const limit = 50;

    return `${char} / ${limit}`;
});

const computedStars = computed(() => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
        stars.push(i <= rating.value);
    }
    return stars;
});
</script>

<template>
    <form class="review-form" @submit.prevent="handleSave">
        <h3>리뷰 작성</h3>
        <div class="review-form__rating">
            <PhStar 
                v-for="(star, index) in computedStars" 
                :key="index"
                :size="24" 
                :color="star ? 'blue' : '#ccc'" 
                :weight="'fill'"
                class="star-icon"
                @click="handleStarClick(index + 1)"
            />
        </div>
        <div class="review-form__container">
            <textarea v-model="reviewContent" name="content" id="review-content"
                class="review-content form-control w-100" rows="3" maxlength="50"></textarea>
            <ActionButton class="btn-action" type="submit">등록</ActionButton>
            <small class="text-muted char-count">{{ charactersLeft }}</small>
        </div>
    </form>
</template>

<style scoped>
.review-form,
.input-group {
    display: flex;
    flex-direction: column;
}

.review-form {
    gap: 2.4rem;
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

.star-icon {
    cursor: pointer;
}

.char-count {
    text-align: end;
}
</style>