<template>
  <div class="diary">
    <div class="diary-controls p-10 d-flex jc-sb">
      <button 
        class="btn btn-outline-primary" 
        @click="manipulateScope(-1)">
        <i class="fa fa-angle-left"></i> Predchádzajúci
      </button>

      <button
        class="btn btn-outline-primary"
        @click="resetScope">
        <i class="fa fa-bullseye"></i>
      </button>

      <button 
        class="btn btn-outline-primary" 
        @click="manipulateScope(1)">
        Nasledujúci <i class="fa fa-angle-right"></i>
      </button>
    </div>

    <div class="diary-info p-10">
      <span>{{ scopeDay.monthLong }} - {{ scopeDay.weekNumber }}. týždeň {{ scopeDay.year }}</span>
    </div>

    <div class="diary-week p-10">
      <div 
        v-for="(day, index) in days" 
        :key="index" 
        class="diary-day"
        :class="{ 'diary-day-current' : day.startOf('day').toMillis() === DateTime.local().startOf('day').toMillis() }">

        <div class="diary-day-header">
          <span class="diary-day-header-date">
            {{ day.day }}
          </span>
          {{ day.weekdayLong }}
        </div>

        <textarea rows="3" class="diary-day-content" v-model="day.content" @input="dayUpdate(day)">
        </textarea>
      </div>
    </div>
  </div>
</template>

<script>
import { DateTime } from 'luxon';
import debounce from 'debounce';

import DiaryApi from '@/api/diary.api';

import { mapGetters } from 'vuex';

export default {
  props: {

  },
  data () {
    return {
      scopeDay: DateTime.local()
    }
  },
  computed: {
    ...mapGetters(['scopedDiary']),
    days () {
      return Array.from({ length: 7 }, (v, i) => i).map(i => {
        const d = this.scopeDay.startOf('week').plus({ days: i })
        Object.assign(d, { content: '' })
        return d;
      })
    },
    DateTime () {
      return DateTime
    }
  },
  methods: {
    manipulateScope(diff) {
      this.scopeDay = this.scopeDay.plus({ weeks: diff })
    },
    resetScope() {
      this.scopeDay = DateTime.local()
    },
    dayClass(dateTime) {
    },
    dayUpdate: debounce(function(day) {
      console.log(day)
      DiaryApi.postDay(this.scopedDiary.slug, day.daysInYear, day.year, day.content, () => {
        console.log('saved')
      })
    }, 750)
  }
}
</script>

<style lang="scss">
$diary-border: 1px solid #ccc;

.diary {
  background-color: #fff;
  height: calc(100vh - 61px);

  .diary-controls {

  }

  .diary-info {
    text-transform: capitalize;
  }

  .diary-week {
    display: flex;
    flex-wrap: wrap;

    .diary-day {
      border-right: $diary-border;
      border-top: $diary-border;
      flex: 35vw 1 0;
      min-height: 17vh;
      padding: 5px 10px;

      .diary-day-header {
        // float: left;
        text-transform: uppercase;

        .diary-day-header-date {
          font-size: 28px;
          line-height: 28px;
          vertical-align: top;
        }
      }

      &:nth-child(2n + 1) {
        border-left: $diary-border;
      }

      &:last-child {
        border-bottom: $diary-border;
      }

      &.diary-day-current {
        background-color: #daffd8;
      }

      .diary-day-content {
        border: none;
        resize: none;
        width: 100%;
        background: none !important;
      }
    }
  }
}

</style>
