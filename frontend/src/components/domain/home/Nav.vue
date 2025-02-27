<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import SearchBar from '@/components/common/SearchBar.vue';
import router from '@/router';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';
import { useMemberStore } from '@/store/memberStore';

// Use
const tokenStore = useTokenStore();
const memberStore = useMemberStore();

// State
const categoriesDto = ref([]);
const isMobileMenuOpen = ref(false);
const isBottomNavOpen = ref(false);

// Computed
const shouldShowBottomNav = computed(() => {
    const currentURLPath = router.currentRoute.value.path;
    return !["/login", "/register"].includes(currentURLPath);
});

const shouldShowMemberNav = computed(() => {
    const currentURLPath = router.currentRoute.value.path;
    return ["/member/edit", "/liked", "/saved", "/cart", "/orders", "/library"].includes(currentURLPath);
});

const isMobileWidth = computed(() => {
    return window.innerWidth < 768;
});

// Actions
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
    closeMobileMenu();
}

const toggleMobileMenu = () => {
    isMobileMenuOpen.value = !isMobileMenuOpen.value;
    if (!isMobileMenuOpen.value) {
        isBottomNavOpen.value = false;
    }
}

const closeMobileMenu = () => {
    isMobileMenuOpen.value = false;
    isBottomNavOpen.value = false;
}

const toggleBottomNav = () => {
    isBottomNavOpen.value = !isBottomNavOpen.value;
}

const fetchCategories = async () => {
    try {
        const res = await customAxios.get("/api/v1/categories");
        categoriesDto.value = res.data;
    } catch (err) {
        console.log(err);
    }
};

// Lifecycle hooks
onMounted(() => {
    fetchCategories();
})

</script>

<template>
    <nav class="navbar mt-5">
        <!-- 탑 네비게이션 START -->
        <div class="d-flex align-items-center w-100 justify-content-between flex-wrap">
            <RouterLink to="/" class="navbar__logo" @click="closeMobileMenu">
                <img src="@/assets/logo.png" alt="Logo">
            </RouterLink>
            
            <!-- 모바일 메뉴 -->
            <button class="mobile-menu-toggle" @click="toggleMobileMenu">
                <span></span>
                <span></span>
                <span></span>
            </button>

            <div class="navbar__search">
                <SearchBar></SearchBar>
            </div>
            
            <div class="navbar__links" :class="{ 'mobile-menu-open': isMobileMenuOpen }">
                <!-- 로그인 안된 상태 -->
                <template v-if="!tokenStore.isLoggedIn">
                    <RouterLink to="/login" class="nav-link" @click="closeMobileMenu">로그인</RouterLink>
                    <RouterLink to="/register" class="nav-link" @click="closeMobileMenu">회원가입</RouterLink>
                </template>
                <!-- 로그인된 상태 -->
                <template v-if="tokenStore.isLoggedIn">
                    <RouterLink to="/member/edit" class="nav-link" @click="closeMobileMenu">마이페이지</RouterLink>
                    <RouterLink to="/cart" class="nav-link" @click="closeMobileMenu">장바구니</RouterLink>
                    <a class="nav-link" @click.prevent="handleLogout">로그아웃</a>
                </template>
            </div>
        </div>
        <!-- 탑 네비게이션 END -->

        <!-- 바텀 네비게이션 START -->
        <template v-if="shouldShowBottomNav && !shouldShowMemberNav">
            <div class="navbar__bottom" :class="{ 'mobile-menu-open': isMobileMenuOpen }">
                
                <RouterLink to="/products?page=0&size=20&sort=sold,desc" class="nav-item" @click="closeMobileMenu">베스트상품</RouterLink>
                <RouterLink to="/products?page=0&size=20&sort=publishedDate,desc" class="nav-item" @click="closeMobileMenu">신상품</RouterLink>
                <RouterLink to="/products?page=0&size=20&sort=discountRate,desc" class="nav-item" @click="closeMobileMenu">세일</RouterLink>
                <a href="#" class="nav-item dropdown-toggle" role="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent"
                    aria-controls="navbarToggleExternalContent" aria-expanded="false"
                    aria-label="Toggle navigation" @click="toggleBottomNav">카테고리</a>
            </div>
            <div class="collapse category-menu" :class="{ 'show': isBottomNavOpen && isMobileWidth }" id="navbarToggleExternalContent" data-bs-theme="light">
                <div class="category-list">
                    <RouterLink class="category-item" :to="`/categories/${dto.id}`" v-for="dto in categoriesDto" @click="closeMobileMenu">
                        {{ dto.name }}
                    </RouterLink>
                </div>
            </div>
        </template>
        <!-- 바텀 네비게이션 END -->

        <!-- 유저 정보 네비게이션 START -->
        <template v-if="shouldShowMemberNav">
            <div class="navbar__bottom" :class="{ 'mobile-menu-open': isMobileMenuOpen }">
                <RouterLink to="/liked" class="nav-item" @click="closeMobileMenu">좋아요 목록</RouterLink>
                <RouterLink to="/saved" class="nav-item" @click="closeMobileMenu">찜 목록</RouterLink>
                <RouterLink to="/cart" class="nav-item" @click="closeMobileMenu">장바구니</RouterLink>
                <RouterLink to="/orders" class="nav-item" @click="closeMobileMenu">주문 목록</RouterLink>
                <RouterLink to="/library" class="nav-item" @click="closeMobileMenu">라이브러리</RouterLink>
            </div>
        </template>
        <!-- 유저 정보 네비게이션 END -->
        
    </nav>

