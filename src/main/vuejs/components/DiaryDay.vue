<template>
   <div 
      class="diary-day d-flex flex-column"
      :class="{ 'diary-day-current' : day.startOf('day').toMillis() === DateTime.local().startOf('day').toMillis(), 'diary-day-focused' : scopedDay.ts === day.ts }"
      @click="focusDayContent(day, $event)">

      <div class="diary-day-header">
        <span class="diary-day-header-date">
          {{ day.day }}
        </span>
        {{ day.weekdayLong }}
      </div>

      <textarea 
        rows="3" 
        class="diary-day-content flex-grow-1 text-secondary" 
        v-model="day.content" 
        @input="dayUpdate">
      </textarea>
    </div>
</template>

<script>
import debounce from 'debounce';
import { DateTime } from 'luxon';
import { mapGetters, mapActions } from 'vuex';

export default {
  props: {
    day: {
      type: Object,
      default () {
        return {}
      }
    }
  },
  computed: {
    ...mapGetters(['scopedDay']),
    DateTime() {
      return DateTime;
    }
  },
  methods: {
    ...mapActions(['scopeDay']),
    
    dayUpdate: debounce(function(e) {
      this.updateScopedDay(e.target.value)
    }, 1000),

    focusDayContent (day, e) {
      this.scopeDay(day);
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
