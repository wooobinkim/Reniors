<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">이력서 작성</span>
      </div>
    
      <div style="float: right; margin-top: 5px">
        <img class="order" src="@/assets/one_active.svg" alt="order">
        <img class="order" src="@/assets/two.svg" alt="order">
        <img class="order" src="@/assets/three.svg" alt="order">
        <img class="order" src="@/assets/four.svg" alt="order">
        <img class="order" src="@/assets/five.svg" alt="order">          
      </div>
    </header>

    <div class="content">
      <img class="one" src="@/assets/bigone.svg" alt="one">
      <p class="text1">기본정보 입력</p>
      <p class="text2">기본정보에서 수정할 사항이 있을 시 수정해주세요.</p>
      <div style="width:312px; margin:0 auto; ">
        <div class="basic">
          <img style="float: right;" id="previewImage" src="@/assets/defaultimage.svg">
          <p class="forminfo">이름</p>
          <b-form-input style="width:56%" class="mb-3" type="text" placeholder="홍길동" ></b-form-input>
          <p class="forminfo">생년월일</p>
          <b-form-input style="width:56%" class="mb-3" type="date" placeholder="생년-월-일" ></b-form-input>
          <p class="forminfo">주소</p>
          <b-form-input class="mb-3" type="text" placeholder="성남시" ></b-form-input>
          <p class="forminfo">연락처</p>
          <b-form-input class="mb-3" type="text" placeholder="01000000000" ></b-form-input>
          <p class="forminfo">이메일</p>
          <b-form-input class="mb-3" type="text" placeholder="apple@ssafy.com" ></b-form-input>

          {{ this.currentUser }}
        </div>
      </div>
    </div>

    <footer style="width: 312px; "> 
      <button><router-link style="text-decoration:none; color: white;" :to="{ name: 'ResumeStepTwo' }">다음</router-link></button>
    </footer>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  components: {},
  data() {
    return {
      editUser: {
        // 변수 이름 설정
        // userAppId: this.currentUser.userAppId,
        // userAppPwd: this.currentUser.userAppPwd,
        // name: this.currentUser.name,
        // phone: this.currentUser.phone,
        // address: this.currentUser.address,
        // // extraAddress: '',
        // lastEdu: this.currentUser.lastEdu,
        // birth: this.currentUser.birth,
        // gender: this.currentUser.gender,
        // isOpen: this.currentUser.isOpen,
        // kakaoId: this.currentUser.kakaoId,
        // portfolioName: this.currentUser.portfolioName,
        // portfolioPath: this.currentUser.portfolioPath,
        // profileImgName: this.currentUser.profileImgName,
        // profileImgPath: this.currentUser.profileImgPath,
        // totalCareer: this.currentUser.totalCareer,      
        // 이력서 공개 여부        
      }
    }
  },
  computed: {
    ...mapGetters(['currentUser'])
  },
  setup() {},
  created() {
    this.fetchCurrentUser()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchCurrentUser'])
  }
}
</script>

<style scoped>
  header{
    height: 84px;
    border-style: none none solid none;
    border-width: 0.5px;
    border-color: #EAEAEA;
  }

  .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }
/* .nav {
  height: 64px;
  background-color: white;
  box-shadow: 0px 2px 0px rgba(0, 0, 0, 0.25);
  margin-bottom: 10px;
} */

  .order{
    margin: 2px;

  }

  .one{
    float: left;
    margin: 16px;
  }

  .text1{
    text-align: left;
    font-size: 18px;
    padding: 20px 4px 6px 4px;
    font-weight: 900;
    margin: 0px;
  }

  .text2{
    text-align: left;
    font-size: 15px;
    padding-right: 50px;
    color: #6D6D6D;
  }

  .forminfo {
    color: #8A8A8A;
    font-weight: 400;
    font-size: 13px;
    margin-bottom: 5px;
  }

  .basic{
    margin: 0px;
    height: 600px;
  }

  footer {
    width: 100%;
    height: 50px;
    position: fixed;
    left: 50%;
    transform: translate(-50%, 0);
    bottom: 30px;
  }

  footer > button {  
    margin:0 auto;  
    background-color: var(--color-red-2);
    width: 100%;
    height: 80%;
    /* height: 40px; */
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 18px;
    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }

  input[type="date"]::-webkit-datetime-edit-text,
  input[type="date"]::-webkit-datetime-edit-month-field,
  input[type="date"]::-webkit-datetime-edit-day-field,
  input[type="date"]::-webkit-datetime-edit-year-field {
    color: #888;
  }
</style>
