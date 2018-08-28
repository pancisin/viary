<template>
  <section class="section-full studio-area">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <div class="studio-content">
            <p class="text-uppercase c-white">We create top class designs</p>
            <h2 class="h1 c-white text-uppercase mb-20">
              A Digital Studio Crafting <br> Tech Business
            </h2>
            <p class="c-white mB-30">
              Computer users and programmers have become so accustomed to using Windows,
              even for the changing capabilities and the appearances of the graphical
              interface of the versions, therefore it has remained Microsoft’s product.
              Although, Lycoris, Red Hat, Mandrake,
            </p>
            <a
              href="#"
              class="primary-btn c-white d-inline-flex align-items-center">
              Start Business
              <br>
              <i class="fa fa-money"/>
            </a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {

};
</script>

<style lang="scss">
.studio-area {
  position: relative;

  &:before {
    position: absolute;
    content: "";
    left: 0;
    top: 0;
    width: 50%;
    height: 100%;
    background: #404598;
    z-index: 2;
  }

  &:after {
    position: absolute;
    content: "";
    right: 0;
    top: 0;
    width: 50%;
    height: 100%;
    background: url(https://colorlib.com/etc/datarc/img/studio-bg.jpg) no-repeat center center/cover;
    z-index: 1;
  }

  .container {
    position: relative;
    z-index: 3;
  }
}

@media (max-width: 991px) {
  .studio-area:before {
    background: rgba(64, 69, 152, 0.95);
    width: 100%;
  }
}
</style>
