<script setup>
import featureImage from "@/assets/feature-item.png";
import { PhStar } from "@phosphor-icons/vue";
import { computed } from "vue";
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

const formattedCurrency = computed(() => {
    return props.price ? `${new Intl.NumberFormat().format(props.price)}원` : "무료";
})
</script>

<template>
    <div class="features__item">
        <RouterLink :to="`/product/${id}`"><img :src="imgSrc || featureImage" class="features__img" alt="feature img" />
        </RouterLink>
        <div class="features__item--title pt-3">
            <RouterLink :to="`/product/${id}`">{{ title }}</RouterLink>
        </div>
        <div class="features__item--author py-1 text-muted">
            <div class="author-name"><small>{{ author }} 저</small></div>
            <div class="publisher"><small>{{ publisher }}</small></div>
        </div>
        <div class="features__item--price"><strong>{{ formattedCurrency }}</strong></div>
        <div class="features__item--rating d-flex align-items-center pt-1 gap-1" v-if="ratingAvg && ratingCnt">
            <PhStar :size="16" fill="#325dfa" weight="fill" />
            <span class="rating-avg">{{ ratingAvg }}</span>
            <small class="rating-cnt text-muted">({{ ratingCnt }})</small>
        </div>
    </div>
</template>

<style scoped>
.features__item {
    display: flex;
    flex-direction: column;
    /* gap: .8rem; */
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