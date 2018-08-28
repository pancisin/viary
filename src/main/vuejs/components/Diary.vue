<template>
  <div class="diary">
    <div class="diary-controls p-10 d-flex jc-sb">
      <button 
        class="btn btn-outline-primary" 
        @click="manipulateScope(-1)">
        <i class="fa fa-angle-left"></i> Previous
      </button>

      <button
        class="btn btn-outline-primary"
        @click="resetScope">
        <i class="fa fa-bullseye"></i>
      </button>

      <button 
        class="btn btn-outline-primary" 
        @click="manipulateScope(1)">
        Next <i class="fa fa-angle-right"></i>
      </button>
    </div>

    <div class="diary-info p-10">
      <span>{{ scopeDay.monthLong }} - {{ scopeDay.weekNumber }}. week of {{ scopeDay.year }}</span>
    </div>

    <div class="diary-week p-10">
      <div 
        v-for="(day, index) in days" 
        :key="index" 
        class="diary-day">

        <div class="diary-day-header">
          <span class="diary-day-header-date">
            {{ day.day }}
          </span>
          {{ day.weekdayLong }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { DateTime } from 'luxon';
export default {
  props: {

  },
  data () {
    return {
      scopeDay: DateTime.local()
    }
  },
  computed: {
    days () {
      return Array.from({ length: 7 }, (v, i) => i).map(i => {
        return this.scopeDay.startOf('week').plus({ days: i })
      })
    }
  },
  methods: {
    manipulateScope(diff) {
      this.scopeDay = this.scopeDay.plus({ weeks: diff })
    },
    resetScope() {
      this.scopeDay = DateTime.local()
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

  .diary-week {
    display: flex;
    flex-wrap: wrap;

    .diary-day {
      border-right: $diary-border;
      border-top: $diary-border;
      flex: 35vw 1 0;
      min-height: 18vh;
      padding: 5px 10px;

      .diary-day-header {
        float: left;
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

      }
    }
  }
}

</style>
