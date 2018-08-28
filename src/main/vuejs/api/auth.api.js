import Vue from 'vue';

export default {
  login (credentials, success) {
    var data = {
      ...credentials,
      grant_type: 'password'
    };

    Vue.http
      .post('/oauth/token', data, {
        emulateJSON: true,
        headers: {
          'Authorization': `Basic ${window.btoa('testjwtclientid:XY7kmzoNzl100')}`
        }
      })
      .then(response => {
        success(response.body);
      });
  },

  register (user, success) {
    Vue.http.post('/api/register', user).then(response => {
      success(response.body);
    });
  }
};
