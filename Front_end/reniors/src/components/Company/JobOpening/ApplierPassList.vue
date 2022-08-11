<template>
  <div>
    <template v-for="apply in applylist" :key="apply.id">
      <template v-if="apply.jobOpeningProcess == '최종합격'">
        <div>
          <applier-pass-list-item
            :apply="apply"
            :jobOpeningId="this.$route.params.no"
          >
          </applier-pass-list-item>
        </div>
        <hr>
      </template>
    </template>
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapState } from "vuex";
</script>
<script>
import ApplierPassListItem from "./ApplierInterviewListItem.vue";
export default {
  components: {
    ApplierPassListItem,
  },
  props: {
    // apply: Object,
    jobopeningdetail:Object,
  },

  created(){
    this.getapplylist(this.$route.params.no);
  },
  computed: {
    ...mapState("company", ["jobopening","applylist"]),
  },
  methods: {
    ...mapActions("company", ["getapplylist", "progressJobOpening","updateApply"]),

    resumeview() {
      this.$router.push({
        name: "resumeview",
        params: { no: this.apply.userId },
      });
    },
  },
};
</script>

<style></style>
