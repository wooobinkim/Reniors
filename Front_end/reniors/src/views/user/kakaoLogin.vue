<template>
  <div>
    <h1>카카오 로그인</h1>
    {{ codes }}
    <input type="text" v-model="form.name">
    <input type="text" v-model="form.phone">
    <input type="text" v-model="form.address">
    <input type="text" v-model="form.extraAddress">
    <input type="text" v-model="form.lastEdu">
    <input type="text" v-model="form.birth">
    <input type="text" v-model="form.gender">
    <input type="text" v-model="form.kakaoId">
  </div>
</template>
<script>
import axios from 'axios'

export default {
  components: {},
  data() {
    return {
      codes: '',
      form: {
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
      this.getToken()
    },
    getToken(){
      axios
        .get("https://i7b307.p.ssafy.io/api/users/kakao/callback?code=" + this.codes)
        .then((res) => {
          console.log(res)
          console.log(1)
          this.form.kakaoId = res.data.kakaoUserInfo.email
          this.form.gender = res.data.kakaoUserInfo.gender
          this.form.userProfile = res.data.kakaoUserInfo.profileImage
        })
        .catch((err) => {
          console.log(2)
          console.log(err)
        })
    }
  }
}
</script>