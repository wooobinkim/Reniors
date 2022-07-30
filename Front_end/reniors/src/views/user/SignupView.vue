<template>
  <div>
    <h1>회원가입</h1>
    <div>
      <h2>step1</h2>
      <b-form-input class="mb-2" style="width: 312px;" v-model="credentials.user_id" sizs="sm" type="text" placeholder="사용하실 이메일을 입력해주세요." required></b-form-input>
      <b-form-input class="mb-2" style="width: 312px;" v-model="credentials.username" sizs="sm" type="password" placeholder="비밀번호를 입력해주세요." required></b-form-input>
    </div>
    <div>
      <h2>step2</h2>
      <b-form-input class="mb-2" style="width: 312px;" v-model="credentials.name" sizs="sm" type="text" placeholder="이름을 입력해주세요." required></b-form-input>
    </div>

  </div>
</template>
<script>
export default {
  name: 'SignupView',
  components: {},
  data() {
    return {
      credentials: {
        user_id: '',
        password: '',
        name: '',
        phone: '',
        address: '',
        lastEdu: '',
        birth: '',
        gender: '',
        // 이력서 공개 여부
      },
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>