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
    padding: 4rem 2rem;
    background-color: #f5f5f5;
}

.hero__header {
    text-align: center;
    margin-bottom: 1.6rem;
}

.hero__header-subtitle {
    font-size: 1.2rem;
    color: #666;
    text-transform: uppercase;
    letter-spacing: 2px;
}

.hero__header-title {
    font-size: 2.5rem;
    margin: 1rem 0;
    color: #333;
}

.hero__header-desc {
    color: #666;
    max-width: 600px;
    margin: 1rem auto;
}

hr {
    width: 60px;
    margin: 1.5rem auto;
    border: none;
    border-top: 2px solid #333;
}

.book-swiper {
    padding: 2rem 0;
}

.book-card {
    background: rgba(255, 255, 255, 0.9);
    padding: 2rem;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    text-align: center;
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
    position: relative;
}

.book-card__rank {
    position: absolute;
    top: 1rem;
    left: 1rem;
    background: #333;
    color: white;
    width: 2rem;
    height: 2rem;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
}

.book-card__title {
    font-size: 1.5rem;
    color: #333;
    margin-bottom: 0.5rem;
}

.book-card__author {
    color: #666;
    font-size: 1rem;
    margin-bottom: 1rem;
}

.book-card__link {
    display: inline-block;
    padding: 0.5rem 1rem;
    background: #333;
    color: white;
    text-decoration: none;
    border-radius: 4px;
    transition: background 0.3s;
}

.book-card__link:hover {
    background: #555;
}

:deep(.swiper-pagination-bullet) {
    width: 12px;
    height: 12px;
    background: #333;
    opacity: 0.3;
}

:deep(.swiper-pagination-bullet-active) {
    opacity: 1;
}
</style>