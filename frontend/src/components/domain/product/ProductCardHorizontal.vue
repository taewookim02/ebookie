<script setup>
import { PhStar } from "@phosphor-icons/vue";
import { computed } from "vue";

// State
const props = defineProps({
    rank: Number,
    imgSrc: String,
    title: String,
    author: String,
    publisher: String,
    price: Number,
    ratingAvg: Number,
    ratingCnt: Number,
    id: Number,
});

// Actions
const getImageUrl = () => {
    return new URL(`/src/assets/images/product/${props.imgSrc}`, import.meta.url);
}
</script>

<template>
    <div class="features__item">
        <RouterLink :to="`/products/${id}`"><img :src="getImageUrl()" class="features__img" alt="feature img" />
        </RouterLink>
        <div class="features__item-info">
            <span class="features__item--rank">
                {{ rank }}
            </span>
            <div class="features__item--title pt-3">
                <RouterLink :to="`/products/${id}`">{{ title }}</RouterLink>
            </div>
            <div class="features__item--author py-1 text-muted">
                <div class="author-name"><small>{{ author }} 저</small></div>
                <div class="publisher"><small>{{ publisher }}</small></div>
            </div>
            <div class="features__item--rating d-flex align-items-center pt-1 gap-1" v-if="ratingAvg && ratingCnt">
                <PhStar :size="16" fill="#325dfa" weight="fill" />
                <span class="rating-avg">{{ ratingAvg }}</span>
                <small class="rating-cnt text-muted">({{ ratingCnt }})</small>
            </div>
        </div>
    </div>
</template>

<style scoped>
.features__item {
    display: grid;
    grid-template-columns: 3fr 5fr;
    align-items: start;
    gap: 1.6rem;
}

.features__item-info {
    display: flex;
    flex-direction: column;
    align-items: start;
}

.features__item--rank {
    font-weight: 700;
    color: blue;
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