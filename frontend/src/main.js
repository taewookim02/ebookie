// 라이브러리
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import { createPinia } from "pinia";
import Toast, { TYPE } from "vue-toastification";
import "vue-toastification/dist/index.css";

import './assets/main.css' // 공통 css
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'; // 라우터 설정

const toastOptions = {
    toastDefaults: {
        // ToastOptions object for each type of toast
        [TYPE.ERROR]: {
            timeout: 3000,
        },
        [TYPE.SUCCESS]: {
            timeout: 3000,
        },
        [TYPE.INFO]: {
            timeout: 3000,
        },
    },
    transition: "Vue-Toastification__fade",
    position: "bottom-right",
};

const pinia = createPinia();
const app = createApp(App);
app.use(pinia);
app.use(router); // 라우터 설정
app.use(Toast, toastOptions);
app.mount('#app');
