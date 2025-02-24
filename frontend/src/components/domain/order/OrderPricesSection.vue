<script setup>
import { PhEquals, PhMinus } from '@phosphor-icons/vue';

const props = defineProps({
    totalOriginalPrice: Number,
    totalDiscountAmount: Number,
    totalFinalPrice: Number
});

</script>

<template>
    <section class="cart__numbers">
        <table class="table table-borderless cart__numbers--table text-center">
            <colgroup>
                <col width="30%" />
                <col width="5%" />
                <col width="30%" />
                <col width="5%" />
                <col width="30%" />
            </colgroup>
            <thead>
                <tr>
                    <th>총 상품금액</th>
                    <th></th>
                    <th>총 할인금액</th>
                    <th></th>
                    <th>최종 결제금액</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><span class="price-emphasis">{{ totalOriginalPrice.toLocaleString() }}</span>원</td>
                    <td class="icon-cell">
                        <div class="price-icon">
                            <PhMinus :size="32" color="#71c1cc" weight="fill" />
                        </div>
                    </td>
                    <td><span class="price-emphasis">{{ totalDiscountAmount.toLocaleString() }}</span>원</td>
                    <td class="icon-cell">
                        <div class="price-icon">
                            <PhEquals :size="32" color="#71c1cc" weight="fill" />
                        </div>
                    </td>
                    <td><span class="price-emphasis">{{ totalFinalPrice.toLocaleString() }}</span>원</td>
                </tr>
            </tbody>
        </table>
    </section>
</template>

<style scoped>
.cart__numbers--table {
    margin: 0;
    border-collapse: separate;
    border-spacing: 0;

    & td,
    & th {
        padding: 0;
        vertical-align: middle;
    }
}

.icon-cell {
    padding: 0 !important;
    width: 5%;
}

.price-icon {
    display: flex;
    justify-content: center;
    align-items: center;
}

.price-icon__parent {
    vertical-align: middle;
}

.price-emphasis {
    font-size: 3rem;
    font-weight: 600;
}
</style>