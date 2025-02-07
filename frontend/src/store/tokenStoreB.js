import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useTokenStore = defineStore("token", () => {
    const accessToken = ref("");

    const isLoggedIn = computed(() => {
        return !!accessToken.value;
    });

    const setAccessToken = (token) => {
        accessToken.value = token;
    };

    const getAccessToken = () => {
        return accessToken.value
    }

    return {
        accessToken,
        isLoggedIn,
        getAccessToken,
        setAccessToken
    }
});