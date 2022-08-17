<template>
  <div class="jobopening-list">
    <!-- {{ jobopenings }} -->
    <div v-if="isJobopenings">
      <div class="jobopening-msg">검색 결과</div>
      <JobopeningItem
        v-for="(jobopening, index) in jobopenings"
        :key="index"
        :jobopening="jobopening"
      />
      <button v-show="!isLast" class="more-btn" @click="pageUp()">
        더보기
      </button>
    </div>
    <div v-else>아직 채용공고가 없어요!</div>
  </div>
</template>

<script>
import { computed } from "vue";
import { mapActions, mapGetters, useStore } from "vuex";
import JobopeningItem from "./JobopeningItem.vue";

export default {
  name: "JobopeningList",
  components: {
    JobopeningItem,
  },
  setup() {
    const store = useStore();

    const jobopenings = computed(() => store.getters["jobopening/jobopenings"]);
    const isJobopenings = computed(
      () => store.getters["jobopening/isJobopenings"]
    );

    return {
      jobopenings,
      isJobopenings,
    };
  },
  methods: {
    ...mapActions("jobopening", [
      "fetchJobopenings",
      "clearJobopenings",
      "setCurrPage",
    ]),
    async pageUp() {
      await this.setCurrPage(this.currPage + 1);
    },
  },
  async created() {
    await this.setCurrPage(0);
    await this.clearJobopenings();
    await this.fetchJobopenings({
      page: 0,
    });
    console.log(this.jobopenings);
  },
  computed: {
    ...mapGetters("jobopening", ["jobopenings", "isLast", "currPage"]),
  },
  watch: {
    async currPage() {
      if (this.currPage != 0) {
        await this.fetchJobopenings({
          page: this.currPage,
        });
      }
    },
  },
};
</script>

<style scoped>
.jobopening-msg {
  display: flex;
  margin-top: 10px;
  margin-left: 12px;
}
/* .jobopening-list > div {
  display: grid;
  grid-template-columns: 170px 170px;
  margin-top: 10px;
}

@media screen and (min-width: 720px) {
  .jobopening-list > div {
    grid-template-columns: 20vh 20vh 20vh;
  }
} */
.more-btn {
  margin-top: 30px;
  margin-bottom: 200px;
  border-radius: 5px;
  border: none;
  padding: 8px 40px;
  background-color: var(--color-red-2);
  color: white;
  font-weight: bold;
}
</style>
