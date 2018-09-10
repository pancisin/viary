<template>
  <div>
    <diary-module :diary-api="DiaryApi" :me-api="MeApi" />
  </div>
</template>

<script>
import { DiaryModule } from '@/modules'
import DiaryApi from '@/api/diary.api'
import MeApi from '@/api/me.api';

export default {
  name: 'diary-page',
  components: {
    DiaryModule
  },
  computed: {
    DiaryApi () {
      return DiaryApi;
    },
    MeApi () {
      return MeApi;
    }
  }
}
</script>
