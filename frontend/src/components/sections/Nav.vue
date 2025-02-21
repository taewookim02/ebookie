<script setup>
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import SearchBar from '../shared/SearchBar.vue';
import router from '@/router';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';
import { useMemberStore } from '@/store/memberStore';
// const route = useRoute();

// state
const tokenStore = useTokenStore();
const memberStore = useMemberStore();
const categoriesDto = ref([]);


const shouldShowBottomNav = computed(() => {
    const currentURLPath = router.currentRoute.value.path;
    return !["/login", "/register"].includes(currentURLPath);
})
const shouldShowMemberNav = computed(() => {
    const currentURLPath = router.currentRoute.value.path;
    return ["/member/edit", "/liked", "/saved", "/cart", "/orders", "/library"].includes(currentURLPath);
})
// actions
const handleLogout = (e) => {
    customAxios
        .get("/api/v1/auth/logout", {
            withCredentials: true
        }).then(res => {
            console.log(res);
        }).catch(err => {
            console.log(err);
        })
    tokenStore.setAccessToken("");
    memberStore.setMember(null);
    alert("로그아웃 성공!");
    router.push("/");
}

const fetchCategories = async () => {
    try {
        const res = await customAxios.get("/api/v1/categories");
        categoriesDto.value = res.data;
        // console.log(res);
    } catch (err) {
        console.log(err);
    }
};

onMounted(() => {
    fetchCategories();
})

</script>

<template>
    <nav class="navbar mt-5">
        <!-- 탑 네비게이션 START -->
        <div class="d-flex align-items-center w-100 justify-content-between">
            <RouterLink to="/" class="d-flex">
                <img src="@/assets/logo.png" alt="Logo" style="max-height: 31px; width: auto;">
            </RouterLink>
            <div class="navbar__search">
                <SearchBar></SearchBar>
            </div>
            <div class="navbar__links d-flex gap-4">
                <!-- 로그인 안된 상태 -->
                <template v-if="!tokenStore.isLoggedIn">
                    <RouterLink to="/login" class="link-dark">로그인</RouterLink>
                    <RouterLink to="/register" class="link-dark">회원가입</RouterLink>
                </template>
                <!-- 로그인된 상태 -->
                <template v-if="tokenStore.isLoggedIn">
                    <RouterLink to="/member/edit" class="link-dark">마이페이지</RouterLink>
                    <RouterLink to="/cart" class="link-dark">장바구니</RouterLink>
                    <a to="#" class="link-dark" @click.prevent="handleLogout" style="cursor: pointer;">로그아웃</a>
                </template>
            </div>
        </div>
        <!-- 탑 네비게이션 END -->

        <!-- 바텀 네비게이션 START -->
        <template v-if="shouldShowBottomNav && !shouldShowMemberNav">
            <div class="navbar__bottom d-flex align-items-center w-100 justify-content-between mt-4">
                <a href="#" class="flex-grow-1 p-3 link-dark text-center dropdown-toggle" role="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent"
                    aria-controls="navbarToggleExternalContent" aria-expanded="false"
                    aria-label="Toggle navigation">카테고리</a>
                <RouterLink to="/todo" class="flex-grow-1 link-dark p-3 text-center">베스트상품</RouterLink>
                <RouterLink to="/todo" class="flex-grow-1 link-dark p-3 text-center">신상품</RouterLink>
                <RouterLink to="/todo" class="flex-grow-1 link-dark p-3 text-center">이벤트</RouterLink>
            </div>
            <div class="collapse w-100" id="navbarToggleExternalContent" data-bs-theme="light">
                <div class="p-4 d-flex gap-2">
                    <RouterLink class="link-dark" :to="`/categories/${dto.id}`" v-for="dto in categoriesDto">
                        {{ dto.name }}
                    </RouterLink>
                </div>
            </div>
        </template>
        <!-- 바텀 네비게이션 END -->

        <!-- 유저 정보 네비게이션 START -->
        <template v-if="shouldShowMemberNav">
            <div class="navbar__bottom d-flex align-items-center w-100 justify-content-between mt-4">
                <RouterLink to="/liked" class="flex-grow-1 link-dark p-3 text-center">좋아요 목록</RouterLink>
                <RouterLink to="/saved" class="flex-grow-1 link-dark p-3 text-center">찜 목록</RouterLink>
                <RouterLink to="/cart" class="flex-grow-1 link-dark p-3 text-center">장바구니</RouterLink>
                <RouterLink to="/orders" class="flex-grow-1 link-dark p-3 text-center">주문 목록</RouterLink>
                <RouterLink to="/library" class="flex-grow-1 link-dark p-3 text-center">라이브러리</RouterLink>
            </div>
        </template>
        <!-- 유저 정보 네비게이션 END -->
        
    </nav>

</template>
<style scoped>
/* NAV */
.nav {
    padding: 2.4rem;
    /* display: flex; */
    align-items: center;
    justify-content: space-between;
}

.nav__logo {
    max-height: 3.2rem;
    width: auto;
    display: block;
    cursor: pointer;
}

.nav__links {
    display: flex;
    gap: 2.4rem;
}


.navbar__bottom>a:hover {
    background-color: #ddd;
}
</style>