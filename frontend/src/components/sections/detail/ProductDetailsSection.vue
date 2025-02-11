<script setup>

</script>

<template>
    <section class="product-details">   
        <h3>품목정보</h3>
        <table class="product-details__table table table-borderless">
            <colgroup>
                <col width="20%" />
                <col width="80%" />
            </colgroup>
            <tbody>
                <tr>
                    <th>발행일</th>
                    <td>2023년 02월 08일</td>
                </tr>
                <tr>
                    <th>이용안내</th>
                    <td>
                        <ul>
                            <li>배송 필요없음</li>
                            <li>결제 후 다운로드 가능</li>
                            <li>이용기간 제한없음</li>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <th>파일포맷</th>
                    <td>PDF</td>
                </tr>
                <tr>
                    <th>용량</th>
                    <td>11.83 MB</td>
                </tr>
            </tbody>
        </table>
    </section>
</template>

<style scoped>
.product-details {
    display: flex;
    flex-direction: column;
    gap: 2.4rem;
    padding-top: 6.4rem;
    border-top: 1px solid #ccc;
}

.product-details__table ul {
    list-style-type: "▪ ";
}
</style>
