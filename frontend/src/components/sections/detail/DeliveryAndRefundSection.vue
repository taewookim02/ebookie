<script setup>

</script>

<template>
    <section class="delivery-refund">
        <h3>배송/반품 안내</h3>
        <div class="delivery-container">
            <h4>배송 안내</h4>
            <table class="table table-borderless">
                <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                </colgroup>
                <tbody>
                    <tr>
                        <th>배송 정보</th>
                        <td>
                            <ul>
                                <li>구매 후 즉시 다운로드 가능</li>
                                <li>배송비: 무료배송</li>
                            </ul>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="refund-container">
            <h4>반품 안내</h4>
            <table class="table table-borderless">
                <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                </colgroup>
                <tbody>
                    <tr>
                        <th>반품 방법</th>
                        <td>
                            <ul>
                                <li>마이페이지 > 주문목록 > 반품 신청</li>
                            </ul>
                        </td>
                    </tr>
                    <tr>
                        <th>반품 제한</th>
                        <td>
                            <ul>
                                <li>구매 후 7일 이내의 상품</li>
                                <li>다운로드 횟수가 0인 상품</li>
                            </ul>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </section>
</template>

<style scoped>
.delivery-refund,
.delivery-container,
.refund-container {
    display: flex;
    flex-direction: column;
}

.delivery-refund {
    gap: 2.4rem;
}

.delivery-container,
.refund-container {
    gap: 0.8rem;
    font-size: 1.4rem;
}


.delivery-refund th {
    vertical-align: middle;
    text-align: center;
    font-weight: 700;
}

.delivery-refund td {
    color: #666666;
}

.delivery-refund ul {
    margin: 0;
}

.delivery-refund li {
    list-style-type: "•  ";

}

.table {
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
</style>