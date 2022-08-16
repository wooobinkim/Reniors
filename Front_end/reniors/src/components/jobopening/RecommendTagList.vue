<template>
    <div class="recommend-tag-list">
      <div class="recommend-tag-title">
        <h2>조건에 맞는 <span>추천 공고</span>입니다</h2>
        <button>재설정</button>
      </div>
      <!-- <RecommendTagItem v-for="(tag, index) in tags" :key="index" :tag="tag" /> -->
      <div class = 'jobopening-list'>
        <div>
          <RecommendJobOpeningItem 
          v-for="jobopening in recommendJobopenings" 
          :key="jobopening"
          :jobopening = 'jobopening'
          />
        </div>
      </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
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
  watch:{
    recommendJobopenings:function (data) {
      let len = 0;
      if(data.length%2 == 1) len = (data.length/2)+0.5;
      else len = (data.length/2);
      document.documentElement.style.setProperty("--columns",len);
    }
  },
  computed:{
    ...mapGetters(["recommendJobopenings"]),
  },
  methods:{
    ...mapActions(["fetchRecommend"]),
  },
  created(){
    this.fetchRecommend();
  },
};
</script>

<style scoped>
:root{
  --columns:4;
}

.recommend-tag-title {
  display: flex;
  justify-content: space-between;
  margin: 15px 15px 10px 15px;
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
  grid-template-columns: repeat(var(--columns),170px);
  margin-top: 10px;
  justify-items: center;
}
@media screen and (min-width: 720px) {
  .jobopening-list > div {
    grid-template-rows: 150px 150px;
  grid-template-columns: repeat(var(--columns),170px);
  margin-top: 10px;
  justify-items: center;
  }
}
</style>
