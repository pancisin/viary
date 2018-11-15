<template>
  <div>
    <diary-module :offlineMode="offlineMode" />
  </div>
</template>

<script>
import Vue from 'vue';
import { DiaryModule } from 'diary-core/dist/diary-core.common.js'
export default {
  name: 'diary-page',
  components: {
    DiaryModule
  },
  data () {
    return {
      offlineMode: false
    }
  },
  mounted () {
    const updateOnlineStatus = e => {
      this.offlineMode = e.type.toLowerCase() === 'offline';
    }

    console.log(this.$route)

    window.addEventListener('online',  updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);
  }
}
</script>
 