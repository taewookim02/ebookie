<script setup>
import AuthForm from '@/components/forms/AuthForm.vue';
</script>


<template>
  <AuthForm heading="Create account" googleButtonText="Sign up with Google" submitButtonText="Sign up"
    footerLink="/login" footerLinkText="Sign in" footerText="Already a member?" :isSignUp="true" />
</template>

<style scoped></style>