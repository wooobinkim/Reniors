<template>
  <div>
    <h2 class="home-jobopening-type">내가 북마크한 공고</h2>
    <div class="home-calendar-list">
      <router-link :to="{ name: 'JobopeningDetail', params: { jobopeningId: bookmark.jobOpeningResponse.id } }" class="home-calendar-item" v-for="bookmark, index in bookmarks" :key="index" :id="'homeCalendarItem'+index">
        <div>{{ bookmark.jobOpeningResponse.companyName }}</div>
        <div>{{ bookmark.jobOpeningResponse.title }}</div>
        <div>~ {{ bookmark.jobOpeningResponse.finishedDate.split('T')[0] }}</div>
      </router-link>
    </div>
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

<style>
.home-calendar-list {
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
  text-align: start;
}

.home-calendar-item {
  background-color: var(--color-orange-5);
  border: 1px solid var(--color-orange-1);
  border-radius: 0.3rem;
  margin: 10px;
  padding: 10px;
  color: black;
  list-style: none;
  flex: 0 0 auto;
  text-decoration: none;
}

.home-calendar-item:hover {
  color: black;
}

.home-calendar-item > div:first-child {
  font-weight: bold;
}
</style>