<template>
  <div class="peers fxw-nw h-100vh ai-c row ov-h">
    <div class="overlay overlay-bg"></div>
    <cloud-canvas class="pos-r peer-greed d-n@sm-" />
    <div
      id="signin-wrapper"
      class="col-sm-6 col-md-4 bd">
      <h4 class="c-grey-900 mB-40">{{ title }}</h4>
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
  position: absolute;
  margin: 0 auto;
  left: 0;
  right: 0;
  overflow: hidden;
  padding: 20px;
  background-color: #fff;

  @media screen and (max-width: 575px) {
      height: 100%;
  }
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
