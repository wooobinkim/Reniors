<template>
  <div>
    <eval-list-item
      v-for="(evalquestion, idx) in this.list"
      :key="evalquestion.id"
      :idx="idx"
      :evalquestion="evalquestion"
    ></eval-list-item>
    <div v-if="registflag">
      <eval-regist />
    </div>
    <div class="add-flag">
      <p @click="changeflag()">
        <i class="bi bi-plus-circle"></i> 평가 항목 추가
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
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
      list: null,
    };
  },
  watch: {
    evalquestionlist: function (data) {
      console.log(data);
      this.list = data;
    },
  },
  created() {
    this.getEvalQuestionList(this.$route.params.no);
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
    ...mapState("company", ["evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", ["getEvalQuestionList"]),
    changeflag() {
      this.registflag = !this.registflag;
    },
  },
};
</script>

<style scope>
.add-flag {
  display: flex;
  justify-content: center;
  font-size: 18px;
  font-weight: bold;
  color: var(--color-green-1);
}
</style>
