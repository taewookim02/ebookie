import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useTokenStore = defineStore("token", () => {
    // state
    const accessToken = ref("");
    const accessTokenExpiration = ref("");
    const refreshTokenExpiration = ref("");


    // getter
    const getAccessToken = computed(() => accessToken.value);

    // action
    const setAccessToken = (token) => {
        accessToken.value = token;
    };

    const setLoginResponse = (accessTokenParam, accessTokenExpirationParam) => {
        accessToken.value = accessTokenParam;
        accessTokenExpiration = new Date(accessTokenExpirationParam);
    };

    return {
        accessToken,
        accessTokenExpiration,
        refreshTokenExpiration,
        getAccessToken,
        setAccessToken,
        setLoginResponse
    };
});