<script setup>
import { register } from "swiper/element/bundle";
register();



</script>

<template>
    <swiper-container slides-per-view="6" slides-per-group="6" speed="500" loop="true" navigation="true"
        pagination-type="fraction">
        <swiper-slide>
            <img src="https://placehold.co/600x400/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/orange/white/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/beige/gray/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/lightblue/gray/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/green/gray/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/darkgray/gray/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/lightgreen/gray/png" alt="placehold">
        </swiper-slide>
        <swiper-slide>
            <img src="https://placehold.co/600x400/rose/gray/png" alt="placehold">
            <p>hello world</p>
        </swiper-slide>
    </swiper-container>

</template>

<style scoped>
swiper-container {
    --swiper-navigation-color: #333;
    --swiper-pagination-color: #333;
    --swiper-pagination-bullet-inactive-color: #333;
    /* --swiper-pagination-top: -15px; */
    /* --swiper-pagination-bottom: auto; */
}

swiper-container {
    width: 100%;
    height: 100%;
    padding-top: 40px;
}

swiper-slide {
    text-align: center;
    font-size: 18px;
    background: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
}

swiper-slide img {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
swiper-pagination {
    /* TODO: not being applied */
    text-align: right;
}
</style>