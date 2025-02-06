<template>
  <section class="features">
    <h2 class="features__heading">Features that we love</h2>
    <div class="features__container">
      <div class="features__item">
        <h3>Feature 1</h3>
        <img src="@/assets/feature-item.png" class="features__img" alt="feature img" />
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Saepe, maiores?</p>
      </div>
      <div class="features__item">
        <h3>Feature 2</h3>
        <img src="@/assets/feature-item.png" class="features__img" alt="feature img" />
        <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Sapiente, ipsum?</p>
      </div>
      <div class="features__item">
        <h3>Feature 3</h3>
        <img src="@/assets/feature-item.png" class="features__img" alt="feature img" />
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Optio, impedit.</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
/* FEATURES */
.features {
  display: flex;
  flex-direction: column;
  gap: 2.4rem;
  /* TODO: add media queries for smaller sizes */
}



.features__container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 1.6rem;
}

.features__item {
  display: flex;
  flex-direction: column;
  gap: 1.6rem;
  align-items: center;
}

.features__img {
  width: 100%;
  height: auto;
  object-fit: cover;
}
</style>