import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useTokenStore = defineStore("token", () => {
    // state
    const accessToken = ref("");

    // getter
    const getAccessToken = computed(() => accessToken.value);
    const isLoggedIn = computed(() => !!accessToken.value);

    // action
    const setAccessToken = (token) => {
        accessToken.value = token;
    };

    const setLoginResponse = (accessTokenParam) => {
        accessToken.value = accessTokenParam;
    };

    return {
        accessToken,
        getAccessToken,
        setAccessToken,
        setLoginResponse
    };
});