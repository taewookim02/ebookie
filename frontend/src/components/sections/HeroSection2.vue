<script setup>
import { register } from "swiper/element/bundle";
import { ref, onMounted } from 'vue';

register();

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
    loop: true,
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
                이번 달 TOP 5
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
            <swiper-slide class="swiper-slide--1">
                <div class="book-card">
                    <img src="@/assets/images/product/L (10).jpeg" alt="">
                    <div class="book-card__info">
                        <h2 class="book-card__title">클린 코드</h2>
                        <p class="book-card__author">로버트 C. 마틴</p>
                    </div>
                </div>
            </swiper-slide>
            <swiper-slide class="swiper-slide--2">
                <div class="book-card">
                    <img src="@/assets/images/product/L (11).jpeg" alt="">
                    <div class="book-card__info">
                        <h2 class="book-card__title">리팩터링</h2>
                        <p class="book-card__author">마틴 파울러</p>
                    </div>
                </div>
            </swiper-slide>
            <swiper-slide class="swiper-slide--3">
                <div class="book-card">
                    <img src="@/assets/images/product/L (12).jpeg" alt="">
                    <div class="book-card__info">
                        <h2 class="book-card__title">도메인 주도 설계</h2>
                        <p class="book-card__author">에릭 에반스</p>
                    </div>
                </div>
            </swiper-slide>
            <swiper-slide class="swiper-slide--4">
                <div class="book-card">
                    <img src="@/assets/images/product/L (13).jpeg" alt="">
                    <div class="book-card__info">
                        <h2 class="book-card__title">객체지향의 사실과 오해</h2>
                        <p class="book-card__author">조영호</p>
                    </div>
                </div>
            </swiper-slide>
            <swiper-slide class="swiper-slide--5">
                <div class="book-card">
                    <img src="@/assets/images/product/L (14).jpeg" alt="">
                    <div class="book-card__info">
                        <h2 class="book-card__title">이펙티브 자바</h2>
                        <p class="book-card__author">조슈아 블로크</p>
                    </div>
                </div>
            </swiper-slide>
        </swiper-container>
        
        <div class="swiper-pagination"></div>
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
    /* border-radius: 8px; */
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    text-align: center;
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
}



.book-card__title {
    font-size: 1.5rem;
    color: #333;
    margin-bottom: 0.5rem;
}

.book-card__author {
    color: #666;
    font-size: 1rem;
}

/* Swiper slide backgrounds */
/* .swiper-slide--1 { background: linear-gradient(45deg, #ff6b6b, #ffd93d); }
.swiper-slide--2 { background: linear-gradient(45deg, #4facfe, #00f2fe); }
.swiper-slide--3 { background: linear-gradient(45deg, #43e97b, #38f9d7); }
.swiper-slide--4 { background: linear-gradient(45deg, #fa709a, #fee140); }
.swiper-slide--5 { background: linear-gradient(45deg, #6a11cb, #2575fc); } */

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