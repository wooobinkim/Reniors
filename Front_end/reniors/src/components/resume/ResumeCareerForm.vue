<template>
  <div>
    <form @submit.prevent="createCareer(career)">
      <div class="career">
        <p class="forminfo">기업명</p>
        <b-form-input class="mb-3" type="text" placeholder="기업명" v-model="career.companyName"></b-form-input>
        <p class="forminfo">퇴사날짜</p>
        <b-form-input class="mb-3" type="date" placeholder="생년-월-일" v-model="career.finishedAt"></b-form-input>
        <p class="forminfo">퇴사날짜</p>
        <b-form-input class="mb-3" type="date" placeholder="생년-월-일" v-model="career.startedAt"></b-form-input>
        <p class="forminfo">상세내용</p>
        <b-form-textarea
          id="textarea-default"
          class="mb-3"
          placeholder="맡으신 직무와 업무에 대해 입력해주세요"
          rows="8"
          v-model="career.jobContents"
        ></b-form-textarea>
        <button>저장</button>
      </div>
    </form>

  </div>
</template>
<script>
import { mapActions } from 'vuex'

export default {
  components: {},
  props: {
    cnt: Number,
  },
  data() {
    return {
      career: {
        companyName: '',
        finishedAt: '',
        jobContents: '',
        startedAt: '',

      }
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['createCareer'])
  }
}
</script>

<style scoped>
  input[type="date"]::-webkit-datetime-edit-text,
  input[type="date"]::-webkit-datetime-edit-month-field,
  input[type="date"]::-webkit-datetime-edit-day-field,
  input[type="date"]::-webkit-datetime-edit-year-field {
    color: #888;
  }

  .career{
    margin: 20px;
    height: 550px;
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
  form {
    margin-top: 20px;
  }
</style>