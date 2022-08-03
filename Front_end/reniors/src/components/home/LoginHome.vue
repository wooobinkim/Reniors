<template>
  <div>
    <SearchBar />
    <HomeNotice :login="true"/>
    <HomeInfo />
    <HomeJobopeningList v-if="isFetch" type="추천 채용공고" :jobopenings="recommendJobopenings" />
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

    const fetchHome = () => store.dispatch('home/fetchHome', '취업정보')
    fetchHome()

    const recommendJobopenings = computed(() => store.state.home.recommendJobopenings)
    const youtubes = computed(() => store.getters['home/youtubes'])
    const isFetch = computed(() => store.getters['home/isJobopenings'])
    const isYoutube = computed(() => store.getters['home/isYoutube'])

    return {
      recommendJobopenings, youtubes, isYoutube, isFetch,
    }
  },
}
</script>

<style>

</style>