<script setup>
import AuthorDescription from '@/components/shared/AuthorDescription.vue';


</script>

<template>
    <section class="author-details">
        <h3>저자 소개</h3>
        <div class="author-details-container">
            <!-- TODO: pass props (name, imgPath, description) -->
            <AuthorDescription />
        </div>
    </section>
</template>

<style scoped>
.author-details {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
}

.author-details-container {
    padding: 0 2.4rem 6.4rem 2.4rem;
    display: flex;
    flex-direction: column;
    gap: 4rem;
    border-bottom: 1px solid #ccc;
}
</style>