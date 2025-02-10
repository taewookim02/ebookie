
<script setup>
import AuthInputField from '@/components/forms/AuthInputField.vue';
import { PhArrowLeft } from '@phosphor-icons/vue';
import { onMounted, ref } from 'vue';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';
// 상태
const email = ref("");
const newPassword = ref("");
const newPasswordConfirm = ref("");
const currentPassword = ref("");
const errMsg = ref("");
// 액션
const handleUpdate = () => {
    console.log(email, newPassword, newPasswordConfirm, currentPassword);

    // 유효성 검사
    if (newPassword.value !== newPasswordConfirm.value) {
        errMsg.value = "새 비밀번호와 새 비밀번호 확인이 다릅니다.";
        return;
    }

    customAxios.patch("/api/member/update", {
        email: email.value,
        newPassword: newPassword.value,
        newPasswordConfirm: newPasswordConfirm.value,
        currentPassword: currentPassword.value
    }).then(res => {
        console.log(res);
    }).catch(err => {
        console.log("err:", err);
        // 비밀번호, 이메일 틀렸을 시 
        errMsg.value = err.response.data.message;
    })

}


// 라이프사이클 훅
onMounted(() => {
    // 로그인 유저 정보
    customAxios.get("/api/member/detail")
    .then(res => {
        email.value = res.data.email;
    }).catch(err => {
        console.log(err);
    })
})
</script>

<template>
  <section class="settings">
    <div class="settings__controls">
      <RouterLink class="settings__controls--link link-dark" to="/">
        <PhArrowLeft :size="32" />
        <span class="settings__controls--text">뒤로가기</span>
      </RouterLink>
    </div>
    <form @submit.prevent="handleUpdate" class="settings__form">

      <h1>회원 정보 수정</h1>
      <AuthInputField type="email" id="email" name="email" label="이메일" valueText="hello" :readonly="true" v-model="email"  />
      <AuthInputField type="password" id="password" name="password" label="새로운 비밀번호" :required="true" v-model="newPassword" />
      <AuthInputField type="password" id="confirm-password" name="confirm-password" label="새로운 비밀번호 확인" :required="true" v-model="newPasswordConfirm" />
      <AuthInputField type="password" id="current-password" name="current-password" label="현재 비밀번호" :required="true" v-model="currentPassword" />
      <button type="submit">Update</button>
      <span class="auth__err" style="color: red;">{{errMsg}}</span>
    </form>
  </section>

</template>


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