<template>
  <div>
    <diary />
    Diary Module
  </div>
</template>

<script>
import Diary from './_components/Diary';
import store from './_store';

const MODULE_NAMESPACE = '$_diary';

import Vue from 'vue';

export default {
  name: 'DiaryContainer',
  components: {
    Diary
  },
  created () {
    this.$store.registerModule(MODULE_NAMESPACE, store);
    this.$store.dispatch(`${MODULE_NAMESPACE}/initializeDiaries`);
  },
  beforeDestroy () {
    this.$store.unregisterModule(MODULE_NAMESPACE);
  }
};
</script>