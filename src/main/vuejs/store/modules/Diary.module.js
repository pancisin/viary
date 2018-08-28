import DiaryApi from '@/api/diary.api';
import MeApi from '@/api/me.api';
import * as types from '@/store/mutation-types';

import { DateTime } from 'luxon';

const state = {
  diaries: [],
  scopedDiary: {},
  scopedDiaryDays: [],
  scopedDiaryWeeks: []
}

const getters = {
  diaries: state => state.diaries,
  scopedDiary: state => state.scopedDiary,
  scopedDiaryDays: state => state.scopedDiaryDays,
  getDiaryWeek: state => weekNumber => {
    const idx = state.scopedDiaryWeeks.findIndex(w => w.weekNumber === weekNumber)

    if (idx != -1) {
      return state.scopedDiaryWeeks[idx]
    }

    return null
  }
}

const actions = {
  initializeDiaries ({ commit, dispatch }) {
    return new Promise(resolve => {
      MeApi.getDiaries(diaries => {
        commit(types.SET_DIARIES, { diaries })
        dispatch('scopeDiary')
        resolve(diaries)
      })
    })
  },

  scopeDiary ({ commit, getters }) {
    return new Promise(resolve => {
      const diary = getters.diaries[0] || {}
      commit(types.SCOPE_DIARY, { diary })
    })
  },

  scopeDiaryWeek ({ commit, getters }, weekNumber) {
    const week = DateTime.fromObject({ weekNumber })
    if (getters.getDiaryWeek(weekNumber) == null) {
      return new Promise(resolve => {
        DiaryApi.getDays(getters.scopedDiary.slug, {
          from: week.startOf('week').toFormat('MM/dd/yyyy'),
          to: week.endOf('week').toFormat('MM/dd/yyyy')
        }, days => {
          commit(types.SET_SCOPED_DIARY_DAYS, { days });
          commit(types.ADD_WEEK_TO_SCOPE, { weekNumber, days });
          resolve();
        })
      })
    }
  },

  updateDay ({ commit, getters }, { weekNumber, day }) {
    return new Promise(resolve => {
      DiaryApi.postDay(getters.scopedDiary.slug, day, result => {
        resolve()
        console.log('saved')
        commit(types.UPDATE_DAY, { weekNumber, day: result })
      })
    })
  }
}

const mutations = {
  [types.SET_DIARIES] (state, { diaries }) {
    state.diaries = diaries;
  },

  [types.SCOPE_DIARY] (state, { diary }) {
    state.scopedDiary = diary;
  },

  [types.SET_SCOPED_DIARY_DAYS] (state, { days }) {
    state.scopedDiaryDays = days;
  },

  [types.ADD_WEEK_TO_SCOPE] (state, { weekNumber, days }) {
    const idx = state.scopedDiaryWeeks.findIndex(w => w.weekNumber === weekNumber)

    if (idx != -1) {
      state.scopedDiaryWeeks.splice(idx, 1, {
        weekNumber,
        days: [ ...days]
      })
    } else {
      state.scopedDiaryWeeks.push({
        weekNumber,
        days: [ ...days]
      })
    }
  },

  [types.UPDATE_DAY] (state, { weekNumber, day }) {
    const weekIdx = state.scopedDiaryWeeks.findIndex(w => w.weekNumber === weekNumber)
    const week = state.scopedDiaryWeeks[weekIdx]

    const dayIdx = week.days.findIndex(d => d.date_number === day.date_number)
    state.scopedDiaryWeeks = state.scopedDiaryWeeks.map(w => {
      if (w.weekNumber === weekNumber) {
        w.days.splice(dayIdx, 1, day)
      }

      return w
    })
  }
}
 
export default {
  state,
  getters,
  actions,
  mutations
}