<script setup>
defineProps({
    detailDto: Object
});

</script>

<template>
    <section class="author-details">
        <h3>저자 소개</h3>
        <div class="author-details-container" v-for="author in detailDto.authors">
            <div class="author-container">
                <div class="author-header">
                    <span class="author-header__name">{{ author.name }} 저</span>
                </div>
                <div class="author-body">
                    <p>{{ author.description }}</p>
                </div>
            </div>
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

.author-container {
    display: flex;
    flex-direction: column;
    gap: 1.6rem;
}

.author-header__name {
    font-weight: 700;
}

.author-body {
    display: flex;
    gap: 2.4rem;
}

.author-img {
    width: 112px;
    height: auto;
    border-radius: 50%;
}
</style>