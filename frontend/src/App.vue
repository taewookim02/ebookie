<script setup>
import Nav from '@/components/sections/Nav.vue';
import Footer from '@/components/sections/Footer.vue';
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';
import { useMemberStore } from './store/memberStore';


const route = useRoute();
const tokenStore = useTokenStore();
const memberStore = useMemberStore();


const renewToken = async () => {
    try {
        const res = await customAxios
            .get("/api/v1/auth/refresh-token", {
                withCredentials: true
            });
        if (res.data.accessToken) {
            tokenStore.setAccessToken(res.data.accessToken);
            await fetchMemberInfo(); // 유저 정보 세팅
            console.log("액세스토큰 갱신");
        } else {
            throw new Error("토큰 없음");
        }
    } catch (err) {
        console.log("토큰 갱신 실패 -> 로그아웃 상태");
        tokenStore.setAccessToken("");
    } finally {
        tokenStore.setLoading(false);
    }
};

const fetchMemberInfo = async () => {
    try {
        if (tokenStore.accessToken) {

            const res = await customAxios.get("/api/v1/members/info");
            memberStore.setMember(res.data)
        }
        // TODO: set memberStore;
    } catch (err) {
        console.log("fetchMemberInfo() err:", err);
        memberStore.setMember(null);
    }
};

onMounted(() => {
    renewToken();
})
</script>

// 기본 레이아웃
<template>
    <div class="template-container container">
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
    gap: 6.4rem;
}

/* 
.template-container {
    max-width: 1200px;
    margin: auto;
} */
</style>
