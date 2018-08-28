import Vue from 'vue';

const ROOT_API_URL = '/api/v1';

export default {
  getInitialData (success) {
    Vue.http.get(`${ROOT_API_URL}/initial`).then(response => {
      success(response.body);
    });
  }
};
