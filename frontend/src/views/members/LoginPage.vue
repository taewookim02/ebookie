<script setup>
import { customAxios } from "@/plugins/axios";
import { onMounted, ref } from 'vue';
import GoogleAuthButton from '@/components/domain/member/GoogleAuthButton.vue';
import AuthInputField from '@/components/domain/member/AuthInputField.vue';
import ActionButton from '@/components/common/ActionButton.vue';
import router from '@/router';
import { useTokenStore } from '@/store/tokenStore';
import { useToast } from "vue-toastification";

// state
const email = ref("");
const password = ref("");
const errMsg = ref("");
const store = useTokenStore();
const isLoading = ref(false);
const hasError = ref(false);
const toast = useToast();

// actions
const handleLogin = async () => {
    try {
        isLoading.value = true;
        const res = await customAxios.post("/api/v1/auth/login", {
            email: email.value,
            password: password.value
        });
        toast.success("로그인 성공!");
        
        // 메모리에 토큰 저장
        store.setAccessToken(res.data.accessToken);
        // 홈 이동
        router.push("/");
    } catch (err) {
        console.log(err);
        hasError.value = true;
        // 에러 메세지
        errMsg.value = err.response.data.message;
    } finally {
        isLoading.value = false;
    }
}
</script>

<template>
    <div v-if="isLoading" class="loading">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>
    <!--
    <div v-else-if="hasError" class="empty-state">
        <i class="bi bi-exclamation-circle" style="font-size: 4rem;"></i>
        <h3 class="mt-4">로그인에 실패했습니다</h3>
        <p class="text-muted">{{ errMsg }}</p>
        <ActionButton class="mt-3" @action="hasError = false">다시 시도</ActionButton>
    </div>
    -->
    <section v-else class="auth">
        <form @submit.prevent="handleLogin" class="auth__form">
            <h1 class="auth__heading">로그인</h1>
            <!-- TODO: Google Login
            <GoogleAuthButton type="button" msg="Google 계정으로 로그인" />
            <span class="auth__or">or</span> 
            -->
            <AuthInputField type="email" id="email" name="email" label="이메일" v-model="email" :required="true" />
            <AuthInputField type="password" id="password" name="password" label="비밀번호" v-model="password" :required="true" />
            <button type="submit" class="auth__btn btn btn-primary btn-lg">로그인</button>
            <span class="auth__err">{{errMsg}}</span>

            <div class="auth__link">
                <!-- <RouterLink to="/reset-password" class="auth__link--item">비밀번호 찾기</RouterLink> -->
                <span>회원이 아니신가요?
                    <RouterLink to="/register" class="auth__link--item">회원가입</RouterLink>
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

.auth__err {
    color: red;
}

.loading,
.empty-state {
    min-height: 50vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    padding: 4rem;
    color: #6c757d;
}
</style>
