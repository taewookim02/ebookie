<script setup>
import AuthInputField from '@/components/domain/member/AuthInputField.vue';
import { onMounted, ref } from 'vue';
import { customAxios } from '@/plugins/axios';
import { useToast } from 'vue-toastification';
import router from '@/router';

// 상태
const email = ref("");
const newPassword = ref("");
const newPasswordConfirm = ref("");
const currentPassword = ref("");
const errMsg = ref("");
const toast = useToast();

// 액션
const handleUpdate = () => {
    console.log(email, newPassword, newPasswordConfirm, currentPassword);

    // 유효성 검사
    if (newPassword.value !== newPasswordConfirm.value) {
        errMsg.value = "새 비밀번호와 새 비밀번호 확인이 다릅니다.";
        return;
    }

    customAxios.patch("/api/v1/members/update", {
        email: email.value,
        newPassword: newPassword.value,
        newPasswordConfirm: newPasswordConfirm.value,
        currentPassword: currentPassword.value
    }).then(res => {
        console.log(res);
        toast.success("회원정보가 성공적으로 수정되었습니다.");
        router.push("/");
    }).catch(err => {
        console.log("err:", err);
        // 비밀번호, 이메일 틀렸을 시 
        errMsg.value = err.response.data.message;
    })
}

// 라이프사이클 훅
onMounted(() => {
    // 로그인 유저 정보
    customAxios.get("/api/v1/members/detail")
    .then(res => {
        email.value = res.data.email;
    }).catch(err => {
        console.log(err);
    })
})
</script>

<template>
    <div class="container py-5">
        <section class="settings">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <form @submit.prevent="handleUpdate" class="settings__form">
                        <h2 class="text-center mb-4">회원 정보 수정</h2>
                        
                        <AuthInputField 
                            type="email" 
                            id="email" 
                            name="email" 
                            label="이메일" 
                            valueText="hello" 
                            :readonly="true" 
                            v-model="email"
                            class="mb-3"
                        />
                        
                        <AuthInputField 
                            type="password" 
                            id="password" 
                            name="password" 
                            label="새로운 비밀번호" 
                            :required="true" 
                            v-model="newPassword"
                            class="mb-3"
                        />
                        
                        <AuthInputField 
                            type="password" 
                            id="confirm-password" 
                            name="confirm-password" 
                            label="새로운 비밀번호 확인" 
                            :required="true" 
                            v-model="newPasswordConfirm"
                            class="mb-3"
                        />
                        
                        <AuthInputField 
                            type="password" 
                            id="current-password" 
                            name="current-password" 
                            label="현재 비밀번호" 
                            :required="true" 
                            v-model="currentPassword"
                            class="mb-4"
                        />

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg">수정하기</button>
                        </div>

                        <div v-if="errMsg" class="alert alert-danger mt-3" role="alert">
                            {{ errMsg }}
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
</template>

<style scoped>
.settings {
    max-width: 500px;
    margin: 0 auto;
    width: 100%;
}

.card {
    border-radius: 10px;
    border: none;
}

.btn-primary {
    background-color: #007bff;
    border: none;
    padding: 12px;
    font-weight: 500;
    transition: all 0.3s ease;
}

.btn-primary:hover {
    background-color: #0056b3;
    transform: translateY(-1px);
}

.alert {
    font-size: 0.9rem;
    padding: 12px;
}
</style>