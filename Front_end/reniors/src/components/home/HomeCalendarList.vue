<template>
  <div>
    <h2 class="home-jobopening-type">내가 북마크한 공고</h2>
    <ul class="home-calendar-list">
      <router-link class="home-calendar-item" :to="{ name: 'JobopeningDetail', params: { jobopeningId: bookmark.jobOpeningResponse.id } }" v-for="bookmark, index in bookmarks" :key="index" :id="'homeCalendarItem'+index">
        <img class="company-image mb-3" :src="bookmark.jobOpeningResponse.baseURL + bookmark.jobOpeningResponse.companyProfile">
        <h4 class="mb-3">[회사명] {{ bookmark.jobOpeningResponse.companyName }}</h4>
        <div class="home-jobopening-info">
          <p class="mb-1">[공고명] {{ bookmark.jobOpeningResponse.title }}</p>
          <p>[마감일] {{ bookmark.jobOpeningResponse.finishedDate.split('T')[0] }}</p>
        </div>

      </router-link>
    </ul>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'HomeCalendarList',
  setup() {
    const store = useStore()

    const fetchBookmark = () => store.dispatch('jobopening/fetchBookmark')
    fetchBookmark()

    const bookmarks = computed(() => store.getters['jobopening/bookmarks'])
    console.log(bookmarks)

    return {
      bookmarks,
    }
  },
}
</script>

<style scoped>
.home-jobopening-type {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
  font-size: 20px;
}
.company-image{
  width: 100%;
  border: 3px solid var(--color-red-4);
  border-radius: 5px;
}
.home-calendar-list {
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
  text-align: start;
}
.home-calendar-list::-webkit-scrollbar{
  height: 10px;
}
.home-calendar-list::-webkit-scrollbar-thumb{
  background-color: var(--color-red-2);
  border-radius: 10px;
}
.home-calendar-list::-webkit-scrollbar-track{
  background-color: var(--color-red-3);
  border-radius: 10px;
}
.home-calendar-item {
  background-color: white;
  border: 1px solid var(--color-black-4);
  border-radius: 0.3rem;
  margin: 10px;
  padding: 10px;
  list-style: none;
  flex: 0 0 auto;
  text-decoration: none;
  width: 200px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}
.home-calendar-item p, .home-calendar-item h4 {
  margin: 0;
  text-align: start;
  color: black;
}

.home-calendar-item h4 {
  font-weight: bold;
  font-size: 16px;
}
.home-jobopening-info{
  background-color: var(--color-black-4);
  padding: 8px;
  border-radius: 5px;
}
.home-jobopening-info p{
  font-weight: 400;
}
</style>