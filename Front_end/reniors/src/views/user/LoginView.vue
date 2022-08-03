<template>
  <div class="container">
    <div class="loginform" >
      <img style="width: 80%;" alt="logo" src="@/assets/logo.png">
      <br>
      <form style="width: 312px;" @submit.prevent="login(credentials)">
      <!-- <form style="width: 312px;"> -->
        <b-form-input style="width: 100%; height: 48px;" class="mb-2" v-model="credentials.userAppId" type="text" placeholder="아이디" required></b-form-input>
        <b-form-input style="width: 100%; height: 48px;" class="mb-4" v-model="credentials.userAppPwd" type="password" placeholder="비밀번호" required></b-form-input>
        <button id="LoginBtn" v-bind:disabled="credentials.user_id && credentials.password== ''">로그인</button>
      </form>
      <br>
      <p class="line">또는</p>

      <!-- 카카오 로그인하기 구현 -->
      <div style="margin-top: 10px; margin-bottom: 25px;">
        <img src="@/assets/kakaologin.png" style="width: 80%;" alt="kakao">
      </div>
      <b></b>

      <div style="margin: 10px;">
        <router-link class="link" :to="{ name: 'FindUsername' }">아이디 찾기</router-link>
        <router-link class="link" :to="{ name: 'FindPassword' }">비밀번호 찾기 </router-link>
        <router-link class="link" :to="{ name: 'Signup' }">회원가입</router-link>
      </div>

    </div>

  </div>
</template>
<script>
  // import { mapState, mapActions } from 'vuex'
  import { mapActions } from 'vuex'

  export default {
    name: 'LoginView',
    components: {},
    data() {
      return {
        credentials: {
          userAppId: '',
          userAppPwd: '',
        }
      }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    // // 추가
    // computed: {
    //   ...mapState(['isLogin'])
    // },
    methods: {
      ...mapActions(['login']),
    //   ...mapActions(['userConfirm', 'getUserInfo']),
    //   // 코드추가
    //   async confirm(){
    //     await this.userConfirm(this.credentials)
    //     let token = sessionStorage.getItem("access-token")
    //     console.log(token)
    //     if (this.isLogin){
    //       await this.getUserInfo(token)
    //       this.$router.push({ name: 'home' })
    //     }
    //   }
    }
  }
</script>

<style scoped>
  .loginform {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 80vh;
  }

  #LoginBtn {
    background-color: var(--color-red-2);
    width: 100%;
    height: 40px;
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 18px;
    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }

  #LoginBtn:disabled{
    background-color: #FFC0A3;
    width: 100%;
    height: 40px;
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 18px;
    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }

  .line {
    display: flex;
    flex-basis: 100%;
    align-items: center;
    color: rgba(0, 0, 0, 0.35);
    font-weight: 400;
    font-size: 13px;
    margin: 8px 0px;    
    color: #8A8A8A;
  }
  .line::before,
  .line::after {
    content: "";
    flex-grow: 1;
    background: rgba(0, 0, 0, 0.35);
    height: 1px;
    font-size: 0px;
    line-height: 0px;
    margin: 0px 16px;
  }

  .link {
    display: inline;
    margin: 8px;
    /* font-family: 'Inter'; */
    font-style: normal;
    /* font-weight: 600; */
    font-size: 14px;
    line-height: 17px;
    color: #6D6D6D;
    text-decoration:none;
    
  }
</style>

