<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">마이 페이지</span>
      </div>
    </header>
    <br>
    <br>
    <div style="width: 312px; margin: auto;">
      <div class="profileimg">
        <img class="person" :src="this.currentUser.baseURL + this.currentUser.userProfile" alt="person">
      </div>
      <br>
      <br>
      <div class="myinfo">
        <div class="name">{{this.currentUser.name}}</div>
        <button><router-link :to="{ name: 'MyinfoEdit' }" style="text-decoration:none; color: #37BF99;">내 정보 수정</router-link></button>
      </div>

      <button class="resume"><router-link :to="{ name: 'ResumeDetail' }" style="text-decoration:none; color: white; font-weight: 700; font-size: 18px;"><img style="align: absmiddle; filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25)); margin-right: 10px;" src="@/assets/note.svg" alt="note">내 이력서</router-link></button>
      <div v-if="this.prefer.jobParentCategoryResponse.name">
        <div class="interest">
          <div  class="interest1">
            <p style="margin: 8px; font-size:14px; color: #2A2A2A; font-weight: 700">{{ this.prefer.jobParentCategoryResponse.name }}</p>
          </div>
          <div class="interest1">
            <p style="margin: 8px; font-size:14px; color: #2A2A2A; font-weight: 700">{{ this.prefer.gugunResponse.name }}</p>
          </div>
        </div>
        <div class="rechoice">
          <router-link :to="{ name: 'UpdateSetting' }" style="text-decoration:none; color: #6D6D6D; font-weight:800; font-size: 13px">관심 다시 설정하기  <i class="bi bi-reply-all-fill" style="color: #FF843E;"></i></router-link>
        </div>
      </div>
      <div v-else>
        <router-link :to="{ name: 'CreateSetting' }" style="text-decoration:none; color: #6D6D6D; font-weight:600; font-size: 14px; color: #FF843E;">관심 설정하기</router-link>
      </div>

    </div>

    <hr>

    <div style="width: 312px; margin: auto;" class="ing">
      <div style="display: flex; justify-content: flex-end;">
        <router-link :to="{ name: 'MyCalendarView' }" class ="calendar"><img style="width: 23px; height: 23px; align: absmiddle; filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));" src="@/assets/calendar.svg" alt="calendar">      나의 <span>일정</span> 확인!</router-link>
      </div>
      <br>
      <button @click="toApplied"><span style="color: #37BF99; font-size: 20px;">{{ applyNumber }}</span> <br><br> 지원 현황</button>
      <button @click="toBookmark"><span style="color: #ffb252; font-size: 20px;">{{ bookmarkNumber }}</span> <br><br> 관심 공고</button>
    </div>

    <br>
    <br>

  </div>
</template>
<script>
import { computed } from 'vue'
import { mapActions, mapGetters, useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: "MyPageView",
  components: { },
  data() {
  },
  computed: {
    ...mapGetters(['currentUser', 'prefer'])
  },
  setup() {
    const store = useStore()
    const router = useRouter()

    const fetchApply = () => store.dispatch('jobopening/fetchApply')
    const fetchBookmark = () => store.dispatch('jobopening/fetchBookmark')
    fetchApply()
    fetchBookmark()

    const applyNumber = computed(() => store.getters['jobopening/applies'].length)
    const bookmarkNumber = computed(() => store.getters['jobopening/bookmarks'].length)

    const toApplied = () => {
      router.push({ name: 'Jobopening' })
      return store.dispatch('jobopening/fetchApplied')
    }

    const toBookmark = () => {
      router.push({ name: 'Jobopening' })
      return store.dispatch('jobopening/fetchBookmarked')
    }

    return {
      toApplied, toBookmark, applyNumber, bookmarkNumber,
    }
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchCurrentUser', 'fetchPrefer']),
  },
  created() {
    this.fetchCurrentUser()
    this.fetchPrefer()
  },  
}
</script>

<style scoped>
  .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }
  .person {
    width: 120px;
    height: 160px;
    border-radius: 500px;
    float: left;
  }

  .myinfo {
    display: flex;
    flex-direction: column;
    padding-left: 30px;
  }

  button {
    border-radius: 10px;
    border-style: none;
    filter: drop-shadow(0px 1px 1px rgba(0, 0, 0, 0.25));
  }

  .myinfo > button {
    width: 96px;
    height: 32px;
    font-size: 14px;
    font-weight: 700;
    border-style: solid;
    border-color: #37BF99;
    background-color: white;
  }

  .name {
    text-align: left;
    margin-bottom: 5px;
    font-size: 20px;
    font-weight: 600;
  }

  .resume {
    width: 312px;
    height: 54px;
    margin-top: 40px;
    background-color: #ffb252;
    margin-bottom: 15px;
  }

  .interest {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 10px;
  }

  .interest1 {
    width: 150px;
    height: 40px;
    background-color: #f9f9f9;
    border-radius: 10px;
  }

  .rechoice {
    display: flex;
    justify-content: flex-end;
    filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));
  }

  .calendar{
    text-decoration:none; 
    color: black; 
    font-weight:800; 
    font-size: 15px;
    filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));
  }

  .calendar > span{
    color: #FF843E;
  }



  .ing > button {
    width: 150px;
    height: 150px;
    border-radius: 0px;
    font-size: 13px;
    font-weight: 700;
    background-color: #f9f9f9;
  }


</style>

 