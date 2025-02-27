<template>
  <footer class="footer">
    <div class="footer__content">
      <div class="footer__brand">
        <RouterLink to="/" class="footer__logo">
          <img src="@/assets/logo.png" alt="Logo" class="logo__img">
        </RouterLink>
        <p class="footer__copyright">© {{ new Date().getFullYear() }} eBookie. All rights reserved.</p>
      </div>

      <div class="footer__links">
        <div class="footer__section">
          <h5 class="footer__heading">쇼핑</h5>
          <ul class="footer__list">
            <li><RouterLink to="/products?page=0&size=20&sort=sold,desc" class="footer__link">베스트상품</RouterLink></li>
            <li><RouterLink to="/products?page=0&size=20&sort=publishedDate,desc" class="footer__link">신상품</RouterLink></li>
            <li><RouterLink to="/products?page=0&size=20&sort=discountRate,desc" class="footer__link">세일</RouterLink></li>
          </ul>
        </div>

        <div class="footer__section">
          <h5 class="footer__heading">계정</h5>
          <ul class="footer__list">
            <li><RouterLink to="/member/edit" class="footer__link">마이페이지</RouterLink></li>
            <li><RouterLink to="/cart" class="footer__link">장바구니</RouterLink></li>
            <li><RouterLink to="/orders" class="footer__link">주문내역</RouterLink></li>
            <li><RouterLink to="/library" class="footer__link">라이브러리</RouterLink></li>
          </ul>
        </div>

        <div class="footer__section">
          <h5 class="footer__heading">관심상품</h5>
          <ul class="footer__list">
            <li><RouterLink to="/liked" class="footer__link">좋아요 목록</RouterLink></li>
            <li><RouterLink to="/saved" class="footer__link">찜 목록</RouterLink></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  padding: 4rem 2rem;
  background-color: #f8f9fa;
  border-top: 1px solid #e9ecef;
}

.footer__content {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 4rem;
}

.footer__brand {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.logo__img {
  max-height: 3.1rem;
  width: auto;
}

.footer__copyright {
  color: #6c757d;
  font-size: 0.9rem;
  margin: 0;
}

.footer__links {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
}

.footer__heading {
  color: #212529;
  font-size: 1.1rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.footer__list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer__list li {
  margin-bottom: 0.5rem;
}

.footer__link {
  color: #6c757d;
  text-decoration: none;
  transition: color 0.2s;
}

.footer__link:hover {
  color: #212529;
}

@media (max-width: 768px) {
  .footer__content {
    grid-template-columns: 1fr;
    gap: 2rem;
  }

  .footer__links {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 480px) {
  .footer__links {
    grid-template-columns: 1fr;
  }
}
</style>