import Vue from 'vue';

export default (request, next) => {
  next(response => {
    if (response.status === 500 && Vue.prototype.$error != null) {
      Vue.prototype.$error(response.body.message);
    }
  });
};
