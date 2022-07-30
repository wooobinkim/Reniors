<template>
  <div>
    <h1 class="home-header">comm on</h1>
    <HomeNotice />
    <HomeInfo />
    <HomeJobopeningList type="recommend" :jobopenings="recommendJobopenings" />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import HomeNotice from './HomeNotice.vue'
import HomeInfo from './HomeInfo.vue'
import HomeJobopeningList from './HomeJobopeningList.vue'

export default {
  name: 'LoginHome',
  components: {
    HomeNotice, HomeInfo, HomeJobopeningList,
  },
  setup () {
    const store = useStore()

    console.log(store.getters["home/recommendJobopenings"])

    const recommendJobopenings = computed(() => store.state.home.recommendJobopenings)

    return {
      recommendJobopenings,
    }
  },
}
</script>

<style>

</style>