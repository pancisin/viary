<template>
  <section class="banner-area position-relative">
    <div class="overlay overlay-bg"/>
    <div class="container">
      <div
        class="row fullscreen justify-content-center align-items-center"
        style="height: 777px;">
        <div class="col-lg-8">
          <div class="text-center">
            <p class="text-uppercase c-white">We work hard, we result perfect</p>
            <h1 class="text-uppercase c-white">Apigo Data Serving & Analytics</h1>
            <a
              href="#"
              class="primary-btn c-white">
              <i class="fa fa-globe"/>
              Explore
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
.banner-area {
  background: url(https://colorlib.com/etc/datarc/img/banner.jpg) no-repeat center center/cover;

  .overlay-bg {
    background: -moz-linear-gradient(left, rgba(60, 64, 143, 0.95) 0%, rgba(91, 97, 207, 0.95) 100%);
    background: -webkit-linear-gradient(left, rgba(60, 64, 143, 0.95) 0%, rgba(91, 97, 207, 0.95) 100%);
    background: linear-gradient(to right, rgba(60, 64, 143, 0.95) 0%, rgba(91, 97, 207, 0.95) 100%);
  }

  .overlay {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }
}
</style>
