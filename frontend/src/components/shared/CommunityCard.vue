<template>
    <div class="community__card">
        <h3 class="community__card--heading">{{
            headingText }}</h3>
        <p>{{ paragraphText }}</p>
    </div>
</template>

<script setup>
defineProps({
    headingText: {
        type: String,
        required: true,
    },
    paragraphText: {
        type: String,
        required: true
    }
})
</script>

<style scoped>
.community__card {
    padding: 1.6rem;
    background-color: #f9f9f9;
    border: 1px solid #e0e0e0;
    border-radius: 1rem;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: 100%;
}


.community__card--heading {
    font-size: 2rem;
}
</style>