import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useTokenStore = defineStore("token", () => {
    // state
    const accessToken = ref("");
    const isLoading = ref(true);
    const userEmail = ref("");

    // getter
    const isLoggedIn = computed(() => {
        return !!accessToken.value;
    });
    const getAccessToken = computed(() => {
        return accessToken.value
    });
    const getIsLoading = computed(() => {
        return isLoading.value;
    })


    // actions
    const setAccessToken = (token) => {
        accessToken.value = token;
    };
    const setLoading = (status) => {
        isLoading.value = status;
    }


    return {
        accessToken,
        isLoading,
        isLoggedIn,
        getAccessToken,
        getIsLoading,
        setAccessToken,
        setLoading,
    }
});