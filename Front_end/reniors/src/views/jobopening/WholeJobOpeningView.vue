<template>
  <div>
    <SearchBar />
    <div class="jobopening-container">
      <RecommendTagList />
    </div>
    <JobopeningList />
  </div>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";
import RecommendTagList from "@/components/jobopening/RecommendTagList.vue";
import JobopeningList from "@/components/jobopening/JobopeningList.vue";
import { mapGetters } from "vuex";
export default {
  name: "JobopeningView",
  components: {
    SearchBar,
    RecommendTagList,
    JobopeningList,
  },
  computed: {
    ...mapGetters("jobopening", ["isrecommend"]),
  },
  created() {
    if (this.isrecommend) {
      document.documentElement.style.setProperty("--recommend-height", `380px`);
    } else {
      document.documentElement.style.setProperty("--recommend-height", `76px`);
    }
  },
};
</script>

<style scoped>
:root {
  --recommend-height: ;
}
.jobopening-container {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 10px;
  height: var(--recommend-height);
  /* height: 380px; */
}
</style>
