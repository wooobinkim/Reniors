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
    ...mapActions("company", ["getEvalQuestionList", "finishInterview"]),
    finish() {
      console.log(this.interviewer);
      this.finishInterview({
        // jobOpeningId: this.$route.params.no,
        applyId: this.applyinfo.userId,
      });
    },
  },
};
</script>

<style></style>
