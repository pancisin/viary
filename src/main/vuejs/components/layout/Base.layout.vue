<template>
  <div class="container">
    <!-- <div class="page-container"> -->
      <!-- <main class="main-content"> -->
        <div
          v-loading="loading">
          <router-view />
        </div>
      <!-- </main> -->

      <!-- <toasts /> -->
    <!-- </div> -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { PageTransition } from '@/components/transitions';

// import { Toasts } from 'viary-core/dist/diary-core.common.js';

export default {
  components: {
    // Toasts,
    PageTransition
  },
  computed: {
    ...mapGetters({
      loading: 'globalLoading'
    })
  }
};
</script>
