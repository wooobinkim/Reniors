<template>
  <div>
    <header-component></header-component>
    <div class="jobopening-container">
      <RecommendTagList />
    </div>
    <JobopeningList />
  </div>
</template>

<script>
import RecommendTagList from '@/components/jobopening/RecommendTagList.vue'
import JobopeningList from '@/components/jobopening/JobopeningList.vue'
import HeaderComponent from '@/components/HeaderComponent.vue'
import { mapGetters } from "vuex";
export default {
  name: "JobopeningView",
  components: {
    RecommendTagList, 
    JobopeningList,
    HeaderComponent
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
