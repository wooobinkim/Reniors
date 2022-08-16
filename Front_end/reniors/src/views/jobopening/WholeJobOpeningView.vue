<template>
  <div>
    <!-- header -->
    <div class="header container-fluid">
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
    </div>

    <div class="jobopening-container">
      <RecommendTagList />
    </div>
    <JobopeningList />
  </div>
</template>

<script>
import RecommendTagList from "@/components/jobopening/RecommendTagList.vue";
import JobopeningList from "@/components/jobopening/JobopeningList.vue";
import { mapGetters } from "vuex";
export default {
  name: "JobopeningView",
  components: {
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
.header {
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
}
.header img {
  height: 38px;
  margin: auto;
  width: auto;
}
.jobopening-container {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 10px;
  margin-top: 56px;
  /* height: 380px; */
}
</style>
