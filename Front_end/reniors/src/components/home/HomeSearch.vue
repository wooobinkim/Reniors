<template>
  <div class="home-search">
    <form action="" @submit.prevent="search">
      <input type="text" name="keyword" class="home-search-text"
        placeholder="회사명, 직종, 지역 등 검색어를 입력해주세요" v-model="keyword">
      <svg @click="search" class="home-search-button" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M500.3 443.7l-119.7-119.7c27.22-40.41 40.65-90.9 33.46-144.7C401.8 87.79 326.8 13.32 235.2 1.723C99.01-15.51-15.51 99.01 1.724 235.2c11.6 91.64 86.08 166.7 177.6 178.9c53.8 7.189 104.3-6.236 144.7-33.46l119.7 119.7c15.62 15.62 40.95 15.62 56.57 0C515.9 484.7 515.9 459.3 500.3 443.7zM79.1 208c0-70.58 57.42-128 128-128s128 57.42 128 128c0 70.58-57.42 128-128 128S79.1 278.6 79.1 208z"/></svg>
    </form>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  name: 'HomeSearch',
  setup() {
    const store = useStore()

    let keyword = ''
    const search = function(){
      store.dispatch('home/search', this.keyword)
    }  

    return {
      keyword, search
    }
  },

}
</script>

<style>
.home-search {
  position: relative;
}

.home-search-text {
  box-sizing: border-box;
  background-color: var(--color-black-5);
  border: 1px solid var(--color-black-4);
  border-radius: 2rem;
  height: 40px;
  width: 100%;
  padding: 0 20px;
}

.home-search-button {
  position: absolute;
  right: 15px;
  top: 6px;
  height: 25px;
  width: 25px;
  filter: invert(66%) sepia(0%) saturate(0%) hue-rotate(123deg) brightness(90%) contrast(94%);
}

.home-search-button:hover {
  cursor: pointer;
}
</style>