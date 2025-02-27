<script setup>
import { PhStar } from "@phosphor-icons/vue";
import { computed } from "vue";

// State
const props = defineProps({
    imgSrc: String,
    title: String,
    author: String,
    publisher: String,
    price: Number,
    ratingAvg: Number,
    ratingCnt: Number,
    id: Number,
});

// Computed
const formattedCurrency = computed(() => {
    return props.price ? `${new Intl.NumberFormat().format(props.price)}원` : "무료";
});

// const SERVER_URL = "http://localhost:8080";
const SERVER_URL = window.location.origin;

// 이미지 경로
const imageUrl = computed(() => {
    if (props.imgSrc) {
        return `${SERVER_URL}/api/v1/image?name=${props.imgSrc}`;
    }
    return ''; // 디폴트값
});



</script>

<template>
    <div class="features__item">
        <RouterLink :to="`/products/${id}`">
            <img :src="imageUrl" class="features__img" alt="feature img" />
        </RouterLink>
        <div class="features__item--title pt-3">
            <RouterLink :to="`/products/${id}`">{{ title }}</RouterLink>
        </div>
        <div class="features__item--author py-1 text-muted">
            <div class="author-name"><small>{{ author }} 저</small></div>
            <div class="publisher"><small>{{ publisher }}</small></div>
        </div>
        <div class="features__item--price"><strong>{{ formattedCurrency }}</strong></div>
        <div class="features__item--rating d-flex align-items-center pt-1 gap-1" v-if="ratingAvg && ratingCnt">
            <PhStar :size="16" fill="#325dfa" weight="fill" />
            <span class="rating-avg">{{ ratingAvg.toFixed(1) }}</span>
            <small class="rating-cnt text-muted">({{ ratingCnt }})</small>
        </div>
    </div>
</template>

<style scoped>
.features__item {
    display: flex;
    flex-direction: column;
    align-items: start;
}

.features__img {
    width: 100%;
    height: auto;
    object-fit: cover;
    border-radius: 8px;
}

.features__item--title {
    font-size: 1.4rem;
    font-weight: 700;
    text-align: start;

    & a {
        color: inherit;
        font-weight: inherit;
        text-decoration: none;

        &:hover {
            text-decoration: underline;
        }
    }
}

.features__item--author {
    text-align: start;
    font-size: 1.3rem;
}

.rating-avg {
    font-weight: 700;
}
</style>