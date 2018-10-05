import Vue from 'vue';
import App from './App.vue';
import VueResource from 'vue-resource';
import router from '@/router';
import store from '@/store';
import { AuthPlugin, WebSocketPlugin } from 'diary-core/dist/diary-core.common.js';

// STYLES IMPORTS
// import '@/assets/scss/index.scss';
import 'diary-core/dist/diary-core.css';

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

Vue.use(VueResource);
Vue.config.productionTip = false;

Vue.use(WebSocketPlugin, {})

// INTERCEPTORS INSTALLATION
import * as interceptors from '@/api/interceptors';
for (const i in interceptors) {
  Vue.http.interceptors.push(interceptors[i]);
}

// PLUGINS INSTALLATION
Vue.use(AuthPlugin, {
  store
});

export default new Vue({
  components: { App },
  router,
  store,
  render: h => h(App)
}).$mount('#app')
