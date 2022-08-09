<template>
  <div class="search-bar">
    <HeaderComponent />
    <form class="home-search" action="" @submit.prevent="search">
      <input type="text" name="keyword" class="home-search-text"
        placeholder="회사명, 직종, 지역 등 검색어를 입력해주세요" v-model="keyword">
      <font-awesome-icon @click="search" class="home-search-button" icon="fa-solid fa-magnifying-glass" />
    </form>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import HeaderComponent from './HeaderComponent.vue'

export default {
  name: 'SearchBar',
  components: {
    HeaderComponent,
  },
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
.search-bar {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.home-header {
  color: var(--color-red-1);
  font-weight: bold;
  margin: 20px;
}

.home-search {
  position: relative;
  max-width: 720px;
  margin-bottom: 15px;
}

.home-search-text {
  box-sizing: border-box;
  background-color: var(--color-black-5);
  border: 1px solid var(--color-black-4);
  border-radius: 2rem;
  height: 40px;
  width: 100%;
  padding: 0 20px;
  font-size: 14px;
}

@media screen and (min-width: 720px) {
  .home-search {
    align-self: center;
  }
  .home-search-text {
    width: 600px;
  }
}

.home-search-button {
  position: absolute;
  right: 15px;
  top: 6px;
  height: 25px;
  width: 25px;
  color: var(--color-black-2);
}

.home-search-button:hover {
  cursor: pointer;
}
</style>