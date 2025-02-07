import { defineStore } from "pinia";
import { ref } from "vue";
export const useCounterStore = defineStore('counter', () => {
    // state
    const count = ref(1);

    const doubleCount = () => {
        count.value *= 2;
    }

    return {
        count,
        doubleCount
    }
})