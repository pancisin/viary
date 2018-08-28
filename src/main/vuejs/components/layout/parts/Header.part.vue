<template>
  <div
    :class="cls"
    class="header navbar navbar-expand-md">
    <div class="header-container d-flex flex-wrap">
      <ul class="nav-left">
        <li>
          <a class="navbar-toggler sidebar-toggle fsz-lg" @click="navbarCollapsed = !navbarCollapsed">
            <i class="fa fa-bars"></i>
          </a>
        </li>
      </ul>

      <ul
        v-if="authenticated"
        class="nav-right ml-auto order-md-2">
        <dropdown
          tag="li"
          class="notifications">
          <span slot="link">
            <span class="counter bgc-red">3</span>
            <i class="fa fa-bell-o"/>
          </span>

          <dropdown-item class="pX-20 pY-15 bdB">
            <i class="fa fa-bell-o"/>
            <span class="fsz-sm fw-600 c-grey-900"> Notifications</span>
          </dropdown-item>
          <li>
            <ul class="ovY-a pos-r scrollable lis-n p-0 m-0 fsz-sm">
              <li>
                <a
                  href=""
                  class="peers fxw-nw td-n p-20 bdB c-grey-800 cH-blue bgcH-grey-100">
                  <div class="peer mR-15">
                    <img
                      class="w-3r bdrs-50p"
                      src="https://randomuser.me/api/portraits/men/1.jpg"
                      alt="">
                  </div>
                  <div class="peer peer-greed">
                    <span>
                      <span class="fw-500">John Doe</span>
                      <span class="c-grey-600">liked your <span class="text-dark">post</span>
                      </span>
                    </span>
                    <p class="m-0">
                      <small class="fsz-xs">5 mins ago</small>
                    </p>
                  </div>
                </a>
              </li>
              <li>
                <a
                  href=""
                  class="peers fxw-nw td-n p-20 bdB c-grey-800 cH-blue bgcH-grey-100">
                  <div class="peer mR-15">
                    <img
                      class="w-3r bdrs-50p"
                      src="https://randomuser.me/api/portraits/men/2.jpg"
                      alt="">
                  </div>
                  <div class="peer peer-greed">
                    <span>
                      <span class="fw-500">Moo Doe</span>
                      <span class="c-grey-600">liked your <span class="text-dark">cover image</span>
                      </span>
                    </span>
                    <p class="m-0">
                      <small class="fsz-xs">7 mins ago</small>
                    </p>
                  </div>
                </a>
              </li>
              <li>
                <a
                  href=""
                  class="peers fxw-nw td-n p-20 bdB c-grey-800 cH-blue bgcH-grey-100">
                  <div class="peer mR-15">
                    <img
                      class="w-3r bdrs-50p"
                      src="https://randomuser.me/api/portraits/men/3.jpg"
                      alt="">
                  </div>
                  <div class="peer peer-greed">
                    <span>
                      <span class="fw-500">Lee Doe</span>
                      <span class="c-grey-600">commented on your <span class="text-dark">video</span>
                      </span>
                    </span>
                    <p class="m-0">
                      <small class="fsz-xs">10 mins ago</small>
                    </p>
                  </div>
                </a>
              </li>
            </ul>
          </li>

          <dropdown-item class="pX-20 pY-15 ta-c bdT">
            View All Notifications <i class="ti-angle-right fsz-xs mL-10"/>
          </dropdown-item>
        </dropdown>

        <dropdown
          tag="li"
          class="test">
          <span
            slot="link"
            class="peers ai-c lh-1">
            <div class="peer mR-10">
              <img
                class="w-2r bdrs-50p"
                src="https://randomuser.me/api/portraits/men/10.jpg"
                alt="">
            </div>
            <div class="peer">
              <span class="fsz-sm c-grey-900">{{ user.display_name }}</span>
            </div>
            <div class="peer"/>
          </span>

          <dropdown-item :router-link-to="{ name: 'test' }">
            <i class="fa fa-user"/>
            Profile
          </dropdown-item>
          <dropdown-item :router-link-to="{ name: 'test' }">
            <i class="fa fa-cogs"/>
            Settings
          </dropdown-item>
          <dropdown-item @click="doLogout">
            <i class="fa fa-sign-out"/>
            Logout
          </dropdown-item>
        </dropdown>
      </ul>

      <ul
        v-else
        class="nav-right ml-auto order-md-1">
        <li class="nav-item">
          <router-link :to="{ name: 'signin' }" class="nav-link">
            Login
          </router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'signup' }" class="nav-link">
            Sign up
          </router-link>
        </li>
      </ul>

      <slide-transition>
      <div class="navbar-collapse" v-show="!navbarCollapsed">
        <ul class="navbar-nav nav-left">
          <li class="nav-item">
            <router-link to="/" class="nav-link">
              <i class="fa fa-home mR-10"/>
              Home
            </router-link>
          </li>
          <li v-if="authenticated" class="nav-item">
            <router-link :to="{ name: 'contribute' }" class="nav-link">
              <i class="fa fa-database mR-10"/>
              Contribute
            </router-link>
          </li>
          <li v-if="authenticated" class="nav-item">
            <router-link :to="{ name: 'dq-monitoring' }" class="nav-link">
              <i class="fa fa-eye mR-10"/>
              DQ monitoring
            </router-link>
          </li>
          <li v-if="authenticated" class="nav-item">
            <router-link :to="{ name: 'home' }" class="nav-link">
              <i class="fa fa-bar-chart mR-10"/>
              Data analysis
            </router-link>
          </li>
        </ul>
      </div>
      </slide-transition>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { Dropdown, DropdownItem } from '@/components/elements/dropdown';
import { SlideTransition } from '@/components/transitions';
export default {
  components: {
    Dropdown, DropdownItem, SlideTransition
  },
  data () {
    return {
      scrollY: 0,
      navbarCollapsed: true
    };
  },
  watch: {
    $route () {
      this.navbarCollapsed = true;
    }
  },
  computed: {
    ...mapGetters(['authenticated', 'user']),
    cls () {
      return this.scrollY > 0 || !this.navbarCollapsed ? 'shadow' : '';
    }
  },
  mounted () {
    window.addEventListener('scroll', e => {
      this.scrollY = window.scrollY;
    });
  },
  methods: {
    ...mapActions(['logout']),
    doLogout () {
      this.logout().then(() => {
        this.$router.replace('/');
      });
    }
  }
};
</script>
