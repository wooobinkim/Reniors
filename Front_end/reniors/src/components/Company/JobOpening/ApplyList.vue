<template>
  <div>
    <div>{{ jobopeningdetail.title }}</div>
    <apply-list-item
      v-for="apply in applylist"
      :key="apply.id"
      :apply="apply"
      :jobOpeningId="this.$route.params.no"
    ></apply-list-item>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ApplyListItem from "./ApplyListItem.vue";
export default {
  components: { ApplyListItem },
  data() {
    return {
      jobopeningdetail: {},
    };
  },
  created() {
    this.getapplylist(this.$route.params.no);
    this.getJobOpening(this.$route.params.no);
  },
  watch: {
    jobopening: function (data) {
      this.jobopeningdetail = data;
    },
  },
  computed: {
    ...mapState("company", ["jobopening", "applylist"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpening", "getapplylist"]),
  },
};
</script>

<style></style>
