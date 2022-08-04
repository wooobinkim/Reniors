<template>
  <div>
    <div class="jobopening-box">
      <h3>상세모집내용</h3>
      <hr>
      <p>{{ jobopening.contents }}</p>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'DetailContent',
  setup() {
    const store = useStore()

    const jobopening = computed(() => store.getters['jobopening/selectedJobopening'])

    return {
      jobopening,
    }
  }
}
</script>

<style>
/* same as DetailCondition.vue */
</style>