<template>
    <JournalWrapper>
        <section class="journal">
            <div class="journal__item">
                <span>1</span>
                <span class="journal__item--title">title</span>
                <span class="journal__item--date">2024-10-11</span>
            </div>
            <div class="journal__item">
                <span>1</span>
                <span class="journal__item--title">Today was awesome and I had to ....</span>
                <span class="journal__item--date">2024-10-11</span>
            </div>
            <div class="journal__item">
                <span>1</span>
                <span class="journal__item--title">title</span>
                <span class="journal__item--date">2024-10-11</span>
            </div>
            <div class="journal__item">
                <span>1</span>
                <span class="journal__item--title">title</span>
                <span class="journal__item--date">2024-10-11</span>
            </div>
        </section>
    </JournalWrapper>
</template>

<script setup>
import JournalWrapper from '@/components/shared/JournalWrapper.vue';

</script>

<style scoped>
body {
    padding: 0;
}

.container {
    display: grid;
    grid-template-columns: min-content 1fr;
    max-height: 100vh;
}

.journal {
    display: flex;
    flex-direction: column;
    border: 1px solid #333;
    padding: 1.6rem;
    gap: 0.8rem;
    border-radius: 0.8rem;
}

.journal__nav {
    display: flex;
    align-items: center;
    gap: 1.6rem;
}

.journal__item {
    display: grid;
    gap: 1.6rem;
    grid-template-columns: min-content 1fr min-content;
}

.journal__item--title {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.journal__item--date {
    white-space: nowrap;
}
</style>