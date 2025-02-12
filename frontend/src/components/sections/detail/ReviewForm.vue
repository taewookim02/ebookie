<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { computed, ref } from 'vue';


// state

const rawContent = ref("");

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

const handleSave = () => {
    console.log("handleSave", reviewContent.value);
};
</script>

<template>
    <!-- TODO: if logged in -->
    <form class="review-form" @submit.prevent="handleSave">
        <h3>리뷰 작성</h3>
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


.char-count {
    text-align: end;
}
</style>