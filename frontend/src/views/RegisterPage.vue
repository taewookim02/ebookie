<script setup>
import { ref } from 'vue';
import GoogleAuthButton from '@/components/shared/GoogleAuthButton.vue';
import AuthInputField from '@/components/forms/AuthInputField.vue';
import router from '@/router';
import { useTokenStore } from '@/store/tokenStore';
import { customAxios } from '@/plugins/axios';

// state
const email = ref("");
const password = ref("");
const confirmPassword = ref("");
const errMsg = ref("");
const store = useTokenStore();

const handleSubmit = (e) => {
    e.preventDefault();

    // if password.value.length <8 {
    if (password.value.length < 8) {
        errMsg.value = "비밀번호는 최소 8글자여야 합니다.";
        return;
    }

    // if password != confirmPassword
    if (password.value !== confirmPassword.value) {
        errMsg.value = "비밀번호와 비밀번호 확인이 다릅니다.";
        return;
    }


    customAxios.post("/api/auth/register", {
        email: email.value,
        password: password.value,
        confirmPassword: confirmPassword.value
    })
        .then(res => {
            console.log(res);
            // localStorage.setItem("accessToken", res.data.accessToken); // 서버에서 온 토큰 로컬스토리지 저장

            // 메모리에 저장
            store.setAccessToken(res.data.accessToken);

            // 보호된 페이지로 이동
            router.push("/user/edit");
            console.log("router.pushed");
            console.log("router:", router);
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
            <AuthInputField type="email" id="email" name="email" label="이메일" v-model="email" :required="true" />
            <AuthInputField type="password" id="password" name="password" label="비밀번호" v-model="password"
                :required="true" />
            <AuthInputField type="password" id="confirmPassword" name="confirmPassword" label="비밀번호 확인"
                v-model="confirmPassword" :required="true" />
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
