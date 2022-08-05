<template>
  <div>
    <div class="award">
      <p class="forminfo">수상명</p>
      <b-form-input class="mb-3" type="text" placeholder="수상명" ></b-form-input>
      <p class="forminfo">수상날짜</p>
      <b-form-input class="mb-3" type="date" placeholder="생년-월-일" ></b-form-input>
      <button>저장</button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'ResumeAwardForm',
  components: {},
  props: {
    cnt: Number,
  },
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

  .award{
    margin: 20px;
    height: 225px;
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