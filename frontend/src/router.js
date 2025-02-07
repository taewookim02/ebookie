import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';
import Home from "./views/HomePage.vue";
import NotFoundPage from './views/NotFoundPage.vue';
import { useTokenStore } from './store/tokenStoreB';

// URL 목록
const routes = [
    // TODO: maybe the lazy loading is showing another page for a split second
    { path: '/', component: Home },
    { path: '/login', component: () => import('./views/LoginPage.vue') },
    { path: '/register', component: () => import('./views/RegisterPage.vue')},
    { path: '/user/edit', component: () => import('./views/EditProfilePage.vue'), meta: { requiresAuth: true, hideAuthLinks: true }},
    {path: '/testing', component: () => import('@/views/TestingPage.vue')},
    { path: '/:pathMatch(.*)*', component: NotFoundPage },
];

const router = createRouter({
//   history: createWebHashHistory(), // hash mode
  history: createWebHistory(),
  routes
});


// 라우팅 가드
router.beforeEach((to, from, next) => { // 페이지 이동하기 전에 호출되는 함수
    const store = useTokenStore();
    const isLoggedIn = store.isLoggedIn;

    if (["/login", "/register"].includes(to.path) && isLoggedIn) {
        next("/") // 로그인 되어있으면 홈페이지로 이동
    } else if (to.meta.requiresAuth && !isLoggedIn) {
        next("/login"); // 로그인 안되어있으면 로그인 페이지로 이동
    } else {
        next(); // 그대로 이동
    }
})


export default router;