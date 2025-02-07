<template>
  <section class="cta">
    <h1>Lorem ipsum dolor sit amet.</h1>
    <CtaButton msg="lorem" />
    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iusto pariatur vitae hic aperiam cumque deserunt facilis
      error repudiandae eum, exercitationem maxime neque voluptates, quaerat eaque.</p>
  </section>
</template>

<script setup>
import CtaButton from '@/components/shared/CtaButton.vue';
</script>

<style scoped>
/* CTA */
.cta {
  display: flex;
  flex-direction: column;

  align-items: center;
  gap: 2.4rem;
}
</style>