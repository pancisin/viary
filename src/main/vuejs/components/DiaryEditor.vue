<template>
  <div>
    <edit-diary-form :diary="diary" />
  </div>
</template>

<script>
import { EditDiaryForm } from '@/components/forms';

export default {
  props: {
    diary: {
      type: Object,
      required: true
    }
  },
  components: {
    EditDiaryForm
  }
}
</script>
