<script setup>
defineProps({
    text: String,
    isActive: Boolean
})

const emit = defineEmits(["action"]);

const handleClick = () => {
    emit("action")
}
</script>

<template>
    <button class="btn-action" :class="{ active: isActive }" @click="handleClick">{{ text }}</button>
</template>

<style scoped>
.btn-action {
    padding: 4px 16px;
    border: 1px solid #333333;
    border-radius: 4px;
}

.btn-action.active {
    background-color: #333333;
    color: #fff;
}
</style>