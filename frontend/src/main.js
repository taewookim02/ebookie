// 부트스트랩 
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

// pinia 상태관리
import { createPinia } from "pinia";

// 케러셀
// import VueSplide from "@splidejs/vue-splide";

// 공통 css
import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'; // 라우터 설정


const pinia = createPinia();
const app = createApp(App);
app.use(pinia);
// app.use(VueSplide);
app.use(router); // 라우터 설정
app.mount('#app');
