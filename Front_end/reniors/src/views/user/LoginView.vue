<template>
  <div class="container">
    <div class="loginform" >
      <h1>Login</h1>
      <form @submit.prevent="login(credentials)">
        <b-form-input class="mb-2" v-model="credentials.user_id" type="text" placeholder="아이디" required></b-form-input>
        <b-form-input class="mb-4" v-model="credentials.password" type="password" placeholder="비밀번호" required></b-form-input>
        <button id="LoginBtn" type="submit">로그인</button>
      </form>

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
    min-height: 100vh;
  }

  #LoginBtn {
    
  }
</style>