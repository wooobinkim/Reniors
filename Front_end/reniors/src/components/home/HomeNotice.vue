<template>
  <div class="home-notice">
    <p>지금 <router-link to="/mypage">로그인</router-link>을 하고<br>더 정확한 추천공고와 관리를 받아보세요!</p>
  </div>
</template>

<script>
export default {
  name: 'HomeNotice',
}
</script>

<style>
.home-notice {
  padding: 0 10px;
}

.home-notice > p {
  text-align: left;
}

.home-notice a {
  color: var(--color-green-1);
}
</style>