import DiaryApi from '@/api/diary.api';
import MeApi from '@/api/me.api';
import * as types from '@/store/mutation-types';

const state = {
  diaries: [],
  scopedDiary: {}
}

const getters = {
  diaries: state => state.diaries,
  scopedDiary: state => state.scopedDiary
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
  }
}

const mutations = {
  [types.SET_DIARIES] (state, { diaries }) {
    state.diaries = diaries;
  },

  [types.SCOPE_DIARY] (state, { diary }) {
    state.scopedDiary = diary;
  }
}
 
export default {
  state,
  getters,
  actions,
  mutations
}