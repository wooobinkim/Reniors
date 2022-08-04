<template>
  <div class="home-notice">
    <div v-if="login">
      <p>🔔 오늘 확인하셔야 할 알림이 <router-link to="/mypage" class="home-notice-count">{{ noticeCount }}개</router-link> 있어요!</p>
      <HomeCalendarList />
    </div>
    <p v-else>지금 <router-link to="/mypage">로그인</router-link>을 하고<br>더 정확한 추천공고와 관리를 받아보세요!</p>
  </div>
</template>

<script>
import HomeCalendarList from './HomeCalendarList.vue'

export default {
  name: 'HomeNotice',
  components: {
    HomeCalendarList,
  },
  props: {
    login: Boolean,
  },
  setup () {
    const noticeCount = 3

    return {
      noticeCount,
    }
  }
}
</script>

<style>
.home-notice {
  padding: 0;
}

.home-notice p {
  text-align: left;
  margin-left: 10px;
}

.home-notice a {
  color: var(--color-green-1);
}

.home-notice > div > p {
  font-weight: bold;
}

.home-notice-count {
  text-decoration: none;
  font-size: 20px;
}
</style>