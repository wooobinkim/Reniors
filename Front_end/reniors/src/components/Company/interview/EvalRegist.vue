<template>
  <div>
    <div>평가항목<input type="text" v-model="evalquestion.contents" /></div>
    <button @click="regist()">저장</button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      evalquestion: {
        jobOpeningId: this.jobopening,
        contents: "",
      },
    };
  },
  computed: {
    ...mapState("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["registEvalQuestion"]),
    regist() {
      this.registEvalQuestion(this.evalquestion);
    },
  },
};
</script>

<style></style>
