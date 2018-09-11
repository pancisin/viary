import authStore from './_store';

const MODULE_NAMESPACE = '$_auth'
import { LoginForm, RegisterForm } from './_components'

import AuthInterceptor from './_api/auth.interceptor';

const AuthPlugin = {
  
  install(Vue, { store, baseUrl }) {
    if (!store) {
      throw new Error('Please provide vuex store.');
    }
    
    store.registerModule(MODULE_NAMESPACE, authStore({ baseUrl: baseUrl || '' }));

    Vue.http.interceptors.push(AuthInterceptor);

    Vue.component('login-form', LoginForm)
    Vue.component('register-form', RegisterForm)
  }
};

export default AuthPlugin;
