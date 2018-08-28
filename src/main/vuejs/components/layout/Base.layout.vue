<template>
  <div>
    <!-- <sidebar-part /> -->

    <div class="page-container">
      <!-- <header-part /> -->

      <main class="main-content bgc-grey-100">
        <div
          v-loading="loading"
          id="mainContent">

          <page-transition>
            <router-view />
          </page-transition>
        </div>
      </main>

      <toasts />
      <footer-part />
    </div>
  </div>
</template>

<script>
import { HeaderPart, SidebarPart, FooterPart } from './parts';
import { mapGetters } from 'vuex';
import { PageTransition } from '@/components/transitions'

import { Toasts } from '@/modules';

export default {
  components: {
    HeaderPart,
    SidebarPart,
    FooterPart,
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
