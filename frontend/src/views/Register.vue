<script setup>
import axios from 'axios';
import { ref } from 'vue';
import GoogleAuthButton from '@/components/shared/GoogleAuthButton.vue';
import AuthInputField from '@/components/forms/AuthInputField.vue';


const email = ref("");
const password = ref("");
const confirmPassword = ref("");
const errMsg = ref("");

const handleSubmit = (e) => {
    e.preventDefault();

    axios.post("http://localhost:8080/api/auth/register", {
        email: email.value,
        password: password.value,
        confirmPassword: confirmPassword.value
    }).then(res => {
        console.log(res);
        // 서버에서 온 토큰 로컬스토리지 저장
        localStorage.setItem("accessToken", res.data.accessToken);

        // 보호된 페이지로 이동
        router.push("/user/edit")
    }).catch(err => {
        console.log(err);
        // 에러 메세지 
        errMsg.value = err.response.data.message;
    });
}
</script>

<template>
    <section class="auth">
        <form @submit="handleSubmit" class="auth__form">
            <h1 class="auth__heading">회원가입</h1>
            <GoogleAuthButton type="button" msg="Google 계정으로 회원가입" />
            <span class="auth__or">or</span>
            <AuthInputField type="email" id="email" name="email" label="이메일" v-model="email" />
            <AuthInputField type="password" id="password" name="password" label="비밀번호" v-model="password" />
            <AuthInputField type="password" id="confirmPassword" name="confirmPassword" label="비밀번호 확인"
                v-model="confirmPassword" />
            <button type="submit" class="auth__btn">로그인</button>
            <span class="auth__err">{{ errMsg }}</span>

            <div class="auth__link">
                <span>이미 가입하셨나요?
                    <RouterLink to="/login" class="auth__link--item">로그인</RouterLink>
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
</style>
