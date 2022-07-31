<template>
  <div>
    <h1>회원가입</h1>
    <form @submit.prevent="signup(credentials)" class="signupform">
      <div v-show="page===1">
        <h2>step1</h2>
        <b-form-input class="mb-2" v-model="credentials.user_id" type="text" placeholder="사용하실 이메일을 입력해주세요." required></b-form-input>
        <b-form-input class="mb-2" v-model="credentials.password1" type="password" placeholder="비밀번호를 입력해주세요." required></b-form-input>
        <b-form-input class="mb-2" v-model="credentials.password2" type="password" placeholder="비밀번호를 한번 더 입력해주세요." required></b-form-input>
      </div>

      <div v-show="page===2">
        <h2>step2</h2>
        <b-form-input class="mb-2" v-model="credentials.name" type="text" placeholder="이름을 입력해주세요." required></b-form-input>
        <b-form-input class="mb-2" v-model="credentials.phone" type="text" placeholder="예시)01012345678" required></b-form-input>
        <b-form-input class="mb-2" v-model="credentials.address" type="text" placeholder="주소를 입력해주세요" required></b-form-input>
      </div>

      <div v-show="page===3">
        <h2>step3</h2>
        <b-form-select class="mb-2" v-model="credentials.lastEdu" :options="lastEdu" required></b-form-select>
        <b-form-input class="mb-2" v-model="credentials.birth" type="date" placeholder="생년-월-일" required></b-form-input>
        <b-form-select class="mb-2" v-model="credentials.gender" :options="gender" required></b-form-select>
      </div>

      
      <footer>
        <button type="button" v-show="page === 1"><router-link :to="{ name: 'Login' }">이전</router-link></button>
        <button type="button" v-show="page !== 1" @click="decreasePage">이전</button>
        <button type="button" v-show="page !== 3" @click="increasePage">다음</button>
        <button v-show="page === 3">완료</button>
      </footer>
    </form>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  name: 'SignupView',
  components: {},
  data() {
    return {
      credentials: {
        // 변수 이름 설정
        user_id: '',
        password1: '',
        password2: '',
        name: '',
        phone: '',
        address: '',
        lastEdu: '',
        birth: '',
        gender: '',
        // 이력서 공개 여부
      },
      lastEdu: [
        // value 수정
      { value: null, text: '최종학력을 선택해주세요.' },          
      { value: 'a', text: 'This is First option' },
      { value: 'b', text: 'Selected Option' },
      ],
      gender: [
      { value: null, text: '성별을 선택해주세요.' },          
      { value: 'Female', text: '여자' },
      { value: 'Male', text: '남자' },
      ],
      page: 1 
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['signup']),
    increasePage(){
      this.page += 1
    },
    decreasePage(){
      this.page -= 1
    }
  }
}
</script>

<style scoped>
  .signupform {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* min-height: 100vh; */
  }

  footer {
    background-color: blue;
    width: 100%;
    height: 50px;
    bottom: 0px;
    position: absolute;
  }
</style>