<template>
  <div>
    <h1 class="home-header">comm on</h1>
    <button @click="login" style="position: absolute; top: 100px; left: 100px;">login</button>
    <HomeSearch />
    <HomeNotice :login="false" />
    <HomeInfo />
    <HomeJobopeningList type="핫한 채용공고 🔥" :jobopenings="hotJobopenings" />
    <HomeJobopeningList type="신규 채용공고" :jobopenings="newJobopenings" />
    <HomeYoutubeList v-if="isYoutube" type="유튜브 크롤링" :youtubes="youtubes" />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import HomeSearch from './HomeSearch.vue'
import HomeNotice from './HomeNotice.vue'
import HomeInfo from './HomeInfo.vue'
import HomeJobopeningList from './HomeJobopeningList.vue'
import HomeYoutubeList from './HomeYoutubeList.vue'

export default {
  name: 'PreloginHome',
  components: {
    HomeSearch, HomeNotice, HomeInfo, HomeJobopeningList, HomeYoutubeList
  },
  setup () {
    const store = useStore()

    const hotJobopenings = computed(() => store.state.home.hotJobopenings)
    const newJobopenings = computed(() => store.state.home.newJobopenings)
    const youtubes = computed(() => store.getters['home/youtubes'])
    const isYoutube = computed(() => store.getters['home/isYoutube'])

    const login = () => store.dispatch('home/login')

    return {
      hotJobopenings, newJobopenings, youtubes, isYoutube, login
    }
  },
  mounted() {
    const store = useStore()
    const fetch = () => store.dispatch('home/fetchYoutubes', '뮤비')
    fetch()
  }
}
</script>

<style>

</style>