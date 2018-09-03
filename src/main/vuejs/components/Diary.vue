<template>
  <div class="diary" v-loading="loadingDiary">
    <diary-info />

    <div class="diary-week pX-10 pB-10">
      <diary-day v-for="(day, index) in weekDays" :key="index" :day="day" />
    </div>

    <diary-controls />
  </div>
</template>

<script>
import DiaryDay from './DiaryDay';
import DiaryControls from './DiaryControls';
import DiaryInfo from './DiaryInfo';

import DiaryApi from '@/api/diary.api';

import { mapGetters } from 'vuex';

export default {
  components: {
    DiaryDay,
    DiaryControls,
    DiaryInfo
  },
  computed: {
    ...mapGetters(['loadingDiary', 'scopedDay', 'weekDays'])
  }
}
</script>
