import Vue from 'vue';
import VueResource from 'vue-resource';
import router from '@/router';
import store from '@/store';
import { AuthPlugin } from '@/plugins';
import App from './App.vue';

Vue.config.productionTip = false;

// STYLES IMPORTS
import '@/assets/scss/index.scss';
import 'font-awesome/css/font-awesome.css';
import 'linearicons/dist/web-font/style.css';
import 'weather-icons/css/weather-icons.min.css';

// PLUGINS INSTALLATION
Vue.use(VueResource);
Vue.use(AuthPlugin, {
  store,
  baseUrl: 'http://diary.convene.sk'
})

// DIRECTIVES INSTALLATION
import * as directives from '@/directives';
for (const d in directives) {
  Vue.directive(d, directives[d]);
}

// FILTERS INSTALLATION
import * as filters from '@/filters';
for (const f in filters) {
  Vue.filter(f, filters[f]);
}

// INTERCEPTORS INSTALLATION
import * as interceptors from '@/api/interceptors';
for (const i in interceptors) {
  Vue.http.interceptors.push(interceptors[i]);
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
