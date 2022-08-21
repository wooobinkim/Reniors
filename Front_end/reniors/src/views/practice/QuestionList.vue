<template>
  <real-header></real-header>
  <div class="total">
    <div class="head2">
      <router-link class="mx-3 rl" :to="{ name: 'QuestionList' }"
        >답변 작성</router-link
      >
      <router-link class="mx-3 rl" :to="{ name: 'VideoPracticeList' }"
        >화상 연습</router-link
      >
    </div>
    <div class="headsub">
      <p
        class="my-0"
        style="text-align: start; font-size: 16px"
      >
        질문에 대한 답변을 미리 작성해보세요!
      </p>
      <p style="text-align: start; font-size: 16px">
        화상면접 연습 시 참고할 수 있습니다.
      </p>
    </div>

    <question-item
      v-for="(question, idx) in questions"
      :key="idx"
      :question="question.question"
      :idx="idx"
      :id="question.id"
      :checklist="checklist"
    ></question-item>
    <div style="margin-bottom: 100px;"></div>
  </div>
</template>
<script>
import QuestionItem from "@/components/practice/QuestionItem.vue";
import { mapActions, mapGetters } from "vuex";
import RealHeader from "@/components/RealHeader.vue";
export default {
  name: "QuestionList",
  components: { QuestionItem, RealHeader },
  data() {
    return {
      sampleData: "",
    };
  },
  computed: {
    ...mapGetters(["questions", "checklist"]),
  },
  setup() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["fetchQuestions", "fetchChecklist", "clearAnswer"]),
  },
  created() {
    this.fetchQuestions();
    this.fetchChecklist();
    this.clearAnswer();
  },
};
</script>

<style scoped>
.total {
  background-color: #fff5f0;
  width: 100%;
}
.headsub {
  margin: 16px auto;
  width: 336px;
  padding-left: 8px;
}
.rl {
  text-decoration: none;
  color: white;
}
.rl:hover {
  font-weight: bold;
}

.head2 {
  background-color: #ff843e;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 48px;
}

.head2 a.router-link-exact-active {
  font-weight: bold;
}

.question {
  margin: 4px 20px;
  padding-left: 8px;
  height: 88px;
  width: 320px;
  border-radius: 10px;
  border: 1px solid #ff843e;
  box-shadow: 1px 1px 1px gray;
  display: flex;
  align-items: center;
}
</style>
