<script setup>
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import SearchBar from '@/components/common/SearchBar.vue';
import router from '@/router';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';
import { useMemberStore } from '@/store/memberStore';
// const route = useRoute();

// state
const tokenStore = useTokenStore();
const memberStore = useMemberStore();
const categoriesDto = ref([]);
const isMobileMenuOpen = ref(false);

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

const toggleMobileMenu = () => {
    isMobileMenuOpen.value = !isMobileMenuOpen.value;
}

const fetchCategories = async () => {
    try {
        const res = await customAxios.get("/api/v1/categories");
        categoriesDto.value = res.data;
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
        <div class="d-flex align-items-center w-100 justify-content-between flex-wrap">
            <RouterLink to="/" class="navbar__logo">
                <img src="@/assets/logo.png" alt="Logo">
            </RouterLink>
            
            <!-- Mobile Menu Toggle -->
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
                    <RouterLink to="/login" class="nav-link">로그인</RouterLink>
                    <RouterLink to="/register" class="nav-link">회원가입</RouterLink>
                </template>
                <!-- 로그인된 상태 -->
                <template v-if="tokenStore.isLoggedIn">
                    <RouterLink to="/member/edit" class="nav-link">마이페이지</RouterLink>
                    <RouterLink to="/cart" class="nav-link">장바구니</RouterLink>
                    <a class="nav-link" @click.prevent="handleLogout">로그아웃</a>
                </template>
            </div>
        </div>
        <!-- 탑 네비게이션 END -->

        <!-- 바텀 네비게이션 START -->
        <template v-if="shouldShowBottomNav && !shouldShowMemberNav">
            <div class="navbar__bottom">
                <a href="#" class="nav-item dropdown-toggle" role="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent"
                    aria-controls="navbarToggleExternalContent" aria-expanded="false"
                    aria-label="Toggle navigation">카테고리</a>
                    
                <RouterLink to="/products?page=0&size=20&sort=sold,desc" class="nav-item">베스트상품</RouterLink>
                <RouterLink to="/products?page=0&size=20&sort=publishedDate,desc" class="nav-item">신상품</RouterLink>
                <RouterLink to="/products?page=0&size=20&sort=discountRate,desc" class="nav-item">세일</RouterLink>
            </div>
            <div class="collapse category-menu" id="navbarToggleExternalContent" data-bs-theme="light">
                <div class="category-list">
                    <RouterLink class="category-item" :to="`/categories/${dto.id}`" v-for="dto in categoriesDto">
                        {{ dto.name }}
                    </RouterLink>
                </div>
            </div>
        </template>
        <!-- 바텀 네비게이션 END -->

        <!-- 유저 정보 네비게이션 START -->
        <template v-if="shouldShowMemberNav">
            <div class="navbar__bottom">
                <RouterLink to="/liked" class="nav-item">좋아요 목록</RouterLink>
                <RouterLink to="/saved" class="nav-item">찜 목록</RouterLink>
                <RouterLink to="/cart" class="nav-item">장바구니</RouterLink>
                <RouterLink to="/orders" class="nav-item">주문 목록</RouterLink>
                <RouterLink to="/library" class="nav-item">라이브러리</RouterLink>
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

/* Mobile Menu Toggle Button */
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

/* Responsive Styles */
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
        padding: 1rem 0;
        border-top: 1px solid #E5E5E5;
    }

    .navbar__bottom {
        flex-direction: column;
    }

    .nav-item {
        width: 100%;
        border-bottom: 1px solid #E5E5E5;
    }
}

@media (max-width: 480px) {
    .category-list {
        padding: 1rem;
        gap: 1rem;
    }

    .category-item {
        width: calc(50% - 0.5rem);
    }
}
</style>