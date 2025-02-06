<template>
    <div class="wrapper">
        <JournalNav />

        <main class="main">
            <slot></slot>
        </main>
    </div>
</template>

<script setup>
import JournalNav from '../sections/JournalNav.vue';
</script>

<style scoped>
.wrapper {
    display: grid;
    grid-template-columns: min-content 1fr;
    height: 100vh;
    gap: 0.8rem;
}

.main {
    display: flex;
    flex-direction: column;
    gap: 0.8rem;
}
</style>