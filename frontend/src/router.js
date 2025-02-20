import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';
import Home from "@/views/HomePage.vue";
import NotFoundPage from '@/views/NotFoundPage.vue';
import { useTokenStore } from '@/store/tokenStore';
import { watch } from 'vue';

// URL 목록
const routes = [
    // TODO: maybe the lazy loading is showing another page for a split second
    { path: '/', component: Home },
    { path: '/login', component: () => import('@/views/LoginPage.vue') },
    { path: '/register', component: () => import('@/views/RegisterPage.vue') },
    {
        path: '/member/edit', component: () => import('@/views/EditProfilePage.vue'),
        meta: { requiresAuth: true, hideAuthLinks: true }
    },
    {
        path: "/liked", component: () => import("@/views/LikedPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/saved", component: () => import("@/views/SavedPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/cart", component: () => import("@/views/CartPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/library", component: () => import("@/views/LibraryPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/orders", component: () => import("@/views/OrderPage.vue"),
        meta: { requiresAuth: true }
    },
    {
        path: "/orders/:id", component: () => import("@/views/OrderDetailPage.vue"),
        meta: { requiresAuth: true }
    },
    { path: '/products/:id', component: () => import('@/views/ProductDetailPage.vue') },
    { path: '/testing', component: () => import('@/views/TestingPage.vue') },
    { path: '/:pathMatch(.*)*', component: NotFoundPage },
];

const router = createRouter({
    //   history: createWebHashHistory(), // hash mode
    history: createWebHistory(),
    routes,
    scrollBehavior(to, from, savedPosition) {
        // 페이지 이동 시 페이지 top에서 시작
        return { top: 0 }
    }
});


// 라우팅 가드
router.beforeEach(async (to, from, next) => { // 페이지 이동하기 전에 호출

    const store = useTokenStore();
    // Wait for initial token check to complete
    if (store.isLoading) {
        // Create a promise that resolves when loading is complete
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