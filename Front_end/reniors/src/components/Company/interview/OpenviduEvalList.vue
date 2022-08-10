<template>
  <div>
    <openvidu-eval-list-item
      v-for="evalquestion in evalquestionlist"
      :key="evalquestion.id"
      :evalquestion="evalquestion"
    ></openvidu-eval-list-item>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import OpenviduEvalListItem from "./OpenviduEvalListItem.vue";
export default {
  components: {
    OpenviduEvalListItem,
  },
  created() {
    this.getEvalQuestionList(this.$route.params.no);
  },
  computed: {
    ...mapState("company", ["evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", ["getEvalQuestionList"]),
  },
};
</script>

<style></style>
