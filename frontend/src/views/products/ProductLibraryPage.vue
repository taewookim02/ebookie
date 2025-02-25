<script setup>
import { onMounted, ref } from 'vue';
import { customAxios } from '@/plugins/axios';
import { getImageFromServer } from '@/helper/imgPath';
import ActionButton from '@/components/common/ActionButton.vue';
import { useToast } from 'vue-toastification';
import Pagination from '@/components/common/Pagination.vue';
import { PhDownloadSimple } from '@phosphor-icons/vue';

const libraryPageDto = ref({
    libraryDtos: [],
    totalPages: 0,
    totalElements: 0,
    currentPage: 0
});
const currentPage = ref(0);
const pageSize = ref(10);
const pageSizeOptions = [5, 10, 20, 50];
const toast = useToast();

// actions
const fetchLibrary = async (page = 0) => {
    try {
        const res = await customAxios.get(`/api/v1/products/library?page=${page}&size=${pageSize.value}`);
        libraryPageDto.value = res.data;
        currentPage.value = res.data.currentPage;
    } catch (err) {
        console.log("fetchLibrary() err:", err);
        toast.error("라이브러리 목록을 불러올 수 없습니다.");
    }
}

const handlePageChange = (page) => {
    fetchLibrary(page - 1);
}

const handlePageSizeChange = () => {
    currentPage.value = 0;
    fetchLibrary(0);
}

const handleDownload = async (productId) => {
    try {
        const res = await customAxios.get(`/api/v1/products/files/download/${productId}`, {
            responseType: 'arraybuffer'
        });
        
        const blob = new Blob([res.data], { type: 'application/zip' });
        
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'files.zip';  
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
        
        toast.success("다운로드가 시작됩니다.");
    } catch (err) {
        console.log("handleDownload() err:", err);
        toast.error("다운로드에 실패했습니다.");
    }
}

onMounted(() => {
    fetchLibrary();
});

</script>

<template>
    <div class="library">
        <h1 class="mb-4">내 서재</h1>

        <div class="page-controls">
            <select v-model="pageSize" @change="handlePageSizeChange">
                <option v-for="size in pageSizeOptions" :key="size" :value="size">
                    {{ size }}개씩 보기
                </option>
            </select>
        </div>

        <div v-if="libraryPageDto.libraryDtos.length === 0" class="empty-state">
            <p>구매한 도서가 없습니다.</p>
        </div>

        <div v-else class="library-grid">
            <div v-for="product in libraryPageDto.libraryDtos" :key="product.productId" class="library-item">
                <RouterLink :to="`/products/${product.productId}`">
                    <img :src="getImageFromServer(product.thumbnailUrl)" :alt="product.title"
                        class="library-item__image">
                </RouterLink>
                <div class="library-item__info">
                    <h3>{{ product.title }}</h3>
                    <ActionButton @action="() => handleDownload(product.productId)" class="download-btn">
                        <PhDownloadSimple /> 다운로드
                    </ActionButton>
                </div>
            </div>
        </div>

        <Pagination v-if="libraryPageDto.totalPages > 1" :current-page="currentPage + 1"
            :total-pages="libraryPageDto.totalPages" @page-change="handlePageChange" />
    </div>
</template>

<style scoped>
.library {
    padding: 20px;
}

.page-controls {
    margin-bottom: 1rem;
}

.page-controls select {
    padding: 0.5rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    background: white;
}

.empty-state {
    text-align: center;
    padding: 40px;
    color: #666;
}

.library-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 20px;
}

.library-item {
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 15px;
    text-align: center;
}

.library-item__image {
    width: 100%;
    height: 200px;
    object-fit: contain;
    border-radius: 4px;
    margin-bottom: 10px;
}

.library-item__info h3 {
    font-size: 1.1rem;
    margin-bottom: 5px;
}

.library-item__info p {
    color: #666;
    margin-bottom: 10px;
}

.download-btn {
    width: 100%;
}
</style>
