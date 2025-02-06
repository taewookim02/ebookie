<template>
    <router-link :to="link" class="nav__item">
        <component :is="icon" :size="iconSize" />
        <p>{{ label }}</p>
    </router-link>
</template>
<script setup>
defineProps({
    link: {
        type: String,
        required: true,
    },
    label: {
        type: String,
        required: true,
    },
    icon: {
        type: Object,
        required: true,
    },
    iconSize: {
        type: Number,
        default: 16,
    }
    

})
</script>
<style scoped>
.nav__item {
    display: flex;
    align-items: center;
    gap: 4px;
}
</style>