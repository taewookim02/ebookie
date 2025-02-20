<script setup>
import { ref } from 'vue';
import { customAxios } from '@/plugins/axios';

// should fetch book images, book title, book author, book id from server
const libraryDto = ref([]);

// actions
const fetchLibrary = async () => {
    const res = await customAxios.get(`/api/v1/library`);
    libraryDto.value = res.data;
}
fetchLibrary();

</script>

<template>
    <div>
        <h1>Library</h1>

        
    </div>
</template>


<style scoped>

</style>

