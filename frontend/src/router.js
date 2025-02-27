import { createRouter, createWebHistory } from 'vue-router';
import Home from "@/views/HomePage.vue";
import NotFoundPage from '@/views/NotFoundPage.vue';
import { useTokenStore } from '@/store/tokenStore';
import { watch } from 'vue';

// URL 목록
const routes = [
    { path: '/', component: Home },
    { path: '/home', component: Home },
    { path: '/login', component: () => import('@/views/members/LoginPage.vue') },
    { path: '/register', component: () => import('@/views/members/RegisterPage.vue') },
    {
        path: '/member/edit', component: () => import('@/views/members/EditProfilePage.vue'),
        meta: { requiresAuth: true, hideAuthLinks: true }
    },
    {
        path: "/liked", component: () => import("@/views/members/LikedPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/saved", component: () => import("@/views/members/SavedPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/cart", component: () => import("@/views/members/CartPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/library", component: () => import("@/views/products/ProductLibraryPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/orders", component: () => import("@/views/orders/OrderPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/orders/:id", component: () => import("@/views/orders/OrderDetailPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/products", component: () => import("@/views/products/ProductRankingListPage.vue"),
    },
    {
        path: "/products/search/result", component: () => import("@/views/products/ProductSearchListPage.vue"),
    },
    { path: '/products/:id', component: () => import('@/views/products/ProductDetailPage.vue') },
    { path: '/categories/:id', component: () => import('@/views/products/ProductCategoryListPage.vue') },
    { path: '/:pathMatch(.*)*', component: NotFoundPage },
];

// vue router 설정
const router = createRouter({
    //   history: createWebHashHistory(), // hash mode
    history: createWebHistory(),
    routes,
    scrollBehavior(to, from, savedPosition) {
        // 페이지 이동 시 페이지 top에서 시작
        return { top: 0 }
    }
});

// 라우팅 가드 (URL 보호함)
router.beforeEach(async (to, from, next) => { // 페이지 이동하기 전에 호출

    const store = useTokenStore();
    if (store.isLoading) {
        await new Promise(resolve => {
            const unwatch = watch(
                () => store.isLoading,
                (isLoading) => {
                    if (!isLoading) {
                        unwatch();
                        resolve();
                    }
                },
                { immediate: true }
            );
        });
    }

    const isLoggedIn = store.isLoggedIn;

    if (["/login", "/register"].includes(to.path) && isLoggedIn) {
        next("/");
    } else if (to.meta.requiresAuth && !isLoggedIn) {
        next("/login");
    } else {
        next();
    }
});


export default router;