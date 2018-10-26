<template>
  <signin-layout title="Sign up">
    <register-form 
      :onsignin="onsignin" 
      :success="redirect" />
  </signin-layout>
</template>

<script>
import { SigninLayout } from '@/components/layout';

export default {
  components: {
    SigninLayout
  },
  methods: {
    redirect() {
      this.$router.push({ name: 'diary' });
    },
    onsignin () {
      this.$router.push({ name: 'signin' })
    }
  }
};
</script>
