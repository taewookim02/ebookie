<script setup>
defineProps({
    id: {
        type: String,
        required: true,
    },
    label: {
        type: String,
        required: true,
    },
    type: {
        type: String,
        required: true,
    },
    name: {
        type: String,
        required: true,
    },
    readonly: {
        type: Boolean,
        default: false,
    },
    modelValue: {
        type: String,
        required: false,
        default: '',
    },
    required: {
        type: Boolean,
    }
});

const emit = defineEmits(["update:modelValue"]);
</script>

<template>
    <div class="input-field">
        <label :for="id" class="input-field__label">{{ label }}</label>
        <input :required="required" :type="type" :id="id" :name="name" class="input-field__input" :value="modelValue"
            @input="emit('update:modelValue', $event.target.value)" v-bind="readonly ? { readonly: true } : {}">
    </div>
</template>


<style scoped>
.input-field {
    position: relative;
    text-align: left;
    height: 5.6rem;
    width: 100%;
}

.input-field__label {
    position: absolute;
    top: 0.4rem;
    left: 0.8rem;
    font-size: 11px;
    color: var(--text-secondary-color);
    letter-spacing: 0.55px;
    line-height: 125%;
    background-color: transparent;
}

.input-field__input {
    width: 100%;
    height: 5.6rem;
    padding: 1.6rem 0.8rem 0.4rem;
    font-size: 1.8rem;
    border-radius: 6px;
    background-color: var(--surface-color);
    border: 1px solid var(--border-color);
    color: var(--text-color);
    font-weight: 320;
    transition: border-color 0.2s ease;

    &:read-only {
        border-color: var(--text-secondary-color);
        background-color: var(--surface-color);
        opacity: 0.7;
    }

    &:focus-visible {
        outline: none;
        border-color: var(--primary-color);
    }

    &:hover:not(:read-only) {
        border-color: var(--primary-hover);
    }
}
</style>
