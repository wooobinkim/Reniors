<template>
  <div>
    <h1 class="home-header">comm on</h1>
    <HomeNotice />
    <HomeInfo />
    <HomeJobopeningList type="핫한 채용공고 🔥" :jobopenings="hotJobopenings" />
    <HomeJobopeningList type="신규 채용공고" :jobopenings="newJobopenings" />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import HomeNotice from './HomeNotice.vue'
import HomeInfo from './HomeInfo.vue'
import HomeJobopeningList from './HomeJobopeningList.vue'

export default {
  name: 'PreloginHome',
  components: {
    HomeNotice, HomeInfo, HomeJobopeningList,
  },
  setup () {
    const store = useStore()

    const hotJobopenings = computed(() => store.state.home.hotJobopenings)
    const newJobopenings = computed(() => store.state.home.newJobopenings)

    return {
      hotJobopenings, newJobopenings,
    }
  },
}
</script>

<style>

</style>