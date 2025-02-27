<script setup>
import Autocomplete from '@trevoreyre/autocomplete-vue'
import '@trevoreyre/autocomplete-vue/dist/style.css'
import { ref, onMounted } from 'vue';
import { customAxios } from '@/plugins/axios';
import { useRouter } from 'vue-router';

// state
const searchQuery = ref("");
const results = ref([]);
const router = useRouter();

// action
const search = async (input) => {
    try {
        const response = await customAxios.get(`/api/v1/products/search?query=${input}`);
        return response.data.map(item => ({
            ...item,
            displayText: `${item.name} - ${item.authorNames} (${item.category})`
        }));
    } catch (err) {
        console.error("Search error:", err);
        return [];
    }
}

const getResultValue = (result) => result.displayText;

// 연관단어
const handleSubmit = (result) => {
    if (result) {
        // Navigate to product detail page
        window.location.href = `/products/${result.id}`;
    }
}
// 검색
const handleEnter = (e) => {
    const searchValue = e.target.value;
    router.push(`/products/search/result?query=${searchValue}`);
}
</script>

<template>
    <autocomplete :search="search" :get-result-value="getResultValue" :debounce-time="300"
        placeholder="도서명, 저자, 카테고리로 검색" aria-label="도서 검색" @submit="handleSubmit" class="search-bar" @keyup.enter="handleEnter" />
</template>

<style scoped>

</style>