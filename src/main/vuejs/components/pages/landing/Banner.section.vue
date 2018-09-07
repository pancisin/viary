<template>
  <section class="banner-area pos-r">
    <div class="overlay overlay-bg"></div>
    <cloud-canvas class="pos-a"/>

    <div class="container" id="banner-container">
      <div
        class="row ai-c jc-c" style="height: 100%">

        <div class="col-md-6 phone-mockup d-none d-md-block"></div>

        <div class="col-md-6">
          <div class="text-center c-white">
            <p class="text-uppercase">Plan your whatever now !</p>
            <h1 class="text-uppercase c-white">Diary WebApp</h1>

            <router-link :to="{ name: 'signin' }" class="primary-btn c-white mB-10">
              Enter the web app
              <span class="lnr lnr-chevron-right va-m"></span>
            </router-link>

            <p>
              Or download free mobile app
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import CloudCanvas from '@/components/CloudCanvas';
import debounce from 'debounce';

export default {
  components: {
    CloudCanvas
  },
  mounted () {
    const adjustHeight = () => {
      const el = document.getElementById('banner-container');
      el.style.height = `${window.innerHeight}px`;
    }

    window.onresize = debounce(adjustHeight, 300);
    adjustHeight();
  }
};
</script>

<style lang="scss">
.banner-area {
  .overlay-bg {
    background: linear-gradient(to right, rgba(60, 64, 143, 0.95) 0%, rgba(91, 97, 207, 0.95) 100%);
  }

  .overlay {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }

  .phone-mockup { 
    background: url('../../../assets/img/lumia_mockup.png') no-repeat center center/cover;
    height: 100%;
  }
}
</style>
