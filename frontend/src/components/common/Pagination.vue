<script setup>
// State
const props = defineProps({
    currentPage: {
        type: Number,
        required: true
    },
    totalPages: {
        type: Number,
        required: true
    }
});

// Emit
const emit = defineEmits(['page-change']);

// Actions
const changePage = (page) => {
    emit('page-change', page);
};
</script>

<template>
    <div class="pagination">
        <button :disabled="currentPage === 1" @click="changePage(currentPage - 1)">&lt;</button>

        <button v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }"
            @click="changePage(page)">
            {{ page }}
        </button>

        <button :disabled="currentPage === totalPages" @click="changePage(currentPage + 1)">&gt;</button>
    </div>
</template>

<style scoped>
.pagination {
    display: flex;
    gap: 0.5rem;
    justify-content: center;
    margin: 1rem 0;
}

button {
    padding: 0.5rem 1rem;
    border: 1px solid #ddd;
    background: white;
    cursor: pointer;
}

button:disabled {
    cursor: not-allowed;
    opacity: 0.5;
}

button.active {
    background: #007bff;
    color: white;
    border-color: #007bff;
}
</style>