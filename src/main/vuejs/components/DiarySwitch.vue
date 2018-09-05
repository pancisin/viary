<template>
  <div>
    <div class="form-group">
      <select class="form-control" v-model="selectedDiary">
        <option v-for="(diary, index) in diaries" :key="index" :value="diary.slug">
          {{ diary.name }}
        </option>
      </select>
    </div>

    <div class="text-center">
      <button 
        type="button" 
        class="btn btn-outline-success" 
        @click="switchDiary">
        Switch
      </button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  data () {
    return {
      selectedDiary: null
    }
  },
  computed: {
    ...mapGetters(['diaries', 'scopedDiary', 'scopedDay'])
  },
  mounted () {
    this.selectedDiary = this.scopedDiary.slug
  },
  methods: {
    ...mapActions(['scopeDiary']),
    switchDiary() {
      this.scopeDiary({ slug: this.selectedDiary, scopeDate: this.scopedDay.toSQL() }).then(diary => {
        this.$emit('switched', diary);
      })
    }
  }
}
</script>
