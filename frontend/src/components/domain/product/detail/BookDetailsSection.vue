<script setup>
defineProps({
    detailDto: Object
})
</script>

<template>
    <section class="details">
        <h3>작품소개</h3>
        <div class="details-container">
            <div class="details-category">
                <h4 class="details-category__heading">이 상품이 속한 분야</h4>
                <span>eBook > IT > {{ detailDto.categoryName }}</span>
            </div>
            <div class="details-info">
                <p>
                    파일은 결제내역 > 결제상세 > 다운로드 버튼을 눌러 다운로드 받으실 수 있습니다.
                    (단, 로그인 및 결제 후 다운로드 이용 가능)
                </p>

                <br>

                 <p>
                    {{ detailDto.description }}
                 </p>
            </div>
        </div>
    </section>
</template>

<style scoped>
.details-container {
    padding: 2.4rem 2.4rem 6.4rem 2.4rem;
    display: flex;
    flex-direction: column;
    gap: 3.2rem;
    border-bottom: 1px solid #ccc;
}

.details-category {
    display: flex;
    flex-direction: column;
    gap: 1.6rem;
}

.details-category__heading {
    font-weight: 700;
}
</style>