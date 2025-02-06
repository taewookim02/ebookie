<template>
    <JournalWrapper>
        <section class="journal">
            <div class="journal__nav">
                <div class="journal__nav--controls">
                    <PhCaretLeft :size="32" color="#333" class="journal__nav--left" />
                    <PhCaretRight :size="32" color="#333" class="journal__nav--right" />
                </div>
                <h2 class="journal__nav--heading">Today</h2>
            </div>
            <input type="text" class="journal__title" name="journal__title"
                placeholder="this is where the title is, it should be editable on click">
            <textarea name="journal__content" id="journal__content"
                class="journal__content">this is where the textarea is</textarea>
        </section>
    </JournalWrapper>
</template>

<script setup>
import JournalWrapper from '@/components/shared/JournalWrapper.vue';
import { PhCaretLeft, PhCaretRight, PhUsersThree, PhBookOpenText, PhGearSix, PhDownloadSimple, PhCoffee, PhUser } from '@phosphor-icons/vue';
</script>


<style scoped>
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

.journal__nav--controls {
    display: flex;
    align-items: center;
}

.journal__content {
    resize: none;
    overflow: hidden;
    width: 100%;
    min-height: 25.6rem;
}
</style>