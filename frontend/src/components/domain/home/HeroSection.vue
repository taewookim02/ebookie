<script setup>
import { ref, onMounted } from 'vue';
import { getImageFromServer } from '@/helper/imgPath';
import { RouterLink } from 'vue-router';

const props = defineProps({
    topFiveProductDtos: Array
});

const swiperRef = ref(null);

onMounted(() => {
    const swiperContainer = swiperRef.value;
    const params = {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        autoPlay: true,
        coverflowEffect: {
            rotate: 0,
            stretch: 0,
            depth: 100,
            modifier: 3,
            slideShadows: true
        },
        loop: false,
        pagination: {
            el: ".swiper-pagination",
            clickable: true
        },
        breakpoints: {
            640: { slidesPerView: 2 },
            768: { slidesPerView: 2 },
            1024: { slidesPerView: 3 },
            1560: { slidesPerView: 3 }
        }
    };

    Object.assign(swiperContainer, params);
    swiperContainer.initialize();
});

</script>

<template>

    <section class="hero">

        <header class="hero__header">
            <span class="hero__header-subtitle">
                TOP 5 베스트셀러
            </span>
            <h1 class="hero__header-title">
                개발 추천도서
            </h1>
            <hr>
            <p class="hero__header-desc">
                사람들이 가장 많이 찾는 도서를 만나보세요.
            </p>
        </header>
        <swiper-container ref="swiperRef" init="false" class="book-swiper">
            <swiper-slide v-for="(product, index) in topFiveProductDtos" :key="product.id"
                :class="`swiper-slide--${index + 1}`">
                <div class="book-card">
                    <div class="book-card__rank">
                        <span class="rank-number">{{ index + 1 }}</span>
                    </div>
                    <img :src="getImageFromServer(product.thumbnail.fileName)" :alt="product.name">
                    <div class="book-card__info">
                        <h2 class="book-card__title">{{ product.name }}</h2>
                        <p class="book-card__author">{{ product.authorNames }}</p>
                        <RouterLink :to="`/products/${product.id}`" class="book-card__link">
                            자세히 보기
                        </RouterLink>
                    </div>
                </div>
            </swiper-slide>
        </swiper-container>
    </section>
</template>
<style scoped>
.hero {
    padding: 5rem 2rem;
    background-color: var(--background-color);
    border-radius: 8px;
}

.hero__header {
    text-align: center;
    margin-bottom: 2rem;
}

.hero__header-subtitle {
    font-size: 1.4rem;
    color: var(--text-secondary-color);
    text-transform: uppercase;
    letter-spacing: 1.5px;
}

.hero__header-title {
    font-size: 3rem;
    margin: 1rem 0;
    color: var(--text-color);
    font-weight: bold;
}

.hero__header-desc {
    color: var(--text-secondary-color);
    max-width: 700px;
    margin: 1rem auto;
    font-size: 1.1rem;
}

hr {
    width: 80px;
    margin: 1.5rem auto;
    border: none;
    border-top: 3px solid var(--primary-color);
}

.book-swiper {
    padding: 2rem 0;
}

.book-card {
    background: rgba(255, 255, 255, 0.95);
    padding: 2.5rem;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
    text-align: center;
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
    position: relative;
    border-radius: 8px;
    transition: transform 0.3s;
}

.book-card:hover {
    transform: translateY(-5px);
}

.book-card__rank {
    position: absolute;
    top: 1rem;
    left: 1rem;
    background: var(--primary-color);
    color: white;
    width: 2.5rem;
    height: 2.5rem;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    font-size: 1.2rem;
}

.book-card__title {
    font-size: 1.6rem;
    color: var(--text-color);
    margin-bottom: 0.5rem;
}

.book-card__author {
    color: var(--text-secondary-color);
    font-size: 1.1rem;
    margin-bottom: 1rem;
}

.book-card__link {
    display: inline-block;
    padding: 0.6rem 1.2rem;
    background: var(--primary-color);
    color: white;
    text-decoration: none;
    border-radius: 4px;
    transition: background 0.3s, transform 0.3s;
}

.book-card__link:hover {
    background: var(--primary-hover);
    transform: scale(1.05);
}

:deep(.swiper-pagination-bullet) {
    width: 14px;
    height: 14px;
    background: var(--text-secondary-color);
    opacity: 0.5;
}

:deep(.swiper-pagination-bullet-active) {
    opacity: 1;
    background: var(--primary-color);
}
</style>