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
      <dropdown-item class="bdT" @click="logoutNow">
        Logout 
        <!-- <i class="fa fa-angle-right fsz-xs mL-10"/> -->
      </dropdown-item>
    </dropdown>
  </div>

    
</template>

<script>
import { Dropdown, DropdownItem } from '@/components/elements/dropdown'
import { mapGetters, mapActions } from 'vuex';

export default {
  components: {
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
    }
  }
}
</script>

<style>

</style>
