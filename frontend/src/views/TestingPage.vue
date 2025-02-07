<template>
<h1>Hello</h1>
<p>And the value is : {{ counter.count }}</p>
<button @click="handleClick">hey</button>
</template>

<script setup>
import { useCounterStore } from '@/store/counter';

const counter = useCounterStore();


const handleClick = () => {
    counter.doubleCount();
}
</script>

<style scoped>
</style>