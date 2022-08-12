<template>
  <div>
    <openvidu-eval-list-item
      v-for="evalquestion in evalquestionlist"
      :key="evalquestion.id"
      :evalquestion="evalquestion"
    ></openvidu-eval-list-item>

    <button @click="finish()">평가 마치기</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import OpenviduEvalListItem from "./OpenviduEvalListItem.vue";
export default {
  components: {
    OpenviduEvalListItem,
  },
  created() {
    this.getEvalQuestionList(this.$route.params.no);
  },
  computed: {
    ...mapGetters("company", ["evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", ["getEvalQuestionList", "updateApply"]),
    finish() {
      this.updateApply({
        jobOpeningId: this.$route.params.no,
        applyId: this.interviewer,
        apply: {
          jobOpeningProcess: "면접심사중",
        },
      });
    },
  },
};
</script>

<style></style>
