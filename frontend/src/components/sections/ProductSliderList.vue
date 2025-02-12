<script setup>
import { register } from "swiper/element/bundle";
import { onMounted, ref } from "vue";
import FeatureSectionItem from "../shared/FeatureSectionItem.vue";
import { PhCaretRight } from "@phosphor-icons/vue";

defineProps({
    sliderTitle: String,
    dtoList: Array
});

const swiperRef = ref(null);
const paginationRef = ref(null);

register();

onMounted(() => {
    const swiperContainer = swiperRef.value;

    const params = {
        slidesPerView: 6,
        slidesPerGroup: 6,
        speed: 500,
        loop: true,
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
});

</script>

<template>
    <div class="swiper-container-wrapper">
        <div class="header-container">
            <h3>
                <!-- FIXME: image not being rendered on build -->
                <RouterLink to="#" class="link-dark d-flex align-items-center">{{ sliderTitle }}
                    <PhCaretRight :size="16" />
                </RouterLink>
            </h3>
            <div ref="paginationRef" class="swiper-pagination-external text-muted"></div>
        </div>
        <swiper-container ref="swiperRef" init="false">
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="정보처리기사 필기" author="김영진" img-src="L.jpeg"
                        :price=32000 publisher="위키북스" :rating-avg=8.6 :rating-cnt=4 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="모두를 위한 양자컴퓨터" author="이상민"
                        img-src="L (2).jpeg" :price=28000 publisher="한빛미디어" :rating-avg=9.1
                        :rating-cnt=7 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="Deep Learning 101" author="박재호"
                        img-src="L (3).jpeg" :price=35000 publisher="길벗" :rating-avg=8.9
                        :rating-cnt=5 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="2025 AdsP 데이터분석 준전문가" author="최지원"
                        img-src="L (4).jpeg" :price=30000 publisher="인사이트" :rating-avg=8.8
                        :rating-cnt=6 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="정보처리기사 필기 기본서" author="정민우"
                        img-src="L (5).jpeg" :price=27000 publisher="에이콘" :rating-avg=9.2
                        :rating-cnt=8 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="정보처리기능사 필기 기본서" author="한소영"
                        img-src="L (6).jpeg" :price=33000 publisher="제이펍" :rating-avg=8.7
                        :rating-cnt=3 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="나의 첫 노션 마스터북" author="윤성준"
                        img-src="L (7).jpeg" :price=29000 publisher="프리렉" :rating-avg=9.0
                        :rating-cnt=5 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <FeatureSectionItem title="1400만 직장인을 위한 챗GPT 비즈니스 프롬프트" author="강민석"
                        img-src="L (8).jpeg" :price=31000 publisher="루비페이퍼" :rating-avg=8.5
                        :rating-cnt=4 />
                </div>
            </swiper-slide>
        </swiper-container>
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