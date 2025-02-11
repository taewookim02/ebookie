<script setup>
import ActionButton from '@/components/shared/ActionButton.vue';
import LikeButton from '@/components/shared/LikeButton.vue';
import { PhStar } from '@phosphor-icons/vue';
import { ref } from 'vue';

// state
const props = defineProps({
    productId: String
});
console.log(props.productId);

const isActive = ref(true);


const handleSaveClick = () => {
    console.log("handleClick");
};
const handleLike = () => {
    console.log("handleLike!");
};
const handleCartClick = () => {
    console.log("handleClick");
};
const handleBuyClick = () => {
    console.log("handleClick");
};

</script>

<template>
    <!-- 히어로 -->
    <!-- 히어로__정보 -->
    <!-- 히어로__이미지 -->
    <!-- 정보 -->
    <!-- 액션 -->
    <section class="hero">
        <div class="hero__image">
            <img src="@/assets/images/product/L (25).jpeg" alt="">
        </div>
        <div class="hero__info">
            <div class="info-header">
                <h1 class="info-header__title">업무시간을 반으로 줄이는 AI 활용법</h1>
                <div class="info-header__audit text-muted">
                    <span>김철수 저</span>
                    <span class="spacer">|</span>
                    <span>위키북스</span>
                    <span class="spacer">|</span>
                    <span> 2020년 07월 09일</span>
                </div>
                <div class="info-header__review">
                    <span class="info-header__review--icons">
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                        <PhStar :size="16" color="blue" weight="fill" />
                    </span>
                    <span class="info-header__review--avg">9.2</span>
                    <span class="spacer text-muted">|</span>
                    <span>
                        <RouterLink to="#" class="link-dark">회원리뷰(24건)</RouterLink>
                    </span>
                </div>
            </div>
            <div class="info-bottom pt-4">
                <div class="info-bottom__price">
                    <span>정가 <span class="price text-muted">12,600원</span></span>
                    <span>판매가 <strong class="price">12,600원</strong></span>
                </div>
                <div class="info-bottom__action">
                    <LikeButton @like="handleLike" :is-active="isActive" />
                    <ActionButton text="찜하기" @action="handleSaveClick"/>
                    <ActionButton text="장바구니" @action="handleCartClick"/>
                    <ActionButton text="구매하기" @action="handleBuyClick" :is-active="true"/>
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
</style>