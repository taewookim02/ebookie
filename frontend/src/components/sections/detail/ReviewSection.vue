<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import { PhPencilSimpleLine, PhStar, PhStarHalf } from '@phosphor-icons/vue';
const handleAction = () => {
    console.log("handleAction");
};

</script>

<template>
    <section class="review">
        <div class="review-header">
            <h3>회원리뷰</h3>
            <ActionButton class="review-action-btn" :is-active="true" @action="handleAction">
                리뷰 작성
            </ActionButton>
        </div>
        <div class="review-container">
            <div class="review-container__stars">
                <span class="stars-heading">2명의 eBookie회원이 평가한 평균별점</span>
                <div class="stars-main">
                    <div class="stars-main__icons">
                        <PhStar :size="32" weight="fill" color="blue" />
                        <PhStar :size="32" weight="fill" color="blue" />
                        <PhStar :size="32" weight="fill" color="blue" />
                        <PhStar :size="32" weight="fill" color="blue" />
                        <PhStarHalf :size="32" weight="fill" color="blue" />
                    </div>
                    <div class="stars-main__numbers">
                        <span class="rating-avg">9.0</span>
                        <span class="rating-total text-muted"> / 10.0</span>
                    </div>
                </div>
            </div>
            <div class="review-container__percentage">
                <div class="percetage-stars__row">
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                </div>
                <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="1" aria-valuemin="1"
                    aria-valuemax="100">
                    <div class="progress-bar" style="width: 0%">0%</div>
                </div>
                <div class="percetage-stars__row">
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" color="blue" />
                </div>
                <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="25" aria-valuemin="0"
                    aria-valuemax="100">
                    <div class="progress-bar" style="width: 25%"></div>
                </div>
                <div class="percetage-stars__row">
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" color="blue" />
                    <PhStar :size="16" color="blue" />
                </div>
                <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="50" aria-valuemin="0"
                    aria-valuemax="100">
                    <div class="progress-bar" style="width: 50%"></div>
                </div>
                <div class="percetage-stars__row">
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" color="blue" />
                    <PhStar :size="16" color="blue" />
                    <PhStar :size="16" color="blue" />
                </div>
                <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0"
                    aria-valuemax="100">
                    <div class="progress-bar" style="width: 75%"></div>
                </div>
                <div class="percetage-stars__row">
                    <PhStar :size="16" weight="fill" color="blue" />
                    <PhStar :size="16" color="blue" />
                    <PhStar :size="16" color="blue" />
                    <PhStar :size="16" color="blue" />
                    <PhStar :size="16" color="blue" />
                </div>
                <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="100"
                    aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar" style="width: 100%"></div>
                </div>
            </div>
        </div>
    </section>
</template>

<style scoped>
.review {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
}

.review-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.review-action-btn {
    padding: 8px 16px;
}

.review-container {
    display: flex;
    flex-direction: column;
    gap: 3.2rem;
    padding: 2.4rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    /* display: grid;
    grid-template-columns: 1fr 1fr; */
}

.review-container__stars {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
}

.stars-heading {
    text-align: center;
    font-weight: 700;
    font-size: 1.8rem;
}

.stars-main {
    display: flex;
    justify-content: space-around;
    align-items: center;
}

.stars-main__numbers {
    font-size: 1.7rem;
}

.rating-avg {
    font-size: 2.4rem;
    font-weight: 700;
}

/* 퍼센티지 */
.review-container__percentage {
    display: grid;
    grid-template-columns: min-content 1fr;
    grid-template-rows: repeat(5, 1fr);
    gap: .8rem;
    padding: 0 4rem;
    column-gap: 1.6rem;
}


.percetage-stars__row {
    align-self: center;
    display: flex;
    justify-content: center;
}


.progress,
.progress-stacked {
    height: auto;
}

.progress {
    background-color: #ccc;
    -webkit-box-shadow: none;
    box-shadow: none;
}
</style>