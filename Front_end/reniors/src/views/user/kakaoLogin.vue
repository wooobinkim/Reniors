<template>
  <div>
    <h1>카카오 로그인</h1>
    <form @submit.prevent="submit(forms)">
      {{ codes }}
      <input type="text" v-model="forms.name">
      <input type="text" v-model="forms.phone">
      <input type="text" v-model="forms.address">
      <input type="text" v-model="forms.extraAddress">
      <input type="text" v-model="forms.lastEdu">
      <input type="date" v-model="forms.birth">
      <input type="text" v-model="forms.gender">
      <input type="text" v-model="forms.kakaoId">
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
        methods: 'post',
        data: JSON.stringify(this.forms)
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