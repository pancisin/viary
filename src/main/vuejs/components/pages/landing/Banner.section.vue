<template>
  <section class="banner-area pos-r">
    <div class="overlay overlay-bg"></div>
    <cloud-canvas class="pos-a"/>

    <div class="container" id="banner-container">
      <div
        class="row ai-c jc-c" style="height: 100%">

        <!-- <div class="col-md-6 phone-mockup d-none d-md-block"> -->
        <div class="col-md-6 d-none d-md-block">
          <div class="device-wrapper">
            <div class="device" data-device="MacbookPro" data-orientation="portrait" data-color="black">
              <div class="screen">
                <img :src="mobileScreenshot">
              </div>
              <div class="button">
                <!-- You can hook the "home button" to some JavaScript events or just remove it -->
              </div>
            </div>
          </div>
        </div>

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

import 'html5-device-mockups/dist/device-mockups.min.css';

export default {
  components: {
    CloudCanvas
  },
  computed : {
    mobileScreenshot() {
      return require('@/assets/img/screenshot_desktop178.png')
    }
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
    background: rgba(19, 44, 57, 0.98);
    // background: linear-gradient(to right, rgba(60, 64, 143, 0.95) 0%, rgba(91, 97, 207, 0.95) 100%);
  }

  .overlay {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }

  .phone-mockup { 
    // background: url('../../../assets/img/lumia_mockup.png') no-repeat center center/contain;
    height: 100%;
  }

  .device-wrapper {
    max-width: none;

    .screen img {
      width: 100%;
      height: 100%;
    }
  }
}
</style>
