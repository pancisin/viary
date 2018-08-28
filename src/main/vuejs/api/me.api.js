import Vue from 'vue';

const ME_API_URL = '/api/v1/user/me';

export default {

  getMe (success) {
    Vue.http.get(`${ME_API_URL}`).then(response => {
      success(response.body);
    });
  },

  getDiaries (success) {
    Vue.http.get(`${ME_API_URL}/diary`).then(response => {
      success(response.body);
    })
  },

  postDiary (diary, success) {
    Vue.http.post(`${ME_API_URL}/diary`, diary).then(response => {
      success(response.body);
    })
  }
};
