<template>
  <div>
    <eval-list-item
      v-for="evalquestion in evalquestionlist"
      :key="evalquestion.id"
      :evalquestion="evalquestion"
    ></eval-list-item>
    <div v-if="registflag"><eval-regist></eval-regist></div>
    <button @click="changeflag()">평가항목 추가</button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import EvalRegist from "./EvalRegist.vue";
import EvalListItem from "./EvalListItem.vue";
export default {
  components: {
    EvalRegist,
    EvalListItem,
  },
  data() {
    return {
      registflag: false,
    };
  },
  created() {
    this.getEvalQuestionList(this.$route.params.no);
  },
  computed: {
    ...mapState("company", ["jobopening", "evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", ["getEvalQuestionList"]),
    changeflag() {
      this.registflag = !this.registflag;
    },
  },
};
</script>

<style></style>
