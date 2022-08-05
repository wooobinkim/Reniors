<template>
  <div>
    <div class="license">
      <p class="forminfo">자격증명</p>
      <b-form-input class="mb-3" type="text" placeholder="자격증명을 입력해주세요." ></b-form-input>
      <p class="forminfo">등급</p>
      <b-form-select class="mb-3" :options="grade" ></b-form-select>
      <p class="forminfo">취득일자</p>
      <b-form-input class="mb-3" type="date" placeholder="생년-월-일" ></b-form-input>
      <button>저장</button>
    </div>

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

<style scoped>
  input[type="date"]::-webkit-datetime-edit-text,
  input[type="date"]::-webkit-datetime-edit-month-field,
  input[type="date"]::-webkit-datetime-edit-day-field,
  input[type="date"]::-webkit-datetime-edit-year-field {
    color: #888;
  }

  .license{
    margin: 20px;
    height: 300px;
    border-style: none none dashed none;
    border-width: 0.5px;
    border-color: #EAEAEA;
  }
  
  button {
    float: right;
    width: 80px;
    height: 32px;
    font-weight: 700;
    color: white;

    background-color: #8cd6c1;
    /* border: 1px solid #37BF99; */
    border: none;
    border-radius: 10px;
    box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.05);
  }
</style>