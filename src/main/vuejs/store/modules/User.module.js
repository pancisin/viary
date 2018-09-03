import AuthApi from '@/api/auth.api';
import MeApi from '@/api/me.api';
import * as types from '@/store/mutation-types';

const state = {
  user: null,
  authenticated: false,
  loadingUser: false
};

const getters = {
  user: state => state.user,
  authenticated: state => {
    return (
      state.authenticated ||
      window.localStorage.getItem('access_token') != null ||
      window.sessionStorage.getItem('access_token') != null
    );
  },
  loadingUser: state => state.loadingUser
};

const actions = {
  login ({ state, dispatch }, { credentials, remember }) {
    if (state.authenticated) {
      return;
    }

    return new Promise(resolve => {
      const storage = remember ? window.localStorage : window.sessionStorage;
      AuthApi.login(credentials, result => {
        storage.setItem('access_token', result.access_token);
        dispatch('initializeUser').then(resolve);
      });
    });
  },

  register ({ state, dispatch }, user) {
    return new Promise(resolve => {
      AuthApi.register(user, result => {

        dispatch('login', {
          credentials: {
            username: user.email,
            password: user.password
          },
          remember: true
        }).then(resolve);
      });
    });
  },

  initializeUser ({ commit }) {
    return new Promise(resolve => {
      commit(types.LOADING_USER, true);

      MeApi.getMe(user => {
        commit(types.SET_USER, { user });
        commit(types.LOADING_USER, false);

        resolve(user);
      });
    });
  },

  logout ({ commit, dispatch }) {
    return new Promise(resolve => {
      window.localStorage.removeItem('access_token');
      window.sessionStorage.removeItem('access_token');

      commit(types.SET_USER, { user: null });

      resolve();

      // dispatch('resetModelModule');
      // dispatch('resetApiModule');
    });
  }
};

const mutations = {
  [types.SET_USER] (state, { user }) {
    state.user = { ...user };
    state.authenticated = user != null;
  },

  [types.LOADING_USER] (state, loading_state) {
    state.loadingUser = loading_state;
  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
