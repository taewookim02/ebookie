<script setup>
import { ref } from 'vue';
import AuthForm from '@/components/forms/AuthForm.vue';
const handleLogin = () => {
    console.log("login logic");
}
const email = ref("");
const password = ref("");
</script>

<template>
    <AuthForm heading="로그인" googleButtonText="Google 계정으로 로그인" submitButtonText="로그인"
        linkText="비밀번호 찾기" footerLink="/signup" footerLinkText="회원가입" footerText="회원이 아니신가요?" @submit="handleLogin" v-model:email="email" v-model:password="password" />
</template>

<style scoped></style>
