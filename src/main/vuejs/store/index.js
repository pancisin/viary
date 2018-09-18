import Vue from 'vue';
import Vuex from 'vuex';
import SettingsModule from '@/store/modules/Settings.module';

Vue.use(Vuex);

const getters = {
  globalLoading: (state, getters) => {
    return getters.loadingInitialData;
  }
};

/* eslint-disable no-new */
const store = new Vuex.Store({
  strict: true,
  getters,
  modules: {
    settingsModule: SettingsModule
  }
});

export default store;
