import DiaryApi from '@/api/diary.api';
import MeApi from '@/api/me.api';
import * as types from '@/store/mutation-types';

import { DateTime } from 'luxon';

export default {
  initializeDiaries ({ commit, dispatch }) {
    commit(types.SET_LOADING_DIARY, true);
    return new Promise(resolve => {
      MeApi.getDiaries(diaries => {
        commit(types.SET_DIARIES, { diaries })
        dispatch('scopeDiary')
        resolve(diaries)
        commit(types.SET_LOADING_DIARY, false);  
      })
    })
  },

  scopeDiary ({ commit, getters }) {
    return new Promise(resolve => {
      const diary = getters.diaries[0] || {}
      commit(types.SCOPE_DIARY, { diary });
      resolve();
    })
  },

  scopeDay ({ commit, getters, dispatch }, day) {
    return new Promise(resolve => {
      dispatch('loadWeekData', day.weekNumber).then(() => {
  
        commit(types.SCOPE_DAY, { day });
        resolve();
      })
    })
  },

  loadWeekData ({ commit, getters }, weekNumber) {
    const week = DateTime.fromObject({ weekNumber })
    if (getters.getDiaryWeek(weekNumber) == null) {
      commit(types.SET_LOADING_DIARY, true);

      return new Promise(resolve => {
        DiaryApi.getDays(getters.scopedDiary.slug, {
          from: week.startOf('week').toFormat('MM/dd/yyyy'),
          to: week.endOf('week').toFormat('MM/dd/yyyy')
        }, days => {
          commit(types.SET_SCOPED_DIARY_DAYS, { days });
          commit(types.ADD_WEEK_TO_SCOPE, { weekNumber, days });
          commit(types.SET_LOADING_DIARY, false);
          resolve();
        })
      })
    }
  },

  updateScopedDay ({ commit, getters }, content) {
    const scopedDay = getters.scopedDay;
    const dayNumber = scopedDay.diff(scopedDay.startOf('year'), 'days').toObject().days

    commit(types.SET_SAVING_DIARY, true);

    return new Promise(resolve => {
      DiaryApi.postDay(getters.scopedDiary.slug, {
        date_number: dayNumber,
        year: scopedDay.year,
        content
      }, result => {
        resolve()
        commit(types.UPDATE_DAY, { weekNumber: scopedDay.weekNumber, day: result })
        commit(types.SET_SAVING_DIARY, false);
      })
    })
  }
}