// store/memberStore.js
import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useMemberStore = defineStore("member", () => {
    const member = ref(null);
    const isLoading = ref(false);

    // getters
    const getMember = computed(() => member.value);
    const getMemberEmail = computed(() => member.value?.email);
    const getMemberId = computed(() => member.value?.id);
    const getMemberRole = computed(() => member.value?.role);

    // actions
    const setMember = (memberData) => {
        member.value = memberData;
    };
    const setLoading = (status) => {
        isLoading.value = status;
    };

    return {
        member,
        isLoading,
        getMember,
        getMemberEmail,
        getMemberId,
        getMemberRole,
        setMember,
        setLoading
    }
});