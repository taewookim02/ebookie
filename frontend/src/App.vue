<script setup>
import Nav from '@/components/domain/home/Nav.vue';
import Footer from '@/components/domain/home/Footer.vue';
import { onMounted } from 'vue';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';
import { useMemberStore } from './store/memberStore';

// Use
const tokenStore = useTokenStore();
const memberStore = useMemberStore();

// Actions
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
    } catch (err) {
        console.log("fetchMemberInfo() err:", err);
        memberStore.setMember(null);
    }
};


// LifeCycle hook
onMounted(() => {
    renewToken();
})
</script>

<template>
    <div class="template-container container">
        <Nav />
        <main class="main" >
            <router-view />
        </main>
        <Footer />
    </div>
</template>


<style scoped>
/* MAIN */
.main {
    display: flex;
    flex-direction: column;
    gap: 6.4rem;
}
</style>
