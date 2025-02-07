<template>
  <section class="example">
    <div class="example__item">
      <img src="@/assets/example.svg" alt="example img" class="example__item--img">
      <div class="example__item--text">
        <h3>Lorem, ipsum.</h3>
        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Velit natus expedita labore tempora eos, totam
          magnam alias quos quo! Provident distinctio consectetur hic non asperiores molestias est magnam! Et, id?</p>
      </div>
    </div>
    <div class="example__item">
      <div class="example__item--text">
        <h3>Lorem, ipsum.</h3>
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dignissimos odit aliquam minima temporibus corporis
          nemo quaerat animi tempore. Ex alias minus eaque dolorem fugit nesciunt recusandae qui consequatur, nam
          debitis.</p>
      </div>
      <img src="@/assets/example.svg" alt="example img" class="example__item--img">
    </div>
    <div class="example__item">
      <img src="@/assets/example.svg" alt="example img" class="example__item--img">
      <div class="example__item--text">
        <h3>Lorem, ipsum.</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente, magni eius odio error temporibus eaque!
          Beatae enim similique ad vero libero id nobis quis. Possimus nisi consectetur nemo dolore ex?</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
/* Example */
.example {
  display: flex;
  flex-direction: column;
  gap: 6.4rem;
}

.example__item {
  display: flex;
  gap: 3.2rem;
  /* TODO: add media queries for smaller sizes */
}

.example__item--text {
  display: flex;
  flex-direction: column;
  gap: 4.8rem;
  justify-content: center;
}

.example__item--img {
  width: 50%;
  height: auto;
}
</style>