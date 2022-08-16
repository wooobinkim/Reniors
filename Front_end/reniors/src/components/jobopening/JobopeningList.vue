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
    </div>
    <div v-else>아직 채용공고가 없어요!</div>
  </div>
</template>

<script>
import { computed } from "vue";
import { mapActions, useStore } from "vuex";
import JobopeningItem from "./JobopeningItem.vue";

export default {
  name: "JobopeningList",
  components: {
    JobopeningItem,
  },
  setup() {
    const store = useStore();

    const jobopenings = computed(() => store.getters["jobopenings"]);
    const isJobopenings = computed(
      () => store.getters["isJobopenings"]
    );

    return {
      jobopenings,
      isJobopenings,
    };
  },
  methods: {
    ...mapActions(["fetchJobopenings"]),
  },
  created() {
    this.fetchJobopenings();
  },
};
</script>

<style scoped>
.jobopening-msg{
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
</style>
