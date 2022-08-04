<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">이력서 작성</span>
      </div>
    
      <div style="float: right; margin-top: 5px">
        <img class="order" src="@/assets/one.svg" alt="order">
        <img class="order" src="@/assets/two.svg" alt="order">
        <img class="order" src="@/assets/three_active.svg" alt="order">
        <img class="order" src="@/assets/four.svg" alt="order">
        <img class="order" src="@/assets/five.svg" alt="order">          
      </div>
    </header>


    <h1>3.자격증</h1>
    <p>자격증을 입력해주세요.</p>
    <input type="text">자격증명
    <input type="text">
  </div>
</template>
<script>
export default {
  name: 'ResumeStepThree',
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
  header{
    height: 84px;
    border-style: none none solid none;
    border-width: 0.5px;
    border-color: #EAEAEA;
  }

  .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }

  .order{
    margin: 2px;
  }

</style>