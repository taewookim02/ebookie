import { useTokenStore } from "@/store/tokenStore";
import axios from "axios";


const customAxios = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 1000
});

customAxios.interceptors.request.use(
    (config) => {
        const store = useTokenStore();
        const token = store.accessToken;
        console.log("전역 axios interceptors 시작");
        console.log("token:", token);

        // this buggy bro
        if (token && !config.url.includes("/auth/")) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }

        // this buggy
        console.log(config.headers['Authorization']);

        return config;
    },
    (error) => {
        Promise.reject(error)
    }
)


export { customAxios };
