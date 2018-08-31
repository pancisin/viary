import actions from 'actions';
import getters from 'getters';
import mutations from 'mutations';

import { DateTime } from 'luxon';

const state = {
  diaries: [],
  scopedDiary: {},
  scopedDiaryDays: [],
  scopedDiaryWeeks: [],
  scopedDay: DateTime.local().toSQL(),
  loadingDiaryInProgress: false,
  savingDiaryInProgress: false
}

export default {
  state,
  getters,
  actions,
  mutations
}