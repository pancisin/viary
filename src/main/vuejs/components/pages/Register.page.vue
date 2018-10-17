<template>
  <signin-layout title="Sign up">
    <register-form :onsignin="onsignin" />
  </signin-layout>
</template>

<script>
import { SigninLayout } from '@/components/layout';

export default {
  components: {
    SigninLayout
  },
  methods: {
    onsignin () {
      this.$router.push({ name: 'signin' })
    }
  }
};
</script>
