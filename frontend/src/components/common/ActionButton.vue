<script setup>
defineProps({
    isActive: Boolean,
})

// emit
const emit = defineEmits(["action"]);

// actions
const handleClick = () => {
    emit("action")
}
</script>

<template>
    <button class="btn-action" :class="{ active: isActive }" @click="handleClick">
        <slot></slot>
    </button>
</template>

<style scoped>
.btn-action {
    padding: 1.2rem;
    border: 1px solid var(--primary-color);
    border-radius: 0.6rem;
    background-color: transparent;
    color: var(--primary-color);
    font-weight: 500;
    font-size: 1.6rem;
    cursor: pointer;
    transition: all 0.2s ease;
    flex-grow: 1;
    max-width: 14.4rem;
    max-height: 6.4rem;
    word-break: keep-all;
}

.btn-action:hover {
    background-color: var(--surface-color);
    border-color: var(--primary-hover);
    color: var(--primary-hover);
}

.btn-action:active {
    background-color: var(--surface-color);
    border-color: var(--primary-active);
    color: var(--primary-active);
}

.btn-action.active {
    background-color: var(--primary-color);
    color: var(--background-color);
}

.btn-action.active:hover {
    background-color: var(--primary-hover);
    border-color: var(--primary-hover);
}

.btn-action.active:active {
    background-color: var(--primary-active);
    border-color: var(--primary-active);
}
</style>