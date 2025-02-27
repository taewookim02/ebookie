<script setup>
import ReviewBody from './ReviewBody.vue';
import ReviewStats from './ReviewStats.vue';
import ReviewForm from './ReviewForm.vue';
import { customAxios } from '@/plugins/axios';
import { useToast } from 'vue-toastification';
import { ref } from 'vue';

// Use
const toast = useToast();

// State
const props = defineProps({
    detailDto: Object
});
const editingReviewId = ref(null);


// Actions
const handleReviewSave = async (reviewContent) => {
    // check if rating is set
    if (reviewContent.rating === 0) {
        toast.error("별점을 선택해주세요.");
        return;
    }

    const res = await customAxios.post("/api/v1/reviews", {
        content: reviewContent.content,
        rating: reviewContent.rating,
        productId: props.detailDto.id
    });
    const reviewDto = res.data;

    props.detailDto.reviews.push(reviewDto);
};  

const handleReviewEdit = async (reviewId, editContent) => {
    try {
        const res = await customAxios.patch(`/api/v1/reviews/${reviewId}`, {
            content: editContent
        });
        if (res.status < 300) {
            props.detailDto.reviews = props.detailDto.reviews.map(review => review.id === reviewId ? res.data : review);
            toast.success("리뷰가 수정되었습니다.");
            editingReviewId.value = null;
        }
    } catch (error) {
        console.log(error);
    }
};

const handleReviewDelete = async (reviewId) => {
    try {
        const res = await customAxios.delete(`/api/v1/reviews/${reviewId}`);
        if (res.status < 300) {
            props.detailDto.reviews = props.detailDto.reviews.filter(review => review.id !== reviewId);
            toast.success("리뷰가 삭제되었습니다.");
            editingReviewId.value = null;
        }
    } catch (error) {
        console.log(error);
    }
};

const handleEditOpen = (reviewId) => {
    editingReviewId.value = reviewId;
}

const handleEditCancel = () => {
    editingReviewId.value = null;
}

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


        <template v-for="review in detailDto.reviews">
            <ReviewBody 
                :review="review" 
                @edit="handleReviewEdit" 
                @editOpen="handleEditOpen"
                @editCancel="handleEditCancel"
                @delete="handleReviewDelete" 
                :is-editing="editingReviewId === review.id"
            />
        </template>
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