<script setup>
import Nav from '@/components/sections/Nav.vue';
import Footer from '@/components/sections/Footer.vue';
import { useRoute } from 'vue-router';
const route = useRoute();
</script>

// 기본 레이아웃
<template>
    <div class="container">
        <Nav v-if="!route.meta.hideNavFooter" />
        <main class="main" v-if="!route.meta.noMainWrapper">
            <router-view />
        </main>
        <router-view v-else />
        <Footer v-if="!route.meta.hideNavFooter" />
    </div>
</template>


<style scoped>
/* MAIN */
.main {
    display: flex;
    flex-direction: column;
    gap: 9.2rem;
}
</style>
