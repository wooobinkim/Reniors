<template>
  <div class="jobopening-list">
    {{jobopenings}}
    <div v-if="isJobopenings">
      <JobopeningItem
        v-for="(jobopening, index) in jobopenings"
        :key="index"
        :jobopening="jobopening.jobOpeningResponse"
      />
    </div>
    <div v-else>아직 채용공고가 없어요!</div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import JobopeningItem from './JobopeningItem.vue'

export default {
  name: "JobopeningList",
  components: {
    JobopeningItem,
  },
  setup() {
    const store = useStore()

    const jobopenings = computed(() => store.getters['jobopening/jobopenings'])
    const isJobopenings = computed(() => store.getters['jobopening/isJobopenings'])

    return {
      jobopenings, isJobopenings,
    };
  },
};
</script>

<style>
.jobopening-list > div {
  display: grid;
  grid-template-columns: 170px 170px;
  margin-top: 10px;
}

@media screen and (min-width: 720px) {
  .jobopening-list > div {
    grid-template-columns: 20vh 20vh 20vh;
  }
}
</style>