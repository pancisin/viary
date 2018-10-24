<template>
  <div>
    <diary-module :useLocalDatabase="useLocalDatabase" />
  </div>
</template>

<script>
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

    window.addEventListener('online',  updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);
  },
  methods: {
    useLocalDatabase () {
      return this.offlineMode;
    }
  }
}
</script>
 