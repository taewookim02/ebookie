import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';
import Home from "./views/HomePage.vue";
import NotFound from './views/NotFoundPage.vue';

// URL 목록
const routes = [
    // TODO: maybe the lazy loading is showing another page for a split second
    { path: '/', component: Home },
    { path: '/login', component: () => import('./views/LoginPage.vue') },
    { path: '/register', component: () => import('./views/RegisterPage.vue')},
    { path: '/user/edit', component: () => import('./views/EditProfilePage.vue'), meta: { requiresAuth: true, hideAuthLinks: true }},
    { path: '/:pathMatch(.*)*', component: NotFound },
];

const router = createRouter({
//   history: createWebHashHistory(), // hash mode
  history: createWebHistory(),
  routes
});

// 라우팅 가드
router.beforeEach((to, from, next) => { // 페이지 이동하기 전에 호출되는 함수
    const token = localStorage.getItem("accessToken");
    const isLoggedIn = !!token;
    console.log("token:", token);

    if (["/login", "/register"].includes(to.path) && isLoggedIn) {
        next("/")
    } else if (to.meta.requiresAuth && !token) {
        next("/login"); // 로그인 페이지로 이동
    } else {
        next(); // 그대로 이동
    }
})


export default router;