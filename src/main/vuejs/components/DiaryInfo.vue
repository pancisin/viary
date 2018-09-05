<template>
  <div class="diary-info d-flex jc-sb ai-c">
    <span>{{ scopedDay.monthLong }} - {{ scopedDay.weekNumber }}. týždeň {{ scopedDay.year }}</span>
    
    <dropdown
      class="notifications">
      <span slot="link">
        <i class="fa fa-2x fa-cog"></i>
      </span>

      <li>
        <ul class="ovY-a pos-r scrollable lis-n p-0 m-0 fsz-sm">
          <li>
            <a class="peers fxw-nw td-n p-20 bdB c-grey-800 cH-blue bgcH-grey-100">
              <div class="peer mR-15">
                <img
                  class="w-3r bdrs-50p"
                  src="https://randomuser.me/api/portraits/men/1.jpg"
                  alt="">
              </div>
              <div class="peer peer-greed">
                <span>
                  <span class="fw-500">{{ user.display_name }}</span>
                  <p class="c-grey-600 m-0">{{ scopedDiary.name }}</p>
                </span>
                <!-- <p class="m-0">
                  <small class="fsz-xs">5 mins ago</small>
                </p> -->
              </div>
            </a>
          </li>
        </ul>
      </li>
      <dropdown-item>
        <!-- <i class="fa fa-shopping-basket"></i> -->
        Purchase
      </dropdown-item>
      <dropdown-item>
        <!-- <i class="fa fa-cog"></i> -->
        Options
      </dropdown-item>
      <dropdown-item @click="displaySwitchDiaryModal = true">
        <!-- <i class="fa fa-switch"></i> -->
        Switch diary
      </dropdown-item>
      <dropdown-item class="bdT" @click="logoutNow">
        Logout 
      </dropdown-item>
    </dropdown>

    <modal :show.sync="displaySwitchDiaryModal">
      <span slot="header">Switch diary</span>
      <div slot="body">
        <diary-switch @switched="switchedDiary" />
      </div>
    </modal>
  </div>
</template>

<script>
import { Dropdown, DropdownItem } from '@/components/elements/dropdown';
import { mapGetters, mapActions } from 'vuex';
import { Modal } from '@/components/elements';
import DiarySwitch from './DiarySwitch';

import gravatar from 'gravatar';

export default {
  data () {
    return {
      displaySwitchDiaryModal: false
    }
  },
  components: {
    Modal,
    DiarySwitch,
    Dropdown, 
    DropdownItem
  },
  computed: {
    ...mapGetters(['scopedDay', 'user', 'scopedDiary']),
    profilePic () {
      return gravatar.url(this.user.email)
    }
  },
  methods: {
    ...mapActions(['logout']),
    logoutNow() {
      this.logout().then(() => {
        this.$router.push({ name: 'signin' })
      })
    },
    switchedDiary () {
      this.displaySwitchDiaryModal = false;
    }
  }
}
</script>
