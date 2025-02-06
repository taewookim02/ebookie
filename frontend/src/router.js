import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';
import Home from "./views/HomePage.vue";
import NotFound from './views/NotFoundPage.vue';

const routes = [
    // TODO: maybe the lazy loading is showing another page for a split second
    { path: '/', component: Home },
    { path: '/login', component: () => import('./views/Login.vue') },
    { path: '/register', component: () => import('./views/Register.vue')},
    { path: '/user/edit', component: () => import('./views/EditProfile.vue'), meta: { hideAuthLinks: true }},
    { path: '/:pathMatch(.*)*', component: NotFound },
];

const router = createRouter({
//   history: createWebHashHistory(), // hash mode
  history: createWebHistory(),
  routes
});


export default router;