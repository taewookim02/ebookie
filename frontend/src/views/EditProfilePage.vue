<template>
  <section class="settings">
    <div class="settings__controls">
      <RouterLink class="settings__controls--link link-dark" to="/">
        <PhArrowLeft :size="32" />
        <span class="settings__controls--text">뒤로가기</span>
      </RouterLink>
    </div>
    <form action="" class="settings__form">

      <h1>회원 정보 수정</h1>
      <AuthInputField type="email" id="email" name="email" label="이메일" valueText="hello" :readonly="true" />
      <AuthInputField type="password" id="password" name="password" label="새로운 비밀번호" />
      <AuthInputField type="password" id="confirm-password" name="confirm-password" label="새로운 비밀번호 확인" />
      <AuthInputField type="password" id="current-password" name="current-password" label="현재 비밀번호" />
      <button type="submit">Update</button>
    </form>
  </section>

</template>

<script setup>
import AuthInputField from '@/components/forms/AuthInputField.vue';
import { PhArrowLeft } from '@phosphor-icons/vue';
import { onMounted } from 'vue';


onMounted(() => {
    console.log("페이지 로드되면 'Bearer: jwt 토큰' 회원정보 요청");
    console.log("응답 오면 값 설정");
    // 서버
})
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