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
        연습하고 싶은 질문을 선택하고
      </p>
      <p style="text-align: start; font-size: 16px">
        화상면접을 경험해보세요!
      </p>
    </div>

    <video-question
      v-for="(question, idx) in questions"
      :key="idx"
      :question="question.question"
      :idx="idx"
      :id="question.id"
    ></video-question>

    <router-link :to="{ name: 'PracticePage' }">
      <div class="submit">
        <button class="Btn">연습하기</button>
      </div>
    </router-link>
  </div>
</template>
<script>
import VideoQuestion from "@/components/practice/VideoQuestion.vue";
import { mapActions, mapGetters } from "vuex";
import RealHeader from "@/components/RealHeader.vue";
export default {
  name: "VideoPracticeList",
  components: { VideoQuestion, RealHeader },
  data() {
    return {
      sampleData: "",
    };
  },
  setup() {},
  async created() {
    this.fetchQuestions();
    await this.clearSelected();
  },
  computed: {
    ...mapGetters(["questions"]),
  },
  methods: {
    ...mapActions(["fetchQuestions", "clearSelected"]),
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
p {
  margin: 0px;
}
.submit {
  position: fixed;
  left: 0;
  display: flex;
  justify-content: center;
  bottom: 50px;
  width: 100%;
  margin: auto;
  background-color: white;
  border-top: solid 0.5px #ffedbf;
}
.Btn {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--color-red-2);
  height: 40px;
  width: 340px;
  margin: 8px auto;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
}
</style>
