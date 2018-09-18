<template>
  <div id="app">
    <!-- <transition
      name="fade"
      mode="out-in">
    </transition> -->
    <router-view />
  </div>
</template>

<script>
// import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'App',
  computed: {
    // ...mapGetters('$_auth', ['authenticated'])
  },
  created () {
    // if (this.authenticated) {
    //   this.initializeUser();
    // }

    // this.initializeApplication();
  },
  methods: {
    // ...mapActions('$_auth', ['initializeUser']),
    // ...mapActions(['initializeApplication'])
  }
};
</script>
