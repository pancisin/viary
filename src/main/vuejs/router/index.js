import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store';

Vue.use(VueRouter);

const require_auth = (to, from, next) => {
  if (!store.getters.authenticated) {
    next({
      path: '/signin',
      query: { redirect: to.fullPath }
    });
  } else {
    next();
  }
};

const afterAuth = (to, from, next) => {
  if (store.getters.authenticated) {
    // next(from.path);
    next({ name: 'home' });
  } else {
    next();
  }
};

const routes = [
  {
    path: '',
    name: 'landing',
    component: () => import('@/components/pages/Landing.page'),
    beforeEnter: afterAuth
  },
  {
    path: '/',
    component: () => import('@/components/layout/Base.layout'),
    beforeEnter: require_auth,
    children: [
      {
        path: '',
        name: 'home',
        component: () => import('@/components/pages/Home.page'),
        beforeEnter (to, from, next) {
          store.dispatch('initalizeUserApis').then(() => {
            next();
          });
        }
      },
    ]
  },
  {
    path: '/signin',
    name: 'signin',
    component: () => import('@/components/pages/Login.page'),
    beforeEnter: afterAuth
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('@/components/pages/Register.page'),
    beforeEnter: afterAuth
  }
];

/* eslint-disable no-new */
export default new VueRouter({
  linkExactActiveClass: 'active',
  routes,
  mode: 'hash' // history
});
