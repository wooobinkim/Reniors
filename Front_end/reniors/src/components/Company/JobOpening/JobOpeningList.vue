<template>
  <div>
    <div class="company-jobOpening-btn-box">
      <div @click="changeing()" :class="{ 'now-tab': this.tab == 'ing' }">
        <div class="number">
          {{ jobopeninglisting.length }}
        </div>
        <div>채용 중</div>
      </div>

      <div @click="changeed()" :class="{ 'now-tab': this.tab == 'ed' }">
        <div class="number">
          {{ jobopeninglisted.length }}
        </div>
        <div>채용 완료</div>
      </div>
    </div>
  </div>

    <template v-if="tab == 'ing'">
      <div class="company-jobOpening-list-title">현재 채용중인 공고</div>
        <div class="company-jobOpening-list-box">
                <job-opening-ing-list-item
        v-for="jobopening in jobopeninglisting"
        :key="jobopening.id"
        :jobopening="jobopening"
      ></job-opening-ing-list-item>
        </div>

    </template>
    <template v-if="tab == 'ed'">
      <div class="company-jobOpening-list-title">채용완료 공고</div>
        <div class="company-jobOpening-list-box">
                <job-opening-ed-list-item
        v-for="jobopening in jobopeninglisted"
        :key="jobopening.id"
        :jobopening="jobopening"
      >
      </job-opening-ed-list-item>
        </div>

    </template>

  <button class="create-jobOpening-btn" @click="regjobopening()">
    채용 등록하기
  </button>
</template>

<script scoped>
import { mapActions, mapGetters, mapMutations } from "vuex";
import JobOpeningIngListItem from "./JobOpeningIngListItem.vue";
import JobOpeningEdListItem from "./JobOpeningEdListItem.vue";
export default {
  data() {
    return {
      tab: "ing",
    };
  },
  components: { JobOpeningIngListItem, JobOpeningEdListItem },
  async created() {
    await this.CLEAR_JOBOPENING_LIST;
    await this.getJobOpeningList();
  },
  watch: {},
  mounted() {},
  computed: {
    ...mapGetters("company", ["jobopeninglisting", "jobopeninglisted"]),
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

<style>
.company-jobOpening-btn-box {
  display: flex;
}
.company-jobOpening-btn-box > div {
  width: 138px;
  margin: 0 10px;
  padding: 10px;
  border-radius: 5px;
  background-color: var(--color-black-4);
}
.company-jobOpening-btn-box > div > div {
  font-weight: bold;
  font-size: 14px;
}
.company-jobOpening-btn-box > div > .number {
  font-size: 24px;
}
.now-tab {
  color: white;
  background-color: var(--color-green-1) !important;
}
.company-jobOpening-list-title {
  font-size: 24px;
  font-weight: 1000;
  width: 320px;
  text-align: left;
  margin-top: 35px;
  margin-bottom: 22px;
}
.create-jobOpening-btn {
  border: none;
  border-radius: 10px;
  background-color: var(--color-green-1);
  padding: 10px 100px;
  color: white;
  font-weight: bold;
  font-size: 20px;
  position: fixed;
  bottom: 70px;
}
.company-jobOpening-list-box{
  height: 53vh;
  overflow-y: scroll;
  /* height: calc(100vh-20vh); */

}
@media ( min-width: 768px ) {
  .company-jobOpening-list-box{
        height: 65vh;
  }
}
.company-jobOpening-list-box::-webkit-scrollbar{
  width: 0;
}
</style>
