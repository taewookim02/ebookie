<script setup>
import { VueSpinner } from 'vue3-spinners';
</script>

<template>
    <div class="loading">
        <VueSpinner size="32" color="#ccc"/>
    </div>
</template>

<style scoped>
.loading {
    display: grid;
    place-items: center;
}
</style>

