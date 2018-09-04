<template>
  <div>
    <div class="bg-light p-10">
      <diary-info class="pB-10" />
      <h1>Create diary</h1>
    </div>

    <cloud-canvas />
  </div>
</template>

<script>
import DiaryInfo from '@/components/DiaryInfo';
import CloudCanvas from '@/components/CloudCanvas';

export default {
  components: {
    DiaryInfo,
    CloudCanvas
  }
}
</script>
