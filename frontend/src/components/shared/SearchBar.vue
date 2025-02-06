<template>
    <form @submit.prevent="handleSearch" id="search-form">
        <div class="input-group rounded">
            <input type="search" v-model="searchQuery" class="form-control rounded" placeholder="Search" aria-label="Search"
                aria-describedby="search-addon" />
            <button class="input-group-text border-0" id="search-addon">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#333333" viewBox="0 0 256 256">
                    <path
                        d="M229.66,218.34l-50.07-50.06a88.11,88.11,0,1,0-11.31,11.31l50.06,50.07a8,8,0,0,0,11.32-11.32ZM40,112a72,72,0,1,1,72,72A72.08,72.08,0,0,1,40,112Z">
                    </path>
                </svg>
            </button>
        </div>
    </form>
</template>

<script setup>
import { ref } from 'vue';

// state
const searchQuery = ref("");


// action
const handleSearch = () => {
    console.log("검색!:", searchQuery);    
}
</script>

<style scoped></style>