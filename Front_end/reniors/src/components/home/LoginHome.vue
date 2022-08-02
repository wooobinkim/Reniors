<template>
  <div>
    <h1 class="home-header">comm on</h1>
    <button @click="logout">logout</button>
    <HomeNotice :login="true"/>
    <HomeInfo />
    <HomeJobopeningList type="추천 채용공고" :jobopenings="recommendJobopenings" />
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

    const recommendJobopenings = computed(() => store.state.home.recommendJobopenings)

    const logout = () => store.dispatch('home/logout')
    console.log('logout')

    return {
      recommendJobopenings, logout
    }
  },
}
</script>

<style>

</style>