<template>
  <button class="cta__btn" @click="loginRouterPush">{{ msg }}</button>
</template>

<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();
const loginRouterPush = () => {
  // TODO: if user is logged in
  const isLoggedIn = false;
  if (isLoggedIn) {
    router.push({ path: "/journal" });
  } else {
    router.push({ path: "/login" });
  }
}

defineProps({
  msg: String
})
</script>

<style scoped>
.cta__btn {
  max-width: 32rem;
  width: 80%;
}
</style>