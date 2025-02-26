import { useTokenStore } from "@/store/tokenStore";
import axios from "axios";


const customAxios = axios.create({
    baseURL: window.location.origin, 
    // baseURL: "http://localhost:8080/", 
    timeout: 1000
});

customAxios.interceptors.request.use(
    (config) => {
        const store = useTokenStore();
        const token = store.accessToken;

        // this buggy bro
        if (token && !config.url.includes("/auth/")) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }
        
        return config;
    },
    (error) => {
        Promise.reject(error)
    }
)


export { customAxios };
