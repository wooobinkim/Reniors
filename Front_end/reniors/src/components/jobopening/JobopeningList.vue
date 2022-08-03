<template>
  <div class="jobopening-list">
    <JobopeningItem v-for="jobopening in jobopenings" :key="jobopening.job_opening_id" :jobopening="jobopening" />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import JobopeningItem from './JobopeningItem.vue'

export default {
  name: 'JobopeningList',
  components: {
    JobopeningItem,
  },
  setup() {
    const store = useStore()

    console.log(store)
    const fetchJobopenings = () => store.dispatch('jobopening/fetchJobopenings')
    fetchJobopenings()

    const jobopenings = computed(() => store.getters['jobopening/jobopenings'])

    return {
      jobopenings,
    }
  }
}
</script>

<style>
.jobopening-list {
  display: grid;
  grid-template-columns: 170px 170px;
  margin-top: 10px;
}
</style>