<template>
  <div>
    <SearchBar />
    <div class="jobopening-container">
      <!-- <RecommendTagList />
      <JobopeningList /> -->
      <JobOpeningMainPage/>
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar.vue'
// import RecommendTagList from '@/components/jobopening/RecommendTagList.vue'
// import JobopeningList from '@/components/jobopening/JobopeningList.vue'
import JobOpeningMainPage from '@/components/jobopening/JobOpeningMainPage.vue'

export default {
  name: 'JobopeningView',
  components: {
    SearchBar,
    // RecommendTagList, 
    // JobopeningList,
    JobOpeningMainPage,
  }
}
</script>

<style>
.jobopening-container {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 10px;
}
</style>