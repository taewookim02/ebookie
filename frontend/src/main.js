// 부트스트랩 
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

// 공통 css
import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'; // 라우터 설정

const app = createApp(App);
app.use(router); // 라우터 설정
app.mount('#app');
