<template>
  <div class="diary-info d-flex jc-sb ai-c">
    <span>{{ scopedDay.monthLong }} - {{ scopedDay.weekNumber }}. týždeň {{ scopedDay.year }}</span>
    
    <dropdown
      class="notifications">
      <span slot="link">
        <i class="fa fa-2x fa-cog"></i>
      </span>

      <dropdown-item>
        Account
      </dropdown-item>
      <dropdown-item>
        Profile
      </dropdown-item>
      <dropdown-item @click="displaySwitchDiaryModal = true">
        Switch diary
      </dropdown-item>
      <dropdown-item class="bdT" @click="logoutNow">
        Logout 
        <!-- <i class="fa fa-angle-right fsz-xs mL-10"/> -->
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
    ...mapGetters(['scopedDay'])
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
