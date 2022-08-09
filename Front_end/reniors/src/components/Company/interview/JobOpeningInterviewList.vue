<template>
  <div>회사 공고</div>
  <job-opening-interview-list-item
    v-for="jobopening in jobopeninglist"
    :key="jobopening.id"
    :jobopening="jobopening"
  ></job-opening-interview-list-item>
</template>

<script>
import { mapActions, mapState } from "vuex";
import JobOpeningInterviewListItem from "./JobOpeningInterviewListItem.vue";
export default {
  components: { JobOpeningInterviewListItem },
  created() {
    this.getJobOpeningList();
  },
  watch: {},
  computed: {
    ...mapState("company", ["jobopeninglist"]),
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
