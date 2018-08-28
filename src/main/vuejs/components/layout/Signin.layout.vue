<template>
  <div class="peers ai-s fxw-nw h-100vh">
    <div
      id="signin-wrapper"
      class="d-n@sm- peer peer-greed h-100 pos-r bgr-n bgpX-c bgpY-c bgsz-cv">
      <!-- <div class="pos-a centerXY">
        <div
          class="bgc-white bdrs-50p pos-r"
          style="width: 120px; height: 120px;">
          <img
            class="pos-a centerXY"
            src="../../assets/img/logo.png"
            alt="" >
        </div>
      </div> -->
    </div>
    <div
      class="col-12 col-md-4 peer pX-40 pY-80 h-100 bgc-white scrollable pos-r"
      style="min-width: 320px;">
      <h4 class="fw-300 c-grey-900 mB-40">{{ title }}</h4>
      <slot/>
    </div>
  </div>
</template>

<script>
export default {
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
</style>
