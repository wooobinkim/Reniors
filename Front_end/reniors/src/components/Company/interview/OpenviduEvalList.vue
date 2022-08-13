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
    interviewer : Number,
  },
  created() {
    this.getEvalQuestionList(this.$route.params.no);
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
        applyId: this.$route.params.no,
      });
    },
  },
};
</script>

<style></style>
