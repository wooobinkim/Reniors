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
      <div class="interest">
        <div class="interest1">
          <p style="margin: 8px; font-size:14px; color: #2A2A2A;">관심 직무1</p>
        </div>
        <div class="interest1">
          <p style="margin: 8px; font-size:14px; color: #2A2A2A;">관심 직무2</p>
        </div>
      </div>
      <div class="rechoice">
        <router-link :to="{ name: 'PreferSetting' }" style="text-decoration:none; color: #6D6D6D; font-weight:800; font-size: 13px">관심 설정하기  <i class="bi bi-reply-all-fill" style="color: #FF843E;"></i></router-link>
      </div>
    </div>

    <hr>

    <div style="width: 312px; margin: auto;" class="ing">
      <div style="display: flex; justify-content: flex-end;">
        <router-link :to="{ name: 'FindPassword' }" class ="calendar"><img style="width: 23px; height: 23px; align: absmiddle; filter: drop-shadow(0px 0.5px 0.5px rgba(0, 0, 0, 0.25));" src="@/assets/calendar.svg" alt="calendar">      나의 <span>일정</span> 확인!</router-link>
      </div>
      <br>
      <button><span style="color: #37BF99; font-size: 20px;">{{ apply }}</span> <br><br> 지원 현황</button>
      <button><span style="color: #ffb252; font-size: 20px;">{{ scrap }}</span> <br><br> 관심 공고</button>
    </div>

    <br>
    <br>

  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: "MyPageView",
  components: {},
  data() {
    return {
      apply: 1,
      scrap: 2, 
    }
  },
  computed: {
    ...mapGetters(['currentUser'])
  },
  setup() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchCurrentUser'])
  },
  created() {
    this.fetchCurrentUser()
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

 