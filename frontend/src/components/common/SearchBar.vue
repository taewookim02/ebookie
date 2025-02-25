<script setup>
import Autocomplete from '@trevoreyre/autocomplete-vue'
import '@trevoreyre/autocomplete-vue/dist/style.css'
import { ref } from 'vue';
import { customAxios } from '@/plugins/axios';

// state
const searchQuery = ref("");
const results = ref([]);

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

const handleSubmit = (result) => {
    if (result) {
        // Navigate to product detail page
        window.location.href = `/products/${result.id}`;
    }
}
</script>

<template>
    <autocomplete :search="search" :get-result-value="getResultValue" :debounce-time="300"
        placeholder="도서명, 저자, 카테고리로 검색" aria-label="도서 검색" @submit="handleSubmit" class="search-bar" />
</template>

<style scoped>
/* .search-bar {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
}

.search-bar :deep(.autocomplete-input) {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

.search-bar :deep(.autocomplete-result-list) {
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-top: 4px;
  padding: 0;
  background: white;
}

.search-bar :deep(.autocomplete-result) {
  padding: 8px 12px;
  cursor: pointer;
}

.search-bar :deep(.autocomplete-result:hover) {
  background-color: #f5f5f5;
} */
</style>