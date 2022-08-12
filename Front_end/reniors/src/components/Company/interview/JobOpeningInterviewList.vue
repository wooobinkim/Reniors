<template>
  <div>회사 공고</div>
  <job-opening-interview-list-item
    v-for="jobopening in jobopeninglisting"
    :key="jobopening.id"
    :jobopening="jobopening"
  ></job-opening-interview-list-item>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
import JobOpeningInterviewListItem from "./JobOpeningInterviewListItem.vue";
export default {
  components: { JobOpeningInterviewListItem },
  created() {
    this.CLEAR_JOBOPENING_LIST;
    this.getJobOpeningList();
  },
  watch: {},
  computed: {
    ...mapGetters("company", ["jobopeninglisting"]),
    ...mapMutations("company", ["CLEAR_JOBOPENING_LIST"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpeningList"]),
    regjobopening() {
      this.$router.push({ name: "companyjobopeningregist" });
    },
  },
};
</script>

<style></style>
