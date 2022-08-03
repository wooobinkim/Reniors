<template>
  <div class="recommend-tag-list">
    <div class="recommend-tag-title">
      <h2>조건에 맞는 <span>추천 공고</span>입니다</h2>
      <button>재설정</button>
    </div>
    <RecommendTagItem v-for="(name, index) in names" :key="index" :name="name" />
  </div>
</template>

<script>
import RecommendTagItem from './RecommendTagItem.vue'

export default {
  name: 'RecommendTagList',
  components: {
    RecommendTagItem,
  },
  setup() {
    const names = [
      '#서울 송파구', '#연봉 3000', '#주5일', '#웹디자인', '#백엔드'
    ]

    return {
      names,
    }
  }
}
</script>

<style>
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
</style>