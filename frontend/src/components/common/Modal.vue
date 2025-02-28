<!-- Modal.vue -->
<script setup>
import { PhX } from '@phosphor-icons/vue';
import { ref } from 'vue';

// State
const dialog = ref(null);
defineProps({
    title: String
})
// Actions
const handleBackdropClick = (e) => {
    const dialogDimensions = dialog.value.getBoundingClientRect();
    if (
        e.clientX < dialogDimensions.left ||
        e.clientX > dialogDimensions.right ||
        e.clientY < dialogDimensions.top ||
        e.clientY > dialogDimensions.bottom
    ) {
        dialog.value.close();
    }
}

const showModal = () => {
    if (dialog.value) {
        dialog.value.showModal();
    }
}

const closeModal = () => {
    if (dialog.value) {
        dialog.value.close();
    }
}

defineExpose({ showModal, closeModal });
</script>

<template>
    <dialog class="modal" ref="dialog" @click="handleBackdropClick">
        <div class="modal__container" @click.stop>
            <div class="modal__header">
                <h3>{{title}}</h3>
                <button class="close-button" @click="closeModal">
                    <PhX />
                </button>
            </div>
            <div class="modal__body">
                <slot></slot>
            </div>
        </div>
    </dialog>
</template>

<style scoped>
dialog::backdrop {
    background-color: rgba(0, 0, 0, 0.5);
}

dialog[open] {
    display: block;
    opacity: 1;
    visibility: visible;
    pointer-events: auto;
}

.modal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin: 0;
    padding: 20px;
    border-radius: 8px;
    min-width: 32rem;
    width: fit-content;
    height: fit-content;
    border: none;
}

.modal__container {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
}

.modal__header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.close-button {
    cursor: pointer;
    border: none;
    color: var(--muted-color);
    &:hover {
        color: var(--text-color);
    }
}

:deep(.modal__body) {
    & input {
        border-radius: 8px;
        font-size: 1.4rem;
        padding: 8px;
    }
    & button {
        width: 16rem;
        padding: .8rem 1.6rem;
        align-self: center;
        border: 1px solid var(--border-color);
        background-color: var(--primary-color);
        color: #fff;
        border-radius: 8px;
    }
    
}


</style>