<template>
  <div class="home">
    <div class="header container">
        <router-link class="header-logo" :to="{ name: 'home' }">
            <img src="@/assets/logo_cut.png" alt="logo">
        </router-link>
    </div>
    <div class="sub-title">
        <img class="persona" src="@/assets/persona.png" alt="persona">
      <div class="korean-logo-box">
          <img class="korean-logo" src="@/assets/logo_korean.png" alt="logo">
          <p style="color:var(--color-green-1)"> 다시 시작하는 나의 <span style="color:#F3620F">일</span>생</p>
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
  width: 100%;
  margin-bottom: 100px;
  padding-top: 50px;
}
.sub-title{
  display: flex;
  width: 100%;
  justify-content: center;
}
.persona{
  height: 200px;
  float: left;
}
.korean-logo{
  height: 100px;
  line-height: 200px;
}
.korean-logo-box{
  margin: auto 0;
}
.korean-logo-box > p{
  font-weight: bold;
  font-size: 20px;
  letter-spacing: 5px;
}
@media(max-width:760px){
  .persona{
    height: 100px;
  }
  .korean-logo{
    height: 40px;
  }
  .korean-logo-box > p{
    font-size: 14px;
    letter-spacing: 2px;
  }
}


</style>
