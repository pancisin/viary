<template>
  <div class="diary" v-loading="loadingDiary">
    <div class="diary-info p-10 d-flex jc-sb ai-c">
      <span>{{ scopedDay.monthLong }} - {{ scopedDay.weekNumber }}. týždeň {{ scopedDay.year }}</span>
      <a>
        <i class="fa fa-2x fa-cog"></i>
      </a>
    </div>

    <div class="diary-week pX-10 pB-10">
      <diary-day v-for="(day, index) in weekDays" :key="index" :day="day" />
    </div>

    <diary-controls />
  </div>
</template>

<script>
import DiaryDay from './DiaryDay';
import DiaryControls from './DiaryControls';

import DiaryApi from '@/api/diary.api';

import { mapGetters } from 'vuex';

export default {
  components: {
    DiaryDay,
    DiaryControls
  },
  computed: {
    ...mapGetters(['loadingDiary', 'scopedDay', 'weekDays'])
  }
}
</script>
