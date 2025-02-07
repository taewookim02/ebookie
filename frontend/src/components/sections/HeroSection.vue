<template>
  <section class="hero">
    <h1>이번 달 베스트 eBook</h1>
    <CtaButton msg="구매" />
    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quod assumenda asperiores veniam? Magnam, atque harum
      assumenda repellat a, hic voluptatibus deleniti corrupti sit eos ipsam?</p>
    <img src="@/assets/hero.png" alt="hero img" />
  </section>
</template>

<script setup>
import CtaButton from '@/components/shared/CtaButton.vue';

</script>

<style scoped>
/* HERO */
.hero {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2.4rem;
  padding-top: 4rem;
}
</style>