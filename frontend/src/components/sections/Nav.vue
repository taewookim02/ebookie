<template>
  <nav class="nav">
    <RouterLink to="/" class="nav__link">
      <img src="../../assets/logo.png" alt="logo img" class="nav__logo">
    </RouterLink>
    <div v-if="!hideAuthLinks" class="nav__links">
      <RouterLink to="/login">로그인</RouterLink> |
      <RouterLink to="/signup">회원가입</RouterLink>
    </div>
  </nav>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
const route = useRoute();
const hideAuthLinks = computed(() => route.meta.hideAuthLinks);
</script>

<style scoped>
/* NAV */
.nav {
  padding: 2.4rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.nav__logo {
  max-height: 3.2rem;
  width: auto;
  display: block;
  cursor: pointer;
}

.nav__links {
  display: flex;
  gap: 2.4rem;
}
</style>