<script setup>
// TODO: define props


</script>

<template>
    <div class="author-container">
        <div class="author-header">
            <span class="author-header__name">김철수 저</span>
        </div>
        <div class="author-body">
            <img class="author-img" src="https://placehold.co/120x120/orange/white/png" alt="profileimg">
            <p>서울대학교 기계항공공학부를 졸업하고 롯데면세점 빅데이터팀 데이터 분석가를 거쳐 현재는 프리랜서 마켓 크몽에서 AI 엔지니어로 일하고 있다. 『파이토치 라이트닝으로 시작하는 딥러닝』을 번역했으며,
                최근에는 LLM을 활용한 어시스턴트(에이전트) 개발에 관심이 많다.</p>
        </div>
    </div>
</template>

<style scoped>
.author-container {
    display: flex;
    flex-direction: column;
    gap: 1.6rem;
}

.author-header__name {
    font-weight: 700;
}

.author-body {
    display: flex;
    gap: 2.4rem;
}

.author-img {
    width: 112px;
    height: auto;
    border-radius: 50%;
}
</style>