import RootApi from '@/api/root.api';
import * as types from '@/store/mutation-types';

const state = {
  initialData: {},
  loadingInitialData: false
};

const getters = {
  loadingInitialData: state => state.loadingInitialData
};

const actions = {
  initializeApplication ({ commit }) {
    return new Promise(resolve => {
      commit(types.SET_INIT_IN_PROGRESS, true);

      RootApi.getInitialData(initialData => {
        commit(types.SET_INIT, { initialData });
        commit(types.SET_INIT_IN_PROGRESS, false);
        resolve(initialData);
      });
    });
  }
};

const mutations = {
  [types.SET_INIT] (state, { initialData }) {
    state.initialData = initialData;
  },

  [types.SET_INIT_IN_PROGRESS] (state, inProgress) {
    state.loadingInitialData = inProgress;
  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
