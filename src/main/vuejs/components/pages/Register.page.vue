<template>
  <signin-layout title="Sign up">
    <register-form />
  </signin-layout>
</template>

<script>
import { RegisterForm } from '@/components/forms';
import { SigninLayout } from '@/components/layout';

export default {
  components: {
    SigninLayout,
    RegisterForm
  }
};
</script>
