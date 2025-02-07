import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useTokenStore = defineStore("token", () => {
    // state
    const accessToken = ref("");

    // getter
    const isLoggedIn = computed(() => {
        return !!accessToken.value;
    });
    const getAccessToken = computed(() => {
        return accessToken.value
    });


    // actions
    const setAccessToken = (token) => {
        accessToken.value = token;
    };


    return {
        accessToken,
        isLoggedIn,
        getAccessToken,
        setAccessToken
    }
});