<template>
  <div class="container">
    <div class="loginform" >
      <img style="width: 80%;" alt="Vue logo" src="@/assets/logo.png">
      <br>
      <form style="width: 312px;" @submit.prevent="login(credentials)">
        <b-form-input style="width: 100%; height: 48px;" class="mb-2" v-model="credentials.user_id" type="text" placeholder="아이디" required></b-form-input>
        <b-form-input style="width: 100%; height: 48px;" class="mb-4" v-model="credentials.password" type="password" placeholder="비밀번호" required></b-form-input>
        <button id="LoginBtn" type="submit" v-bind:disabled="credentials.user_id && credentials.password== ''">로그인</button>
      </form>
      <br>
      <p class="line">또는</p>
      <router-link :to="{ name: 'FindUsername' }">아이디 찾기</router-link>
      <router-link :to="{ name: 'FindPassword' }">비밀번호 찾기</router-link>
      <router-link :to="{ name: 'Signup' }">회원가입</router-link>
    </div>

  </div>
</template>
<script>
  import { mapActions } from 'vuex'

  export default {
    name: 'LoginView',
    components: {},
    data() {
      return {
        credentials: {
          user_id: '',
          password: '',
        }
      }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {
      ...mapActions(['login'])
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
    font-size: 12px;
    margin: 8px 0px;
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
</style>