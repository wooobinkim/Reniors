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
export default {
  name: 'JobopeningView',
  components: {
    RecommendTagList, 
    JobopeningList,
    HeaderComponent
  }
}
</script>

<style scoped>
.jobopening-container {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 10px;
}
</style>