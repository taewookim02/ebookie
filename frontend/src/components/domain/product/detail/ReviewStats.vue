<script setup>
import { PhStar } from '@phosphor-icons/vue';
import { computed } from 'vue';

// State
const props = defineProps({
    detailDto: Object
});

// Computed
const computedAvgRating = computed(() => {
    if (!props.detailDto.reviews?.length) return 0;
    const sum = props.detailDto.reviews.reduce((acc, review) => acc + review.rating, 0);
    return (sum / props.detailDto.reviews.length).toFixed(1);
});

const computedStars = computed(() => {
    const avg = parseFloat(computedAvgRating.value);
    const stars = [];
    for (let i = 1; i <= 5; i++) {
        stars.push(i <= avg);
    }
    return stars;
});

const computedRatingPercentages = computed(() => {
    if (!props.detailDto.reviews?.length) return [0, 0, 0, 0, 0];

    const counts = [0, 0, 0, 0, 0]; // 0번째 = 1 star, 4번째 = 5 stars
    const total = props.detailDto.reviews.length;

    props.detailDto.reviews.forEach(review => {
        const index = Math.floor(review.rating) - 1;
        counts[index]++;
    });

    return counts.map(count => ((count / total) * 100).toFixed(0));
});
</script>

<template>
    <div class="review-container">
        <div class="review-container__stars">
            <span class="stars-heading">{{ detailDto.reviews?.length }}명의 eBookie회원이 평가한 평균별점</span>
            <div class="stars-main">
                <div class="stars-main__icons">
                    <PhStar v-for="(filled, index) in computedStars" :key="index" :size="32"
                        :color="filled ? 'var(--primary-color)' : 'var(--border-color)'" weight="fill" :class="{ 'empty-star': !filled }" />
                </div>
                <div class="stars-main__numbers">
                    <span class="rating-avg">{{ computedAvgRating }}</span>
                    <span class="rating-total text-muted"> / 5.0</span>
                </div>
            </div>
        </div>
        <div class="review-container__percentage">
            <!-- 5 stars -->
            <div class="percetage-stars__row">
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
            </div>
            <div class="progress" role="progressbar" :aria-valuenow="computedRatingPercentages[4]" aria-valuemin="0"
                aria-valuemax="100">
                <div class="progress-bar" :style="`width: ${computedRatingPercentages[4]}%`">{{ computedRatingPercentages[4] }}%</div>
            </div>

            <!-- 4 stars -->
            <div class="percetage-stars__row">
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
            </div>
            <div class="progress" role="progressbar" :aria-valuenow="computedRatingPercentages[3]" aria-valuemin="0"
                aria-valuemax="100">
                <div class="progress-bar" :style="`width: ${computedRatingPercentages[3]}%`">{{ computedRatingPercentages[3] }}%</div>
            </div>

            <!-- 3 stars -->
            <div class="percetage-stars__row">
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
            </div>
            <div class="progress" role="progressbar" :aria-valuenow="computedRatingPercentages[2]" aria-valuemin="0"
                aria-valuemax="100">
                <div class="progress-bar" :style="`width: ${computedRatingPercentages[2]}%`">{{ computedRatingPercentages[2] }}%</div>
            </div>

            <!-- 2 stars -->
            <div class="percetage-stars__row">
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
            </div>
            <div class="progress" role="progressbar" :aria-valuenow="computedRatingPercentages[1]" aria-valuemin="0"
                aria-valuemax="100">
                <div class="progress-bar" :style="`width: ${computedRatingPercentages[1]}%`">{{ computedRatingPercentages[1] }}%</div>
            </div>

            <!-- 1 star -->
            <div class="percetage-stars__row">
                <PhStar :size="16" weight="fill" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
                <PhStar :size="16" color="var(--primary-color)" />
            </div>
            <div class="progress" role="progressbar" :aria-valuenow="computedRatingPercentages[0]" aria-valuemin="0"
                aria-valuemax="100">
                <div class="progress-bar" :style="`width: ${computedRatingPercentages[0]}%`">{{ computedRatingPercentages[0] }}%</div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.review-container {
    display: flex;
    flex-direction: column;
    gap: 3.2rem;
    padding: 2.4rem;
    border: 1px solid var(--border-color);
    border-radius: .6rem;
    background-color: var(--background-color);
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
    color: var(--text-color);
}

.stars-main {
    display: flex;
    justify-content: space-around;
    align-items: center;
}

.stars-main__numbers {
    font-size: 1.7rem;
    color: var(--text-secondary-color);
}

.rating-avg {
    font-size: 2.4rem;
    font-weight: 700;
    color: var(--text-color);
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
    height: 2.4rem;
    border-radius: .4rem;
}

.progress {
    background-color: var(--surface-color);
    -webkit-box-shadow: none;
    box-shadow: none;
}

.progress-bar {
    background-color: var(--primary-color);
    color: var(--background-color);
    display: flex;
    align-items: center;
    padding: 0 .8rem;
    font-weight: 500;
    transition: width 0.3s ease;
}
</style>