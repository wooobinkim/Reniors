<template>
  <div>
    <div class="jobopening-box">
      {{ jobopening }}
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'DetailCondition',
  setup() {
    const store = useStore()
    
    const jobopening = computed(() => store.getters['jobopening/selectedJobopening'])

    return {
      jobopening, 
    }
  },
}
</script>

<style>
.jobopening-box {
  background-color: white;
  margin: 10px;
}
</style>