</template>
<style scoped>
.navbar {
    padding: 0 2rem;
}

.navbar__logo {
    display: flex;
    align-items: center;
}

.navbar__logo img {
    max-height: 31px;
    width: auto;
}

.navbar__links {
    display: flex;
    gap: 2rem;
}

.nav-link {
    color: #333333;
    text-decoration: none;
    font-weight: 500;
    transition: color 0.2s ease;
    cursor: pointer;
}

.nav-link:hover {
    color: #4D4D4D;
}

.navbar__bottom {
    display: flex;
    align-items: center;
    width: 100%;
    justify-content: space-between;
    margin-top: 2rem;
    border-top: 1px solid #E5E5E5;
    border-bottom: 1px solid #E5E5E5;
    flex-wrap: wrap;
}

.nav-item {
    flex-grow: 1;
    padding: 1rem;
    color: #333333;
    text-decoration: none;
    text-align: center;
    font-weight: 500;
    transition: background-color 0.2s ease;
}

.nav-item:hover {
    background-color: #F5F5F5;
    color: #1A1A1A;
}

.category-menu {
    width: 100%;
    background-color: #FFFFFF;
    border-bottom: 1px solid #E5E5E5;
}

.category-list {
    padding: 1.5rem;
    display: flex;
    gap: 1.5rem;
    flex-wrap: wrap;
    justify-content: end;
}

.category-item {
    color: #333333;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    transition: background-color 0.2s ease;
}

.category-item:hover {
    background-color: #F5F5F5;
    color: #1A1A1A;
}

/* 모바일 메뉴 */
.mobile-menu-toggle {
    display: none;
    flex-direction: column;
    justify-content: space-between;
    width: 30px;
    height: 20px;
    background: transparent;
    border: none;
    cursor: pointer;
    padding: 0;
}

.mobile-menu-toggle span {
    width: 100%;
    height: 2px;
    background-color: #333;
}

@media (max-width: 768px) {
    .navbar {
        padding: 0 1rem;
    }

    .mobile-menu-toggle {
        display: flex;
    }

    .navbar__search {
        width: 100%;
        order: 3;
        margin: 1rem 0;
    }

    .navbar__links {
        display: none;
        width: 100%;
        flex-direction: column;
        gap: 0;
        order: 4;
    }

    .navbar__links.mobile-menu-open {
        display: flex;
    }

    .nav-link {
        padding: 1rem;
        border-top: 1px solid #E5E5E5;
        text-align: left;
    }

    .navbar__bottom {
        display: none;
        flex-direction: column;
        align-items: flex-start;
        margin-top: 0;
    }

    .navbar__bottom.mobile-menu-open {
        display: flex;
    }

    .nav-item {
        width: 100%;
        border-bottom: 1px solid #E5E5E5;
        text-align: left;
        padding-left: 1rem;
    }

    .category-menu {
        border-top: none;
        margin-top: -1px; 
    }

    .category-list {
        padding: 0.5rem 1rem;
    }

    .category-item {
        width: 100%;
        text-align: left;
        padding: 0.75rem 1rem;
        border-radius: 0;
        border-bottom: 1px solid #E5E5E5;
    }

    .category-item:last-child {
        border-bottom: none;
    }
}

@media (max-width: 480px) {
    .category-list {
        padding: 0;
        gap: 0;
    }

    .category-item {
        width: 100%;
    }
}
</style>