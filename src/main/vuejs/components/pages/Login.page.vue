<template>
  <signin-layout title="Login">
    <login-form :success="redirect" />
  </signin-layout>
</template>

<script>
import { LoginForm } from '@/components/forms';
import { SigninLayout } from '@/components/layout';

export default {
  components: {
    SigninLayout,
    LoginForm
  },
  methods: {
    redirect () {
      var referer = this.$route.query.referer
        ? this.$route.query.referer
        : '/';
      this.$router.push({ path: referer });
    }
  }
};
</script>
