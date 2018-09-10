import Vue from 'vue';

const ME_API_URL = '/api/v1/user/me';

export default {
  getMe (success) {
    Vue.http.get(`${ME_API_URL}`).then(response => {
      success(response.body);
    });
  }
};
