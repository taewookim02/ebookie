<script setup lang="ts">
import FeatureSectionItem from '../shared/FeatureSectionItem.vue';


// TODO: 서버에서 productListResponseDto 가져오기
// id
// title
// imgSrc
// author
// publisher
// ratingAvg
// ratingCnt

</script>

<template>
    <section class="features">
        <h1 class="category__heading">개발/백엔드</h1>
        <h2 class="features__heading">상품</h2>
        <div class="features__container">
            <FeatureSectionItem img-src="/src/assets/images/product/1.webp" title="이게 되네? 챗GPT 미친 활용법 51제"
                author="오힘찬" publisher="골든래빗" :price=19000 :ratingAvg=9.5 :ratingCnt=2 :id=1 />

            <FeatureSectionItem img-src="/src/assets/images/product/2.webp" title="Vue 3 완벽 가이드" author="김코딩"
                publisher="한빛미디어" :price=32000 :ratingAvg=8.7 :ratingCnt=15 :id=2 />

            <FeatureSectionItem img-src="/src/assets/images/product/3.webp" title="챗GPT를 활용한 업무 자동화"
                author="이AI" publisher="인공지능출판" :price=25000 :ratingAvg=9.2 :ratingCnt=8 :id=3 />

            <FeatureSectionItem img-src="/src/assets/images/product/4.jpeg" title="프로그래머를 위한 데이터 구조와 알고리즘"
                author="박개발" publisher="코딩북스" :price=28000 :ratingAvg=9.8 :ratingCnt=20 :id=4 />

            <FeatureSectionItem img-src="/src/assets/images/product/5.webp" title="웹 개발 필수 패턴 50" author="정웹"
                publisher="프론트북스" :price=29000 :ratingAvg=8.9 :ratingCnt=12 :id=5 />

            <FeatureSectionItem img-src="/src/assets/images/product/6.jpeg" title="한권으로 배우는 Django & Vue"
                author="이장고" publisher="풀스택미디어" :price=35000 :ratingAvg=9.4 :ratingCnt=25 :id=6 />
        </div>
    </section>
</template>

<style scoped>
/* FEATURES */
.features {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
    /* TODO: add media queries for smaller sizes */
}

.features__container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 1.6rem;
}
</style>