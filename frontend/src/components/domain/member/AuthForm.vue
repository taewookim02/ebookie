<script setup>
import GoogleAuthButton from '@/components/domain/member/GoogleAuthButton.vue';
import AuthInputField from '@/components/domain/member/AuthInputField.vue';

const props = defineProps({
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
  email: String,
  password: String,
});

// form submit handling
const emit = defineEmits(["submit", "update:email", "update:password"]);
const handleSubmit = (e) => {
    e.preventDefault();
    emit('submit')
}

</script>

<template>
  <section class="auth">
    <form @submit="handleSubmit" class="auth__form">
      <h1 class="auth__heading">{{ heading }}</h1>
      <GoogleAuthButton type="button" :msg="googleButtonText" />
      <span class="auth__or">or</span>
      <AuthInputField type="email" id="email" name="email" label="이메일" v-model:email="props.email" />
      <AuthInputField type="password" id="password" name="password" label="비밀번호" v-model:password="props.password" />
      <!-- <AuthInputField v-if="isSignUp" type="password" id="confirmPassword" name="confirmPassword"
        label="비밀번호 확인" v-model:confirmPassword="props?.confirmPassword" /> -->
      <ActionButton type="submit" class="auth__btn">{{ submitButtonText }}</ActionButton>
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
  padding: 3.2rem 2.4rem;
  background-color: var(--background-color);
  border-radius: 0.8rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.auth__form {
  display: flex;
  flex-direction: column;
  gap: 2.4rem;
  align-items: center;
}

.auth__heading {
  color: var(--text-color);
  font-size: 2.4rem;
  font-weight: 600;
}

.auth__or {
  color: var(--text-secondary-color);
  font-size: 1.4rem;
  position: relative;
  width: 100%;
  text-align: center;
}

.auth__or::before,
.auth__or::after {
  content: '';
  position: absolute;
  top: 50%;
  width: 45%;
  height: 1px;
  background-color: var(--border-color);
}

.auth__or::before {
  left: 0;
}

.auth__or::after {
  right: 0;
}

.auth__link {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2.4rem;
  color: var(--text-secondary-color);
  font-size: 1.4rem;
}

.auth__link--item {
  color: var(--primary-color);
  text-decoration: none;
  font-weight: 500;
}

.auth__link--item:hover {
  color: var(--primary-hover);
  text-decoration: underline;
}

.auth__btn {
  width: 100%;
  cursor: pointer;
  padding: 1.2rem;
  background-color: var(--primary-color);
  color: white;
  border: none;
  border-radius: 0.6rem;
  font-weight: 500;
  font-size: 1.6rem;
  transition: background-color 0.2s ease;
}

.auth__btn:hover {
  background-color: var(--primary-hover);
}

.auth__btn:active {
  background-color: var(--primary-active);
}
</style>
