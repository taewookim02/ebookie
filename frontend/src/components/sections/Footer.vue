<template>
  <!-- <footer class="footer">
    <span class="footer__desc">eBookie</span>
    <div class="footer_link">
      <RouterLink to="#" class="footer__link--item">홈</RouterLink>
      <RouterLink to="#" class="footer__link--item">카테고리</RouterLink>
      <RouterLink to="#" class="footer__link--item">베스트셀러</RouterLink>
      <RouterLink to="#" class="footer__link--item">이벤트</RouterLink>
    </div>
    <span class="footer__info">© {{ new Date().getFullYear() }} All Rights Reserved</span>
  </footer> -->
  <footer class="row row-cols-1 row-cols-sm-2 row-cols-md-5 py-5 my-5 border-top">
    <div class="col mb-3">
        <a href="/" class="d-flex align-items-center mb-3 link-body-emphasis text-decoration-none">
            <img src="@/assets/logo.png" alt="Logo" class="logo__img">

        </a>
        <p class="text-body-secondary">© {{ new Date().getFullYear() }}</p>
    </div>

    <div class="col mb-3">

    </div>

    <div class="col mb-3">
        <h5>Section</h5>
        <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Home</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Features</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Pricing</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">FAQs</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">About</a></li>
        </ul>
    </div>

    <div class="col mb-3">
        <h5>Section</h5>
        <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Home</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Features</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Pricing</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">FAQs</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">About</a></li>
        </ul>
    </div>

    <div class="col mb-3">
        <h5>Section</h5>
        <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Home</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Features</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">Pricing</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">FAQs</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-body-secondary">About</a></li>
        </ul>
    </div>
</footer>

</template>


<style scoped>
.logo__img {
    max-height: 3.1rem;
    width: auto;
}
</style>