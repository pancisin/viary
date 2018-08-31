<template>
  <div class="diary">
    <div class="diary-info p-10 d-flex jc-sb ai-c">
      <span>{{ scopeDay.monthLong }} - {{ scopeDay.weekNumber }}. týždeň {{ scopeDay.year }}</span>
      <a>
        <i class="fa fa-2x fa-cog"></i>
      </a>
    </div>

    <div class="diary-week pX-10 pB-10">
      <div 
        v-for="(day, index) in days" 
        :key="index" 
        class="diary-day d-flex flex-column"
        :class="{ 'diary-day-current' : day.startOf('day').toMillis() === DateTime.local().startOf('day').toMillis(), 'diary-day-focused' : scopeDay.ts === day.ts }"
        @click="focusDayContent(day, $event)">

        <div class="diary-day-header">
          <span class="diary-day-header-date">
            {{ day.day }}
          </span>
          {{ day.weekdayLong }}
        </div>

        <textarea rows="3" class="diary-day-content flex-grow-1 text-secondary" v-model="day.content" @input="dayUpdate(day)">
        </textarea>
      </div>
    </div>

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

  </div>
</template>

<script>
import { DateTime } from 'luxon';
import debounce from 'debounce';

import DiaryApi from '@/api/diary.api';

import { mapGetters, mapActions } from 'vuex';

export default {
  data () {
    return {
      scopeDay: DateTime.local()
    }
  },
  computed: {
    ...mapGetters(['getDiaryWeek']),
    ...mapGetters({
      diary: 'scopedDiary'
    }),
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
    }, 1000),
    focusDayContent (day, e) {
      this.scopeDay = day;
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
$current-color: #daffd8;
$darken-amount: 2;
$bg-gradient: linear-gradient(to right bottom, #e96443, #904e95);

.diary {
  // background-color: #fff;
  height: calc(100vh - 61px);
  background: #ffffffd9;

  .diary-controls {
    position: fixed;
    width: 100%;
    bottom: 0;
    background: #fff;
    // border-top: $diary-border;
    // box-shadow: 0px 15px 20px 14px rgba(70, 70, 70, 0.44);
    background: $bg-gradient;
  }

  .diary-info {
    text-transform: capitalize;
  }

  .diary-week {
    display: flex;
    flex-wrap: wrap;
    margin-bottom: 60px;

    @media screen and (max-width: 480px) {
      flex-direction: column;
    }

    .diary-day {
      border-right: $diary-border;
      border-top: $diary-border;
      flex: 35vw 1 0;
      min-height: 21vh;
      padding: 5px 10px;
      transition: background-color 0.1s ease-in-out;

      .diary-day-header {
        pointer-events: none;
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

      @media screen and (max-width: 480px) {
        border-left: $diary-border;
      }

      &:last-child {
        border-bottom: $diary-border;
      }

      &:hover, &.diary-day-focused {
        background-color: darken(#fff, $darken-amount);
      }

      &.diary-day-current {
        background-color: $current-color;

        &:hover, &.diary-day-focused {
          background-color: darken($current-color, $darken-amount);
        }
      }

      .diary-day-content {
        border: none;
        resize: none;
        width: 100%;
        background: none !important;
        cursor: default;
        margin-top: 5px;
      }
    }
  }
}

</style>
