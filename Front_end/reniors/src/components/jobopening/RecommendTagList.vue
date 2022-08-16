<template>
  <div class="recommend-tag-list">
    <template v-if="isrecommend">
      <div class="recommend-tag-title">
        <h2>조건에 맞는 <span>추천 공고</span>입니다</h2>
        <button @click="movesetting()">재설정</button>
      </div>
    </template>
    <template v-else>
      <div class="recommend-tag-title">
        <h2>조건에 맞는 <span>추천 공고</span>가 없습니다</h2>
      </div>
    </template>
    <!-- <RecommendTagItem v-for="(tag, index) in tags" :key="index" :tag="tag" /> -->
    <div class="jobopening-list">
      <div v-if="isrecommend">
        <RecommendJobOpeningItem
          v-for="jobopening in recommendJobopenings"
          :key="jobopening"
          :jobopening="jobopening"
        />
      </div>
    </div>
  </div>
</template>

<script scoped>
import { mapGetters } from "vuex";
import RecommendJobOpeningItem from "./RecommendJobOpeningItem.vue";

export default {
  name: "RecommendTagList",
  components: {
    RecommendJobOpeningItem,
  },
  setup() {
    // const store = useStore();

    // const tags = computed(() => store.getters["jobopening/tags"]);
    // const recommendJobopenings = computed(() => store.getters["recommendJobopenings"]);
    // console.log(recommendJobopenings);
    return {
      // tags,
    };
  },
  watch: {
    recommendJobopenings: function (data) {
      if (data) {
        let len = 0;
        if (data.length % 2 == 1) len = data.length / 2 + 0.5;
        else len = data.length / 2;
        document.documentElement.style.setProperty("--columns", len);
      }
    },
  },
  computed: {
    ...mapGetters("jobopening", ["recommendJobopenings", "isrecommend"]),
  },
  methods: {
    movesetting() {
      this.$router.push({ name: "UpdateSetting" });
    },
  },
  created() {
    if (!this.recommendJobopenings)
      document.documentElement.style.setProperty("--columns", 0);
    else {
      let len = 0;
      if (this.recommendJobopenings.length % 2 == 1)
        len = this.recommendJobopenings.length / 2 + 0.5;
      else len = this.recommendJobopenings.length / 2;
      document.documentElement.style.setProperty("--columns", len);
    }
    // this.isrecommend = null;
    // this.fetchRecommend();
  },
};
</script>

<style scoped>
:root {
  --columns: 4;
}

.recommend-tag-title {
  display: flex;
  justify-content: space-between;
  margin: 15px 15px 10px 15px;
  height: 30px;
}

.recommend-tag-title > h2 {
  margin: 0;
  font-size: 18px;
  font-weight: bold;
}

.recommend-tag-title > h2 > span {
  color: var(--color-red-1);
  font-size: 20px;
}

.recommend-tag-title > button {
  box-sizing: border-box;
  box-shadow: 1px 1px var(--color-black-2);
  background-color: var(--color-red-1);
  border: none;
  border-radius: 0.4rem;
  padding: 0px 15px;
  color: white;
}

.jobopening-list > div {
  overflow: scroll;
  display: grid;
  /* gap: 1px; */
  grid-template-rows: 150px 150px;
  grid-template-columns: repeat(var(--columns), 170px);
  margin-top: 10px;
  justify-items: center;
}
@media screen and (min-width: 720px) {
  .jobopening-list > div {
    grid-template-rows: 150px 150px;
    grid-template-columns: repeat(var(--columns), 170px);
    margin-top: 10px;
    justify-items: center;
  }
}
</style>
