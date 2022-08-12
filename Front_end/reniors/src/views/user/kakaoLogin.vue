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
    <input type="text" v-model="form.isOpen">
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
        userAppId: '',
        userAppPwd: '',
        name: '',
        phone: '',
        address: '',
        extraAddress: '',
        lastEdu: '',
        birth: '',
        gender: '',
        isOpen: 'CLOSE',
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
      // this.getToken()
    },
    kakaologin(){
      axios
        .post()
    },
    getToken(){
      axios
        .get("https://i7b307.p.ssafy.io/api/users/login/kakao?code=" + this.codes)
        .then((res) => {
          this.form.userAppId = res.data.email
          this.form.userAppPwd = res.data.id
          if (this.form.userAppPwd == undefined){
            alert('틀림')
            this.$router.push('/')
          } else {
            this.kakaologin()
          }
        })
    }
  }
}
</script>