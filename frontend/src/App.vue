<script setup>
import Nav from '@/components/sections/Nav.vue';
import Footer from '@/components/sections/Footer.vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { onMounted } from 'vue';
import { useTokenStore } from './store/tokenStoreB';
const route = useRoute();
const store = useTokenStore();

const renewToken = async () => {
    // axios.get("http://localhost:8080/api/auth/refresh-token", {
    //     withCredentials: true
    // }).then(res => {
    //     console.log(res);
    //     if (res.data.accessToken) {
    //         store.setAccessToken(res.data.accessToken);
    //         console.log("갱신완료");
    //     } else {
    //         throw new Error("토큰 없음");
    //     }
    // }).catch(err => {
    //     console.log(err);
    //     console.log("토큰 갱신 실패 -> 로그아웃 상태");
        
    // })
    try {
        const res = await axios.get("http://localhost:8080/api/auth/refresh-token", {
            withCredentials: true
        });

        if (res.data.accessToken) {
            store.setAccessToken(res.data.accessToken);
            console.log("액세스토큰 갱신");
        } else {
            throw new Error("토큰 없음");
        }
    } catch (err) {
        console.log("토큰 갱신 실패 -> 로그아웃 상태");
        store.setAccessToken("");
    } finally {
        store.setLoading(false);
    }
}


onMounted(() => {
    renewToken();
})
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
