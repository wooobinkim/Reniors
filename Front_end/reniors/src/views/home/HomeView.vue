<template>
  <div class="home">
    <SearchBar />
    <HomeNotice :login="isLogin"/>
    <HomeInfo />
    <div v-if="isLogin">
      <HomeJobopeningList type="추천 채용공고" :jobopenings="hotJobopenings" />
      <HomeYoutubeList v-if="isYoutube" type="맞춤 유튜브 크롤링" :youtubes="youtubes"/>
    </div>
    <div v-else>
      <HomeJobopeningList type="핫한 채용공고 🔥" :jobopenings="hotJobopenings" />
      <HomeYoutubeList v-if="isYoutube" type="유튜브 크롤링" :youtubes="youtubes" />
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import SearchBar from '@/components/SearchBar.vue'
import HomeNotice from '@/components/home/HomeNotice.vue'
import HomeInfo from '@/components/home/HomeInfo.vue'
import HomeJobopeningList from '@/components/home/HomeJobopeningList.vue'
import HomeYoutubeList from '@/components/home/HomeYoutubeList.vue'

export default {
  name: 'HomeView',
  components: {
    SearchBar, HomeNotice, HomeInfo, HomeJobopeningList, HomeYoutubeList
  },
  setup() {
    const store = useStore()

    const fetchYoutube = () => store.dispatch('home/fetchYoutubes', '취업정보')
    const fetchHot = () => store.dispatch('home/fetchHot')
    fetchYoutube()
    fetchHot()

    const isLogin = computed(() => store.getters['isLogginedIn'])
    const hotJobopenings = computed(() => store.getters['home/hotJobopenings'])
    const recommendJobopenings = computed(() => store.state.home.recommendJobopenings)
    const youtubes = computed(() => store.getters['home/youtubes'])
    const isYoutube = computed(() => store.getters['home/isYoutube'])

    return {
      isLogin, hotJobopenings, recommendJobopenings, youtubes, isYoutube,
    }

  },
}
</script>

<style>
.home {
  max-width: 720px;
}
</style>
