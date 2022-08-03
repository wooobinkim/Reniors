<template>
  <div>
    <h1>1.기본정보 입력</h1>
    <p>기본정보에서 수정할 사항이 있을 시 수정해주세요.</p>
    <input type="text">이름
    <input type="date">생년월일
    <input type="file">사진
    <input type="text">주소
    <input type="text">연락처
    <input type="text">이메일


    <footer>
      
      <button><router-link :to="{ name: 'ResumeStepTwo' }">다음</router-link></button>
    </footer>
  </div>
</template>
<script>
export default {
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