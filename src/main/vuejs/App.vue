<template>
  <div id="app">
    <transition
      name="fade"
      mode="out-in">
        <router-view />
    </transition>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'App',
  computed: {
    ...mapGetters(['authenticated'])
  },
  created () {
    if (this.authenticated) {
      this.initializeUser();
    }

    this.initializeApplication();
  },
  methods: {
    ...mapActions(['initializeUser', 'initializeApplication'])
  }
};
</script>
