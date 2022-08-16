<template>
  <div class="home">
    <div class="header">
        <router-link class="header-logo" :to="{ name: 'home' }">
            <img src="@/assets/logo_cut.png" alt="logo">
        </router-link>
    </div>
    <div class="sub-title row">
      <div class="col-4">
        <img style="width:30vw; height: 40vw; margin: 0 0 8px 8px;" src="@/assets/persona.png" alt="persona">
      </div>
      <div class="col-8" style="display:flex; align-items:center; justify-content: center;">
        <div>
          <img src="@/assets/logo_korean.png" alt="logo">
          <p style="margin: 8px 0"> 다시 시작하는 나의 <span style="color:#F3620F">일</span>생</p>
        </div>
      </div>
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
import HomeNotice from '@/components/home/HomeNotice.vue'
import HomeInfo from '@/components/home/HomeInfo.vue'
import HomeJobopeningList from '@/components/home/HomeJobopeningList.vue'
import HomeYoutubeList from '@/components/home/HomeYoutubeList.vue'

export default {
  name: 'HomeView',
  components: {
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
.header{
    min-width: 100vw;
    height: 48px;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    top: 0;
}
.header img{
    height: 38px;
    margin: auto;
    width: auto;
}
.home {
  max-width: 720px;
  margin-bottom: 100px;
}
.sub-title{
  margin: 48px auto 0;
  font-weight: 1000;
  color: var(--color-green-1);
  letter-spacing: 0.1rem;
  background-color: #FFF5F0;
}
.sub-title img{
  width: 50vw;
  height: 13vw;
  margin: 8px auto 0;
}

/* .header-logo > img {
  margin-bottom: 0 !important;
} */

</style>
