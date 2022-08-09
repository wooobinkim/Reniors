<template>
  <div>
    <button @click="logout" style="position: absolute; top: 100px; left: 100px;">logout</button>
    <SearchBar />
    <HomeNotice :login="true"/>
    <HomeInfo />
    <HomeJobopeningList type="추천 채용공고" :jobopenings="recommendJobopenings" />
    <HomeYoutubeList v-if="isYoutube" type="맞춤 유튜브 크롤링" :youtubes="youtubes"/>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import SearchBar from '../SearchBar.vue'
import HomeNotice from './HomeNotice.vue'
import HomeInfo from './HomeInfo.vue'
import HomeJobopeningList from './HomeJobopeningList.vue'
import HomeYoutubeList from './HomeYoutubeList.vue'

export default {
  name: 'LoginHome',
  components: {
    SearchBar, HomeNotice, HomeInfo, HomeJobopeningList, HomeYoutubeList
  },
  setup () {
    const store = useStore()

    const recommendJobopenings = computed(() => store.state.home.recommendJobopenings)
    const youtubes = computed(() => store.getters['home/youtubes'])
    const isYoutube = computed(() => store.getters['home/isYoutube'])

    const logout = () => store.dispatch('removeToken')

    return {
      recommendJobopenings, youtubes, isYoutube, logout
    }
  },
  mounted() {
    const store = useStore()
    const fetch = () => store.dispatch('home/fetchYoutubes', '취업정보')
    fetch()
  }
}
</script>

<style>

</style>