<script setup>
import ActionButton from '@/components/common/ActionButton.vue';

</script>

<template>
    <section class="not-found">
        <h3>404: 요청하신 페이지를 찾을 수 없습니다.</h3>
        <RouterLink to="/">
            <ActionButton>홈으로 가기</ActionButton>
        </RouterLink>
    </section>
</template>

<style scoped>
    .not-found {
        display: grid;
        place-items: center;
        gap: 1.6rem;
        padding: 6.4rem;
    }
</style>