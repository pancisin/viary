<template>
  <div class="peers ai-s fxw-nw h-100vh">
    <div class="overlay overlay-bg"></div>
    <cloud-canvas class="pos-r peer-greed d-n@sm-" />
    <div
      class="col-12 col-md-4 peer pX-40 pY-80 h-100 bgc-white scrollable pos-r"
      style="min-width: 320px;">
      <h4 class="fw-300 c-grey-900 mB-40">{{ title }}</h4>
      <slot/>
    </div>
  </div>
</template>

<script>
import CloudCanvas from '@/components/CloudCanvas'
export default {
  components: {
    CloudCanvas
  },
  props: {
    title: {
      type: String,
      default () {
        return '';
      }
    }
  }
};
</script>

<style lang="scss">
#signin-wrapper {
  // background: url("../../assets/img/bg.jpg");

  
}

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
</style>
