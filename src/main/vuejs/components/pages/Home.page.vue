<template>
  <div class="row">
    <div class="col-md-4">
      <div class="bdrs-3 ov-h bgc-white bd mB-20">
        <div class="bgc-grey-600 p-20 c-white">
          <h5 class="fw-300 mB-0">Apis</h5>
        </div>

        <div class="p-20">
          <button
            class="btn btn-outline-primary btn-sm"
            @click="editApi({})">Add api</button>
        </div>
      </div>
    </div>
    <div class="col-md-8">
      <div class="bdrs-3 bgc-white bd">
        <h5 class="p-20 m-0">Service updates</h5>
      </div>
    </div>

      <modal :show.sync="displayApiEditModal">
      <span slot="header">Edit api</span>
      <div slot="body">
      
      </div>
    </modal>
  </div>
</template>

<script>
import { Modal } from '@/components/elements';
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'HomePage',
  components: {
    Modal
  },
  data () {
    return {
      displayApiEditModal: false,
      selectedApi: {}
    };
  },
  computed: {
    ...mapGetters(['apis'])
  },
  methods: {
    ...mapActions(['initalizeUserApis']),
    editApi (api) {
      this.selectedApi = api;
      this.displayApiEditModal = true;
    },
    apiUpdated () {
      this.displayApiEditModal = false;
    }
  }
};
</script>
