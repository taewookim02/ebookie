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
        <!-- Desktop view -->
        <table class="table table-borderless cart__numbers--table text-center d-none d-md-table">
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
                            <PhMinus :size="32" color="var(--primary-color)" weight="fill" />
                        </div>
                    </td>
                    <td><span class="price-emphasis">{{ totalDiscountAmount.toLocaleString() }}</span>원</td>
                    <td class="icon-cell">
                        <div class="price-icon">
                            <PhEquals :size="32" color="var(--primary-color)" weight="fill" />
                        </div>
                    </td>
                    <td><span class="price-emphasis">{{ totalFinalPrice.toLocaleString() }}</span>원</td>
                </tr>
            </tbody>
        </table>

        <!-- Mobile view -->
        <div class="cart__numbers--mobile d-md-none">
            <div class="price-row">
                <div class="price-label">총 상품금액</div>
                <div class="price-value"><span class="price-emphasis-mobile">{{ totalOriginalPrice.toLocaleString() }}</span>원</div>
            </div>
            <div class="price-row">
                <div class="price-label">총 할인금액</div>
                <div class="price-value"><span class="price-emphasis-mobile">{{ totalDiscountAmount.toLocaleString() }}</span>원</div>
            </div>
            <div class="price-row final">
                <div class="price-label">최종 결제금액</div>
                <div class="price-value"><span class="price-emphasis-mobile">{{ totalFinalPrice.toLocaleString() }}</span>원</div>
            </div>
        </div>
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

/* Mobile styles */
.cart__numbers--mobile {
    padding: 1rem;
    background-color: var(--surface-color);
    border-radius: 0.5rem;
}

.price-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem 0;
    border-bottom: 1px solid var(--border-color);
}

.price-row.final {
    border-bottom: none;
    margin-top: 1rem;
    padding-top: 1.5rem;
    border-top: 2px solid var(--border-color);
}

.price-label {
    font-weight: 500;
    color: var(--text-secondary-color);
}

.price-emphasis-mobile {
    font-size: 1.5rem;
    font-weight: 600;
}

@media (max-width: 576px) {
    .price-emphasis-mobile {
        font-size: 1.2rem;
    }
}
</style>