<template>
  <div class="container">
    <!-- <div class="page-container"> -->
      <!-- <main class="main-content"> -->
        <div
          v-loading="loading">
          <router-view />
        </div>
      <!-- </main> -->

      <toasts />
    <!-- </div> -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { PageTransition } from '@/components/transitions'

import { Toasts } from '@/modules';

export default {
  components: {
    Toasts,
    PageTransition
  },
  computed: {
    ...mapGetters({
      loading: 'globalLoading'
    })
  }
};
</script>
