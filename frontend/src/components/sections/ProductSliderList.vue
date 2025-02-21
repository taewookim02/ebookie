<script setup>
import { register } from "swiper/element/bundle";
import { onMounted, onUpdated, ref, computed, watch } from "vue";
import FeatureSectionItem from "../shared/FeatureSectionItem.vue";
import { PhCaretRight } from "@phosphor-icons/vue";
import LoadingSpinner from "../shared/LoadingSpinner.vue";
import { useRoute } from "vue-router";

const props = defineProps({
    sliderTitle: String,
    products: Array // ProductRelatedResponseDto
});

const route = useRoute();

const swiperRef = ref(null);
const paginationRef = ref(null);
const breakpoints = {
    0: 2,
    640: 3,
    768: 4,
    1024: 5,
    1560: 6
};

const slidesPerView = computed(() => {
    const windowWidth = window.innerWidth;
    
    if (windowWidth < 640) {
        return breakpoints[0];
    } else if (windowWidth < 768) {
        return breakpoints[640];
    } else if (windowWidth < 1024) {
        return breakpoints[768]; 
    } else if (windowWidth < 1560) {
        return breakpoints[1024];
    } else {
        return breakpoints[1560];
    }
});

// register();
const initSwiper = () => {
    const swiperContainer = swiperRef.value;
    if (!swiperContainer) {
        // console.log("Swiper 컨테이너 부착 전");
        return;
    }
    const params = {
        slidesPerView: slidesPerView.value,
        slidesPerGroup: slidesPerView.value,
        speed: 500,
        loop: false,
        spaceBetween: 10,
        pagination: {
            el: paginationRef.value,
            type: "fraction"
        },
        breakpoints: {
            0: {
                slidesPerView: 2,
                slidesPerGroup: 2
            },
            640: {
                slidesPerView: 3,
                slidesPerGroup: 3
            },
            768: {
                slidesPerView: 4,
                slidesPerGroup: 4
            },
            1024: {
                slidesPerView: 5,
                slidesPerGroup: 5
            },
            1560: {
                slidesPerView: 6,
                slidesPerGroup: 6
            }
        },
    };
    Object.assign(swiperContainer, params);
    swiperContainer.initialize();
}


onMounted(() => {
    initSwiper();
    
})
onUpdated(() => {
    initSwiper();
});


</script>

<template>
    <div class="swiper-container-wrapper">
        <div class="header-container">
            <h3>
                <RouterLink to="#" class="link-dark d-flex align-items-center">{{ sliderTitle }}
                    <PhCaretRight :size="16" />
                </RouterLink>
            </h3>
            <div ref="paginationRef" class="swiper-pagination-external text-muted"></div>
        </div>
        <div v-if="!props.products">
            <LoadingSpinner />
        </div>
        <template v-else>
            <swiper-container ref="swiperRef" init="false">
                <swiper-slide v-for="product in products">
                    <div class="slide-div">
                        <FeatureSectionItem :title="product.name" :author="product.authorNames"
                            :img-src="product.thumbnail.fileName" :price="product.price"
                            :publisher="product.publisherName" :rating-avg="product.reviewRating"
                            :rating-cnt="product.reviewCount" :id="product.id" />
                    </div>
                </swiper-slide>
            </swiper-container>
        </template>
    </div>

</template>

<style scoped>
.swiper-container-wrapper {
    display: flex;
    flex-direction: column;
    gap: 1.6rem;
}

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
    min-height: 368.3px;
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


 .swiper-pagination-external {
    text-align: end;
    font-size: 1.2rem;
}

.header-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
</style>