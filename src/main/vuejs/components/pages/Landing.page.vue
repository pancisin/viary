<template>
  <div>
    <!-- <header-part/> -->
    <banner-section />
    <!-- <features-section />
    <about-section /> -->
    <!-- <pricing-section />
    <contact-section /> -->
    <!-- <toasts /> -->
  </div>
</template>

<script>
// import { Toasts } from 'diary-core/dist/diary-core.common.js';
import * as sections from './landing';

export default {
  components: {
    ...sections
    // Toasts
  }
};
</script>

<style lang="scss">
.section-full {
  padding: 150px 0;
}

.gray-bg {
  background: #f9f9ff;
}

.primary-btn {
  line-height: 40px;
  padding: 0 40px;
  border-radius: 20px;
  background: transparent;
  border: 1px solid #00ff8c;
  color: #222222;
  display: inline-block;
  font-weight: 600;
  -webkit-transition: all 0.3s ease 0s;
  -moz-transition: all 0.3s ease 0s;
  -o-transition: all 0.3s ease 0s;
  transition: all 0.3s ease 0s;
  cursor: pointer;
}
</style>
