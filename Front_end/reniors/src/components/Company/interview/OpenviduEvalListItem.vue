<template>
  <div>
    <div>{{ evalquestion.contents }}</div>
    <div>점수<input type="text" v-model="usereval.score" /></div>
    <div>메모<input type="text" v-model="usereval.memo" /></div>
    <button @click="regist()">저장</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: {
    evalquestion: Object,
  },
  data() {
    return {
      usereval: {
        score: null,
        memo: null,
        evalQuestionId: this.evalquestion.id,
      },
    };
  },
  computed: {
    ...mapGetters("company", ["interviewer"]),
  },
  methods: {
    ...mapActions("company", ["registUserEval"]),
    regist() {
      let data = {
        jobOpeningId: this.$route.params.no,
        userId: this.interviewer,
        usereval: this.usereval,
      };
      console.log(data);
      this.registUserEval(data);
    },
  },
};
</script>

<style></style>
