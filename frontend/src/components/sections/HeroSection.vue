<script setup>
import CtaButton from '@/components/shared/CtaButton.vue';

// 캐러우셀 css
import '@splidejs/splide/css';

import Splide from '@splidejs/splide';
import { onMounted } from 'vue';
import { PhCaretLeft, PhCaretRight } from '@phosphor-icons/vue';


onMounted(() => {
    new Splide(".splide", {
        type: "loop",
        perPage: 1,
        autoplay: true,
        // height: "100%"
        height: 336, 
        arrows: true
    }).mount();

})
</script>


<template>
    <section class="hero">
        <div class="splide">
            <div class="splide__arrows splide__arrows--ltr">
                <button class="splide__arrow splide__arrow--prev" type="button" aria-label="Previous slide"
                    aria-controls="container-track">
                    <PhCaretLeft :size="40" color="#8f8f8f" />
                </button>
                <button class="splide__arrow splide__arrow--next" type="button" aria-label="Next slide"
                    aria-controls="container-track">
                    <PhCaretRight :size="40" color="#8f8f8f" />
                </button>
            </div>


            <div class="splide__track">
                <ul class="splide__list">
                    <li class="splide__slide">
                        <img src="@/assets/hero2.png" alt="hero">
                    </li>
                    <li class="splide__slide">
                        <img src="@/assets/hero.png" alt="hero">
                    </li>
                    <li class="splide__slide">
                        <img src="@/assets/hero3.png" alt="hero">
                    </li>
                </ul>
            </div>
        </div>
    </section>
</template>


<style scoped>
/* SPLIDE */
.hero {
    width: 100%;
}

.splide__slide {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}

.slide-content {
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.slide-content img {
    max-height: 100%;
    max-width: 100%;
    object-fit: contain;
    vertical-align: middle;
}

/* HERO */
.hero {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 2.4rem;
}

.splide__slide {
    height: 150px;
}

/* Custom arrow styles */
:deep(.splide__arrow) {
    background: transparent;
    width: 4rem;
    height: 4rem;
    opacity: 1;
}

:deep(.splide__arrow svg) {
    width: 100%;
    height: 100%;
    transform: scale(1);
    /* TODO: set proper color */
    fill: #3f3f3f;
    color: #3f3f3f;
}

:deep(.splide__arrow--prev) {
    left: 1em;
}

:deep(.splide__arrow--next) {
    right: 1em;
}
</style>