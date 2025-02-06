<script setup>
import GoogleAuthButton from '../shared/GoogleAuthButton.vue';
import AuthInputField from './AuthInputField.vue';

defineProps({
  heading: {
    type: String,
    required: true,
  },
  googleButtonText: {
    type: String,
    required: true,
  },
  submitButtonText: {
    type: String,
    required: true,
  },
  linkText: {
    type: String,
    // default: ''
  },
  footerText: {
    type: String,
    required: true
  },
  footerLinkText: {
    type: String,
    required: true,
  },
  footerLink: {
    type: String,
    required: true,
  },
  isSignUp: {
    type: Boolean,
    default: false,
  },
})
</script>

<template>
  <section class="auth">
    <form action="" class="auth__form">
      <h1 class="auth__heading">{{ heading }}</h1>
      <GoogleAuthButton type="button" :msg="googleButtonText" />
      <span class="auth__or">or</span>
      <AuthInputField type="email" id="email" name="email" label="이메일" />
      <AuthInputField type="password" id="password" name="password" label="비밀번호" />
      <AuthInputField v-if="isSignUp" type="password" id="confirm-password" name="confirm-password"
        label="비밀번호 확인" />
      <button type="submit" class="auth__btn">{{ submitButtonText }}</button>
      <div class="auth__link">
        <RouterLink v-if="!isSignUp" to="/reset-password" class="auth__link--item">{{ linkText }}</RouterLink>
        <span>{{ footerText }}
          <RouterLink :to="footerLink" class="auth__link--item">{{ footerLinkText }}</RouterLink>
        </span>
      </div>
    </form>
  </section>
</template>


<style scoped>
.auth {
  margin: auto;
  max-width: 38.4rem;
  width: 100%;
}

.auth__form {
  display: flex;
  flex-direction: column;
  gap: 2.4rem;
  align-items: center;
  border: 1px sold black;
}

.auth__link {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2.4rem;
}
.auth__btn {
    width: 100%;
    cursor: pointer;
    padding: .8rem;
}
</style>
