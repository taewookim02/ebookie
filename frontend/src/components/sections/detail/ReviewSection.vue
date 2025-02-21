<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import ReviewBody from './ReviewBody.vue';
import ReviewStats from './ReviewStats.vue';
import ReviewForm from './ReviewForm.vue';
import { customAxios } from '@/plugins/axios';
import { useToast } from 'vue-toastification';
const toast = useToast();

const props = defineProps({
    detailDto: Object
});

const handleReviewSave = async (reviewContent) => {
    // check if rating is set
    if (reviewContent.rating === 0) {
        toast.error("별점을 선택해주세요.");
        return;
    }

    // console.log(props.detailDto.id);
    console.log(reviewContent);
    const res = await customAxios.post("/api/v1/reviews", {
        content: reviewContent.content,
        rating: reviewContent.rating,
        productId: props.detailDto.id
    });
    const reviewDto = res.data;

    props.detailDto.reviews.push(reviewDto);
};  

const handleReviewEdit = async (reviewId) => {

    console.log(reviewId);
};

const handleReviewDelete = async (reviewId) => {
    try {
        const res = await customAxios.delete(`/api/v1/reviews/${reviewId}`);
        if (res.status < 300) {
            props.detailDto.reviews = props.detailDto.reviews.filter(review => review.id !== reviewId);
            toast.success("리뷰가 삭제되었습니다.");
        }
    } catch (error) {
        console.log(error);
    }
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
        <ReviewForm :detail-dto="detailDto" @save="handleReviewSave" />


        <!-- TODO: v-for each reviews -->
        <template v-for="review in detailDto.reviews">
            <ReviewBody :review="review" @edit="handleReviewEdit" @delete="handleReviewDelete" />
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