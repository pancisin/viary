<template>
  <div class="diary-controls p-10 d-flex jc-sb">
    <button 
      class="btn btn-outline-light" 
      @click="manipulateScope(-1)">
      <i class="fa fa-angle-left"></i> 
    </button>

    <button
      class="btn btn-outline-light"
      @click="resetScope">
      <i class="fa fa-bullseye"></i>
    </button>

    <button 
      class="btn btn-outline-light" 
      @click="manipulateScope(1)">
        <i class="fa fa-angle-right"></i>
    </button>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { DateTime } from 'luxon';

export default {
  mounted() {
    // this.resetScope();
  },
  computed: {
    ...mapGetters(['scopedDay']),
  },
  methods: {
    ...mapActions(['scopeDay', 'updateScopedDay']),
    manipulateScope(diff) {
      this.scopeDay({ day: this.scopedDay.plus({ weeks: diff }) })
    },
    
    resetScope() {
      this.scopeDay({ day: DateTime.local() })
    }
  }
}
</script>

<style>

</style>
