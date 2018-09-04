<template>
  <form class="form" @submit.prevent="submit">
    <div class="form-group">
      <label class="text-normal text-dark">Name</label>
      <input
        v-model.trim="diary.name"
        type="text"
        class="form-control"
        placeholder="My daily diary">
    </div>
    <div class="form-group">
      <label class="text-normal text-dark">Description</label>
      <textarea class="form-control" v-model.trim="diary.description" />
    </div>
    <button type="submit" class="btn btn-outline-primary">Submit</button>
  </form>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  data () {
    return {
      diary: {}
    }
  },
  methods: {
    ...mapActions(['createDiary']),
    submit () {
      this.createDiary(this.diary).then(() => {
        this.$router.push({ name: 'diary' })
      })
    }
  }
}
</script>

<style>

</style>
