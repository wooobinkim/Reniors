<template>
  <div>회사 공고페이지</div>
  <div>현재 채용중인 공고</div>
  <div>
    <span @click="changeing()">채용 중 {{ jobopeninglisting.length }}</span>
    <span @click="changeed()">채용 완료 {{ jobopeninglisted.length }}</span>
  </div>
  <template v-if="tab == 'ing'">
    <job-opening-ing-list-item
      v-for="jobopening in jobopeninglisting"
      :key="jobopening.id"
      :jobopening="jobopening"
    ></job-opening-ing-list-item>
  </template>
  <template v-if="tab == 'ed'">
    <job-opening-ed-list-item
      v-for="jobopening in jobopeninglisted"
      :key="jobopening.id"
      :jobopening="jobopening"
    >
    </job-opening-ed-list-item>
  </template>
  <button @click="regjobopening()">공고올리기</button>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import JobOpeningIngListItem from "./JobOpeningIngListItem.vue";
import JobOpeningEdListItem from "./JobOpeningEdListItem.vue";
export default {
  data() {
    return {
      tab: "ing",
    };
  },
  components: { JobOpeningIngListItem, JobOpeningEdListItem },
  created() {
    this.CLEAR_JOBOPENING_LIST;
    this.getJobOpeningList();
  },
  watch: {},
  computed: {
    ...mapState("company", ["jobopeninglisting", "jobopeninglisted"]),
    ...mapMutations("company", ["CLEAR_JOBOPENING_LIST"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpeningList"]),

    regjobopening() {
      this.$router.push({ name: "companyjobopeningregist" });
    },
    changeing() {
      this.tab = "ing";
    },
    changeed() {
      this.tab = "ed";
    },
  },
};
</script>

<style></style>
