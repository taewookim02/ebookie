<template>
  <footer class="footer">
    <span class="footer__desc">Made with love in Yo, Korea</span>
    <div class="footer_link">
      <RouterLink to="#" class="footer__link--item">About</RouterLink>
      <RouterLink to="#" class="footer__link--item">Privacy Policy</RouterLink>
      <RouterLink to="#" class="footer__link--item">Terms of Service</RouterLink>
      <RouterLink to="#" class="footer__link--item">Github</RouterLink>
    </div>
    <span class="footer__info">© {{ new Date().getFullYear() }} All Rights Reserved</span>
  </footer>
</template>


<style scoped>
  /* FOOTER */
  .footer {
    padding: 6.2rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1.6rem;
  }

  .footer_link {
    display: flex;
    gap: 0.8rem;
  }

  .footer__info {
    font-size: 1.4rem;
    color: #333;
  }
</style>