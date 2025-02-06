import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';
import Home from "./views/HomePage.vue";
import NotFound from './views/NotFoundPage.vue';

const routes = [
    // TODO: maybe the lazy loading is showing another page for a split second
    { path: '/', component: Home },
    { path: '/login', component: () => import('./views/LoginPage.vue') },
    { path: '/signup', component: () => import('./views/SignUpPage.vue')},
    { path: '/journal', component: () => import('./views/JournalPage.vue'), meta: { hideNavFooter: true, noMainWrapper: true }},
    { path: '/journal/list', component: () => import('./views/JournalListPage.vue'), meta: { hideNavFooter: true, noMainWrapper: true }},
    { path: '/community', component: () => import('./views/CommunityPage.vue'), meta: { hideNavFooter: true, noMainWrapper: true }},
    { path: '/user/edit', component: () => import('./views/ProfilePage.vue'), meta: { hideAuthLinks: true }},
    { path: '/:pathMatch(.*)*', component: NotFound },
];

const router = createRouter({
  // history: createWebHashHistory(), // hash mode
  history: createWebHistory(),
  routes
});


export default router;