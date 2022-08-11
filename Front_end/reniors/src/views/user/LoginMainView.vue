<template>
  <div class="list">
    <div class="loginbox">
      <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
      <br />
      <div class="loginmenu">
        <div id="userlogin">
          <router-link :to="{ name:'LoginUser' }">
            <button class="userbtn">
              개인 로그인
            </button>
          </router-link>
        </div>
        <div id="companylogin">
          <router-link :to="{ name:'LoginCompany' }">
            <button class="companybtn">
              기업 로그인
            </button>
          </router-link>
        </div>
      </div>
      <br />
      <p class="proposal">혹시 계정이 없으신가요?</p>
      <router-link class="link" style="color: #FF843E;" :to="{ name: 'Signup' }">
        개인 회원가입
      </router-link>
      <router-link class="link" style="color: #37BF99;" :to="{ name: 'companyregist' }">
        기업 회원가입
      </router-link>
    </div>
  </div>
</template>
<script>
export default {
  name:'LoginMainView',
  components: {},
  data() {
    return {
      example: '',
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>

<style scoped>
.list {
  display: flex;
  flex-direction: column;

  justify-content: center;
  min-height: 100vh;
}

.loginbox {
  width: 100%;
  justify-content: center;
  align-items: center;
  margin: 8px 0px;  
}

.loginmenu {
  display: flex;
  justify-content: center;
  align-items: center;
}

.loginmenu > div {
  margin: 0px 5px;
}

.userbtn {
  width: auto;
  height: 200px;
  border: solid;
  /* border-color: #FF843E;
  background-color: #FFF5F0; */
  background-color: #FF843E;
  border-radius: 10px;
  color: white;
}

.companybtn {
  width: auto;
  height: 200px;
  border: solid;
  /* border-color: #37BF99;
  background-color: #ECFFFA; */
  background-color: #37BF99;
  border-radius: 10px;
  color: white;
}

.proposal {
  color: #6d6d6d;
}

.link {
  display: inline;
  margin: 8px;
  /* font-family: 'Inter'; */
  font-style: normal;
  /* font-weight: 600; */
  font-size: 14px;
  line-height: 17px;
  color: #6d6d6d;
  text-decoration: none;
}
</style>