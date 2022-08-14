<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">내 정보 수정</span>
      </div>
    </header>
    <br>

    <div style="width: 312px; margin: auto;">
      <p class="forminfo">이메일</p>
      <b-form-input class="mb-3" type="email" v-model="currentUser.userAppId" disabled></b-form-input>
      <p class="forminfo">비밀번호</p>
      <form @submit.prevent="changeTest(password)">
        <div style="display: flex; justify-content: space-between;">
          <b-form-input class="mb-3 user-form-control" style="width:80%;" type="password" v-model="password" placeholder="새 비밀번호를 입력해주세요."></b-form-input>
          <button style="">변경</button>
        </div>

      </form>
      <b-form-input class="mb-3" type="password" v-model="password2" placeholder="새 비밀번호를 한번 더 입력해주세요."></b-form-input>

      <resume-basic-form :currentUser="currentUser" action="info"></resume-basic-form>
    </div>


  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import ResumeBasicForm from '@/components/resume/ResumeBasicForm.vue'

export default {
  name: "MyinfoEdit",
  components: { ResumeBasicForm },
  data() {
    return {
      example: '',
      password: '',
      password2: ''
    }
  },
  computed: {
    ...mapGetters(['currentUser', 'isLogginedIn'])
  },
  setup() {},
  created() {
    this.fetchCurrentUser()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchCurrentUser', 'changePassword']),
    changeTest(data){
      if (this.password != this.password2){
        alert('변경하실 비밀번호를 다시 확인해주세요.')
      } else {
        this.changePassword(data)
      }
    }
  }
}
</script>

<style scoped>
  .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }

  p {
    text-align: left;
  }

  .forminfo {
  color: #8A8A8A;
  font-weight: 400;
  font-size: 13px;
  margin-bottom: 5px;
  }

  button {
    background-color: #8CD6C1;
    height: 38px;
    border-radius: 10px;
    /* border-color: var(--color-red-2); */
    color: white;
    font-weight: 600;
    border-style: none;
    box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;

  }

  .user-form-control:focus{
  border-color: var(--color-red-2) !important; 
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}
</style>