<script setup>
import AuthForm from '@/components/forms/AuthForm.vue';
</script>


<template>
  <AuthForm heading="회원가입" googleButtonText="Google 계정으로 계속하기" submitButtonText="회원가입"
    footerLink="/login" footerLinkText="로그인" footerText="회원이신가요?" :isSignUp="true" />
</template>

<style scoped></style>