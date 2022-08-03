<template>
  <div>
    <SearchBar />
    <HomeNotice :login="false" />
    <HomeInfo />
    <HomeJobopeningList v-if="isFetch" type="핫한 채용공고 🔥" :jobopenings="hotJobopenings" />
    <HomeJobopeningList v-if="isFetch" type="신규 채용공고" :jobopenings="newJobopenings" />
    <HomeYoutubeList v-if="isYoutube" type="유튜브 크롤링" :youtubes="youtubes" />
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
  name: 'PreloginHome',
  components: {
    SearchBar, HomeNotice, HomeInfo, HomeJobopeningList, HomeYoutubeList
  },
  setup () {
    const store = useStore()

    const fetchHome = () => store.dispatch('home/fetchHome', '뮤비')
    fetchHome()

    const hotJobopenings = computed(() => store.state.home.hotJobopenings)
    const newJobopenings = computed(() => store.state.home.newJobopenings)
    const youtubes = computed(() => store.getters['home/youtubes'])
    const isFetch = computed(() => store.getters['home/isJobopenings'])
    const isYoutube = computed(() => store.getters['home/isYoutube'])

    return {
      hotJobopenings, newJobopenings, youtubes, isYoutube, isFetch
    }
  },
}
</script>

<style>

</style>