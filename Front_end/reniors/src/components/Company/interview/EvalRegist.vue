<template>
  <div>
    <div>평가항목<input type="text" v-model="evalquestion.contents" /></div>
    <button @click="regist()">저장</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      evalquestion: {
        jobOpeningId: this.$route.params.no,
        contents: "",
      },
    };
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["registEvalQuestion"]),
    regist() {
      console.log(this.evalquestion);
      this.registEvalQuestion(this.evalquestion);
      this.$router.go();
    },
  },
};
</script>

<style></style>
