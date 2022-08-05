<template>
  <div>회사 공고페이지</div>
  <div>현재 채용중인 공고</div>
  <job-opening-list-item
    v-for="jobopening in jobopeninglist"
    :key="jobopening.id"
    :jobopening="jobopening"
  ></job-opening-list-item>
  <button @click="regjobopening()">공고올리기</button>
</template>

<script>
import { mapActions, mapState } from "vuex";
import JobOpeningListItem from "./JobOpeningListItem.vue";
export default {
  components: { JobOpeningListItem },
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
