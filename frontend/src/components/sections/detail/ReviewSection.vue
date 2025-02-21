<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import ReviewBody from './ReviewBody.vue';
import ReviewStats from './ReviewStats.vue';
import ReviewForm from './ReviewForm.vue';

const props = defineProps({
    detailDto: Object
});

const handleAction = () => {
    console.log("handleAction");
    // modal open
};

</script>

<template>
    <section class="review">
        <div class="review-header">
            <h3>회원리뷰</h3>
        </div>

        <!-- 리뷰  -->
        <ReviewStats :detail-dto="detailDto" />

        <!-- 리뷰 작성 -->
        <ReviewForm />


        <!-- TODO: v-for each reviews -->
        <template v-for="review in detailDto.reviews">
            <ReviewBody :review="review" />
        </template>
        <!-- <ReviewBody /> -->
    </section>
</template>

<style scoped>
.review {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
    padding-bottom: 6.4rem;
    border-bottom: 1px solid #ccc;
}

.review-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.review-action-btn {
    padding: 8px 16px;
}
</style>