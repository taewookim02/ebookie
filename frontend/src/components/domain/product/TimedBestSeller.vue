<script setup>
import { onMounted, ref } from 'vue';
import { PhCaretRight, PhRows } from '@phosphor-icons/vue';
import ProductCardHorizontal from '@/components/domain/product/ProductCardHorizontal.vue';
// import { register } from 'swiper/element';

// register();

// 상태
const swiperRef = ref(null);

// 액션
const handleDailyClick = () => {
    console.log("handleDailyClick!");
    // 서버 api call
}

const handleWeeklyClick = () => {
    console.log("handleWeeklyClick!");
}

const handleMonthlyClick = () => {
    console.log("handleMonthlyClick!");
}


// 생명주기 훅
onMounted((e) => {
    const swiperContainer = swiperRef.value;

    const params = {
        slidesPerView: 3,
        slidesPerGroup: 3,
        spaceBetween: 16,
        grid: {
            rows: 2,
            fill: "row"
        },
        // TODO: breakpoints
        // breakpoints: {
        //     640: {
        //     },
        //     768: {
        //     },
        //     1024: {
        //     },
        //     1560: {
        //     }
        // },
    }

    Object.assign(swiperContainer, params);
    swiperContainer.initialize();

})
</script>

<template>
    <div class="swiper-container-wrapper">
        <div class="header-container">
            <h3>
                <RouterLink to="#" class="link-dark d-flex align-items-center">일간/주간/월간 베스트셀러
                    <PhCaretRight :size="16" color="#333333" />
                </RouterLink>
            </h3>
            <div class="header-container__controls">
                <button class="active" @click="handleDailyClick">일간</button>
                <button class="" @click="handleWeeklyClick">주간</button>
                <button class="" @click="handleMonthlyClick">월간</button>
            </div>
        </div>
        <swiper-container ref="swiperRef" init="false">
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="정보처리기사 필기" author="김영진" img-src="L.jpeg"
                        :price=32000 publisher="위키북스" :rating-avg=8.6 :rating-cnt=4 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="모두를 위한 양자컴퓨터" author="이상민"
                        img-src="L (2).jpeg" :price=28000 publisher="한빛미디어" :rating-avg=9.1
                        :rating-cnt=7 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="Deep Learning 101" author="박재호"
                        img-src="L (3).jpeg" :price=35000 publisher="길벗" :rating-avg=8.9
                        :rating-cnt=5 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="2025 AdsP 데이터분석 준전문가" author="최지원"
                        img-src="L (4).jpeg" :price=30000 publisher="인사이트" :rating-avg=8.8
                        :rating-cnt=6 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="정보처리기사 필기 기본서" author="정민우"
                        img-src="L (5).jpeg" :price=27000 publisher="에이콘" :rating-avg=9.2
                        :rating-cnt=8 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="정보처리기능사 필기 기본서" author="한소영"
                        img-src="L (6).jpeg" :price=33000 publisher="제이펍" :rating-avg=8.7
                        :rating-cnt=3 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="나의 첫 노션 마스터북" author="윤성준"
                        img-src="L (7).jpeg" :price=29000 publisher="프리렉" :rating-avg=9.0
                        :rating-cnt=5 />
                </div>
            </swiper-slide>
            <swiper-slide>
                <div class="slide-div">
                    <ProductCardHorizontal title="1400만 직장인을 위한 챗GPT 비즈니스 프롬프트" author="강민석"
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
    gap: 2.4rem;
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
    flex-direction: column;
    align-items: start;
    justify-content: space-between;
    gap: .8rem;
}

.header-container__controls {
    display: flex;
    gap: .8rem;

    & button {
        border-radius: 4px;
        padding: 4px 16px;
        border: 1px solid #333;
        user-select: none;
    }

    & button:hover {
        background-color: #ccc;
    }

    & button.active {
        background-color: #333;
        color: #fff;
    }
}
</style>