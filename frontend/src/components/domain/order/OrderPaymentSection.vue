<script setup>
import IconToss from "@/assets/images/icons/icon_toss.gif";
import IconKakao from "@/assets/images/icons/icon_kakaopay.gif";
import IconPayco from "@/assets/images/icons/icon_payco.gif";
import IconKg from "@/assets/images/icons/icon_kg.png"


// State
defineProps({
    totalFinalPrice: Number,
    pgMethod: String
});

const paymentMethods = [
    { id: 'kakao', name: 'Kakao', icon: IconKakao },
    { id: 'toss', name: 'Toss', icon: IconToss },
    // { id: 'kg', name: 'kg', icon: IconKg },
    // { id: 'payco', name: 'Payco', icon: IconPayco },
    // { id: 'bank-transfer', name: '계좌이체' },
    // { id: 'virtual-account', name: '무통장입금' }
];


// Emit
const emit = defineEmits(['update:pgMethod']);

const selectPaymentMethod = methodId => {
    emit("update:pgMethod", methodId);
}
</script>

<template>

    <section class="order__payment">
        <h3>결제방법</h3>

        <div class="payment__method">
            <div class="payment__method--list">
                <div class="payment__method--list-item" :class="{ selected: pgMethod === method.id }" :key="method.id"
                    v-for="method in paymentMethods" @click="selectPaymentMethod(method.id)">
                    <img :src="method.icon" :alt="method.name" v-if="method.icon">
                    <span v-else>{{ method.name }}</span>
                </div>
            </div>
        </div>
        <div class="payment__info">
            <div class="payment__info--text">최종 결제금액</div>
            <div class="payment__info--price">{{ totalFinalPrice.toLocaleString() }}원</div>
        </div>

    </section>
</template>

<style scoped>
.order__payment {
    display: flex;
    flex-direction: column;
    gap: 3.2rem;
}

.payment__info {
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 2.4rem;
}

.payment__info--price {
    font-size: 2.4rem;
    font-weight: 700;
}

.payment__method--list {
    display: grid;
    width: 100%;
    grid-template-columns: repeat(4, 1fr);
    gap: 0.8rem;
}

.payment__method--list-item {
    display: grid;
    place-items: center;
    padding: 1.6rem;
    flex: 1;
    word-break: keep-all;
    border-radius: 4px;
    user-select: none;
    border: 1px solid transparent;

    &.selected {
        border: 1px solid #ccc;
    }

    &:hover {
        border: 1px solid #ddd;
        cursor: pointer;
    }

    &:active {
        border-color: #ccc;
    }
}
</style>