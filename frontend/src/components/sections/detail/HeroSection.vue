<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import LikeButton from '@/components/shared/LikeButton.vue';
import SaveButton from '@/components/shared/SaveButton.vue';
import { PhStar } from '@phosphor-icons/vue';
import { computed, onMounted, ref } from 'vue';
import { VueSpinner } from "vue3-spinners";

// state
const props = defineProps({
    detailDto: Object
});
// const isActive = ref(true);
const SERVER_URL = "http://localhost:8080";



// actions
const emit = defineEmits(["scrollToReview", "save", "like", "cart", "buy"]);
const handleReviewClick = () => {
    emit("scrollToReview");
}
const handleSaveClick = () => emit("save");

const handleLikeClick = () => {
    emit("like");
};
const handleCartClick = () => {
    emit("cart");
};
const handleBuyClick = () => {
    emit("buy");
};


// 이미지 경로
const imageUrl = computed(() => {
    if (props.detailDto && props.detailDto.images && props.detailDto.images.length > 0) {
        return `${SERVER_URL}/api/v1/image?name=${props.detailDto.images[0].fileName}`;
    }
    return ''; // 디폴트값
});

// 저자 이름 목록
const computedAuthor = computed(() => {
    const nameList = [];
    props.detailDto.authors?.forEach(authorDto => {
        nameList.push(authorDto.name);
    })
    return nameList.join(", ");
});

// 출판일자
const computedPublishedDate = computed(() => {
    if (props.detailDto?.publishedDate) {
        const date = new Date(props.detailDto.publishedDate);
        return new Intl.DateTimeFormat('ko-KR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        }).format(date); // to YYYY년 MM월 DD일
    }
    return '';
});

// 평균 리뷰 점수
const computedReviewAvg = computed(() => {
    let total = 0;
    props.detailDto.reviews?.forEach(review => {
        total += review.rating;
    })
    return total ? (total / props.detailDto.reviews?.length).toFixed(1) : 0;
});

// 판매가
// TODO: is determining price on frontend ideal?
const computedSellingPrice = computed(() => {
    return (props.detailDto.price - (props.detailDto.price * props.detailDto.discountRate / 100)).toLocaleString();
})


// 
const computedStars = computed(() => {
    const avg = parseFloat(computedReviewAvg.value);
    const stars = [];
    for (let i = 1; i <= 5; i++) {
        stars.push(false); // TODO: fill based on avg
    }
    return stars;
});



</script>

<template>
    <!-- 히어로 -->
    <!-- 히어로__정보 -->
    <!-- 히어로__이미지 -->
    <!-- 정보 -->
    <!-- 액션 -->
    <section class="hero">
        <div class="hero__image" :class="{ 'loading': !imageUrl }">
            <img :src="imageUrl" alt="도서 썸네일" v-if="imageUrl">
            <VueSpinner size="64" color="#ccc" margin="auto" v-else />
        </div>
        <div class="hero__info">
            <div class="info-header">
                <h1 class="info-header__title">{{ detailDto.name }}</h1>
                <div class="info-header__audit text-muted">
                    <span>{{ computedAuthor }} 저</span>
                    <span class="spacer">|</span>
                    <span>{{ detailDto.publisherName }}</span>
                    <span class="spacer">|</span>
                    <span>{{ computedPublishedDate }}</span>
                </div>
                <div class="info-header__review">
                    <!-- TODO: review rating -->
                    <!-- <span class="info-header__review--icons">
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                    </span> -->
                    <!-- FIXME: buggy -->
                    <div class="info-header__review--icons">
                        <PhStar v-for="(filled, index) in computedStars" :key="index" :size="16"
                            :color="filled ? 'blue' : '#ccc'" weight="fill" :class="{ 'empty-star': !filled }" />
                    </div>

                    <span class="info-header__review--avg">{{ computedReviewAvg }}</span>
                    <span class="spacer text-muted">|</span>
                    <span>
                        <a to="#" class="link-dark" @click="handleReviewClick">회원리뷰({{ detailDto.reviews?.length
                            }}건)</a>
                    </span>
                </div>
            </div>
            <div class="info-bottom pt-4">
                <div class="info-bottom__price">
                    <span>정가 <span class="price text-muted price-original">{{ detailDto.price?.toLocaleString()
                    }}원</span></span>
                    <span>판매가 <strong class="price">{{ computedSellingPrice }}원</strong> <span
                            v-if="detailDto.discountRate" class="badge text-bg-danger">{{ detailDto.discountRate
                            }}%</span></span>
                </div>
                <div class="info-bottom__action">
                    <LikeButton @like="handleLikeClick" :is-active="detailDto.isLiked" />
                    <SaveButton @save="handleSaveClick" :is-active="detailDto.isSaved" />
                    <!-- <ActionButton @action="handleSaveClick">찜하기</ActionButton> -->
                    <ActionButton @action="handleCartClick">장바구니</ActionButton>
                    <ActionButton @action="handleBuyClick" :is-active="true">구매하기</ActionButton>
                </div>
            </div>

        </div>
    </section>
</template>

<style scoped>
.hero {
    display: grid;
    grid-template-columns: 2fr 6fr;
    gap: 1.6rem;
}

.hero__info {
    align-self: center;
}

.loading {
    display: grid;
    place-items: center;
}

.info-header__audit,
.info-header__review {
    display: flex;
    gap: 4px;
    align-items: center;
}

.info-header {
    display: flex;
    flex-direction: column;
    gap: 0.8rem;
}

.link-dark {
    cursor: pointer;
}

.spacer {
    font-size: 1.4rem;
}

.info-bottom {
    display: flex;
    flex-direction: column;
    gap: 1.6rem;
}

.info-bottom__price {
    display: flex;
    flex-direction: column;
    gap: 0.8rem;
}

strong.price {
    font-size: 2.4rem;
    /* color:  */
}

.info-bottom__action {
    display: flex;
    gap: 0.8rem;
}

.price-original {
    text-decoration: line-through;
}

.empty-star {
    color: #ccc;
}
</style>