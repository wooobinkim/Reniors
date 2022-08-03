<template>
  <div class="jobopening-list">
    <JobopeningItem :jobopening="jobopening" />
  </div>
</template>

<script>
import JobopeningItem from './JobopeningItem.vue'

export default {
  name: 'JobopeningList',
  components: {
    JobopeningItem,
  },
  setup() {
    const jobopenings = [
      {
        title: '어쩌고 회사이름',
      }
    ]

    return {
      jobopenings,
    }
  }
}
</script>

<style>
.jobopening-list {
  margin: 20px;
}
</style>