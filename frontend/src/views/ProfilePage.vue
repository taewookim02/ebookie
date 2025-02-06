<template>
  <section class="settings">
    <div class="settings__controls">
      <RouterLink class="settings__controls--link" to="/journal">
        <PhArrowLeft :size="32" />
        <span class="settings__controls--text">back</span>
      </RouterLink>
    </div>
    <form action="" class="settings__form">

      <h1>User Settings</h1>
      <AuthInputField type="email" id="email" name="email" label="email" valueText="hello" :readonly="true" />
      <AuthInputField type="password" id="password" name="password" label="new password" />
      <AuthInputField type="password" id="confirm-password" name="confirm-password" label="confirm password" />
      <AuthInputField type="password" id="current-password" name="current-password" label="current password" />
      <button type="submit">Update</button>
    </form>
  </section>

</template>

<script setup>
import AuthInputField from '@/components/forms/AuthInputField.vue';
import { PhArrowLeft } from '@phosphor-icons/vue';
</script>

<style scoped>
.settings {
  margin: auto;
  max-width: 38.4rem;
  width: 100%;
}

.settings__form {
  display: flex;
  flex-direction: column;
  gap: 2.4rem;
  align-items: center;
  border: 1px sold black;
}

.settings__controls--link {
  display: flex;
  align-items: center;
  gap: 0.8rem;
}
</style>