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
        :class="{ 'diary-day-current' : day.startOf('day').toMillis() === DateTime.local().startOf('day').toMillis() }"
        @click="focusDayContent">

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

import { mapGetters, mapActions } from 'vuex';

export default {
  props: {

  },
  data () {
    return {
      scopeDay: DateTime.local()
    }
  },
  computed: {
    ...mapGetters(['scopedDiary', 'getDiaryWeek']),
    daysContent () {
      const week = this.getDiaryWeek(this.scopeDay.weekNumber)
      if (week != null) {
        return week.days
      }

      return []
    },
    days () {
      return Array.from({ length: 7 }, (v, i) => i).map(i => {
        const d = this.scopeDay.startOf('week').plus({ days: i })

        const dayNumber = d.diff(d.startOf('year'), 'days').toObject().days
        const c = this.daysContent.filter(d => d.date_number === dayNumber)[0]

        Object.assign(d, { content: '' })
        if (c != null) {
          d.content = c.content;
        }

        return d;
      })
    },
    DateTime () {
      return DateTime
    }
  },
  mounted () {
    this.scopeDiaryWeek(this.scopeDay.weekNumber);  
  },
  methods: {
    ...mapActions(['scopeDiaryWeek', 'updateDay']),
    manipulateScope(diff) {
      this.scopeDay = this.scopeDay.plus({ weeks: diff });
      this.scopeDiaryWeek(this.scopeDay.weekNumber);
    },
    resetScope() {
      this.scopeDay = DateTime.local()
    },
    dayUpdate: debounce(function(day) {

      if (day == null) return
      const dayNumber = day.diff(day.startOf('year'), 'days').toObject().days
      this.updateDay({
        weekNumber: this.scopeDay.weekNumber, 
        day: {
          date_number: dayNumber,
          year: day.year,
          content: day.content
        }
      })
      // DiaryApi.postDay(this.scopedDiary.slug, dayNumber, day.year, day.content, () => {
      //   console.log('saved')
      // })
    }, 1000),
    focusDayContent (e) {
      const el = e.target;
      if (!el.classList.contains('diary-day-content')) {
        const textAreas = e.target.getElementsByClassName('diary-day-content')
        if (textAreas.length > 0) {
          textAreas[0].focus();
        }
      }
    }
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
        pointer-events: none;
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
