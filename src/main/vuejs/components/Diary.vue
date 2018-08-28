<template>
  <div class="diary">
    <div class="diary-controls p-10 d-flex jc-sb">
      <button 
        class="btn btn-primary" 
        @click="manipulateScope(-1)">
        <i class="fa fa-angle-left"></i> Previous
      </button>

      <button 
        class="btn btn-primary" 
        @click="manipulateScope(1)">
        Next <i class="fa fa-angle-right"></i>
      </button>
    </div>

    <div class="diary-week">
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
    },
  },
  methods: {
    manipulateScope(diff) {
      this.scopeDay = this.scopeDay.plus({ weeks: diff })
    }
  }
}
</script>

<style lang="scss">
.diary {
  background-color: #fff;

  .diary-controls {

  }

  .diary-week {
    display: flex;
    flex-wrap: wrap;

    .diary-day {
      border: 1px solid #000;
      flex: 35vw 1 0;
      min-height: 18vh;
      padding: 5px 10px;

      .diary-day-header {
        float: left;
        text-transform: uppercase;

        .diary-day-header-date {
          font-size: 28px;
          vertical-align: top;
        }
      }
    }
  }
}

</style>
