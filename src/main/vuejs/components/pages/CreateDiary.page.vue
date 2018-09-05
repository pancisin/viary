<template>
  <div>
    <!-- <div class="bg-light p-10">
      <diary-info class="pB-10" />
      <h1>Create diary</h1>
    </div> -->
    <div class="p-10 d-flex ai-c" style="height: 100vh">
      <div class="col-md-4 offset-md-4 bg-light bd p-10 bdrs-5">
        <p>You haven't got any diary yet. Let's create your personal one !</p>

        <create-diary-form />
      </div>
    </div>

    <cloud-canvas />
  </div>
</template>

<script>
import DiaryInfo from '@/components/DiaryInfo';
import CloudCanvas from '@/components/CloudCanvas';
import { CreateDiaryForm } from '@/components/forms';

export default {
  components: {
    DiaryInfo,
    CloudCanvas,
    CreateDiaryForm
  }
}
</script>
