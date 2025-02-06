<script setup>
import AuthForm from '@/components/forms/AuthForm.vue';
</script>

<template>
  <AuthForm heading="로그인" googleButtonText="구글로 로그인하기" submitButtonText="로그인"
    linkText="Reset password" footerLink="/signup" footerLinkText="Create one" footerText="No account?" />
</template>

<style scoped></style>
