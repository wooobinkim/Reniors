<template>
  <div>
    <openvidu-eval-list-item
      v-for="evalquestion in evalquestionlist"
      :key="evalquestion.id"
      :evalquestion="evalquestion"
      :applyinfo="applyinfo"
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
  props:{
    applyinfo : Object,
  },
  created() {
    console.log("오픈이발");
    console.log(this.applyinfo);
    this.getEvalQuestionList(this.applyinfo.jobOpeningId);
  },
  computed: {
    ...mapGetters("company", ["evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", ["getEvalQuestionList", "finishInterview","updateApply"]),
    finish() {
      let apply = {
        jobOpeningProcess: "면접심사중",
        interviewDate:null,
        sessionId: null,
      }
      this.updateApply({
        jobOpeningId : this.applyinfo.jobOpeningId,
        applyId : this.applyinfo.id,
        apply: apply,
      });
    },
  },
};
</script>

<style></style>
