<template>
  <div class="home">
    <HeaderComponent />
    <div class="sub-title">
      다시 시작하는 나의 <p>일</p>생
    </div>
    <HomeInfo />
    <HomeNotice :login="isLogin"/>

    <hr/>
    <div v-if="isLogin">
      <HomeJobopeningList type="추천 채용공고" :jobopenings="hotJobopenings" />
      <hr>
      <HomeYoutubeList type="추천 영상" :youtubes="youtubes"/>
    </div>
    <div v-else>
      <HomeJobopeningList type="핫한 채용공고 🔥" :jobopenings="hotJobopenings" />
      <hr>
      <HomeYoutubeList type="추천 영상" :youtubes="youtubes" />
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import HeaderComponent from '@/components/HeaderComponent.vue'
import HomeNotice from '@/components/home/HomeNotice.vue'
import HomeInfo from '@/components/home/HomeInfo.vue'
import HomeJobopeningList from '@/components/home/HomeJobopeningList.vue'
import HomeYoutubeList from '@/components/home/HomeYoutubeList.vue'

export default {
  name: 'HomeView',
  components: {
    HeaderComponent,
    HomeNotice,
    HomeInfo,
    HomeJobopeningList,
    HomeYoutubeList,
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

<style scoped>
.home {
  max-width: 720px;
  margin-bottom: 100px;
}
.sub-title{
  margin-bottom: 30px;
  font-weight: 1000;
  color: var(--color-green-1);
  letter-spacing: 0.3rem;
  margin-top: -50px;
}
p{
  display: inline;
  font-size: 22px;
  color: var(--color-red-1);
}
.header-logo > img {
  margin-bottom: 0 !important;
}

</style>
