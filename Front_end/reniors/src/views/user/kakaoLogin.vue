<template>
  <div>
    <h1>카카오 로그인</h1>
    <form @submit.prevent="submit(forms)">
      {{ codes }}
      <input type="text" v-model="form.name">
      <input type="text" v-model="form.phone">
      <input type="text" v-model="form.address">
      <input type="text" v-model="form.extraAddress">
      <input type="text" v-model="form.lastEdu">
      <input type="date" v-model="form.birth">
      <input type="text" v-model="form.gender">
      <input type="text" v-model="form.kakaoId">
      <button>제출</button>
    </form>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'kakaoLogin',
  components: {},
  data() {
    return {
      codes: '',
      forms: {
        name: '',
        phone: '',
        address: '',
        extraAddress: '',
        lastEdu: '',
        birth: '',
        gender: '',
        isOpen: 'CLOSE',
        kakaoId: '',
        totalCareer: 0,
        userProfile: ''
      }
    }
  },
  setup() {},
  created() {
    this.create()
  },
  mounted() {},
  unmounted() {},
  methods: {
    create(){
      this.codes = this.$route.query.code
      this.getInfo()
    },
    getInfo(){
      axios
        .get("https://i7b307.p.ssafy.io/api/users/kakao/callback?code=" + this.codes)
        .then((res) => {
          console.log(res)
          this.forms.kakaoId = res.data.kakaoUserInfo.email
          this.forms.gender = res.data.kakaoUserInfo.gender
          this.forms.userProfile = res.data.kakaoUserInfo.profileImage
        })
        .catch((err) => {
          console.log(err)
        })
    },
    submit(){
      axios({
        url: "https://i7b307.p.ssafy.io/api/users/kakao/login",
        methods: 'get',
        data: this.forms
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    }
    
    
  }
}
</script>