<template>
  <div class="footer">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">채용공고</router-link> |
    <router-link to="/mypage">MyPage</router-link> |
    <router-link to="/boards">커뮤니티</router-link> |
    <router-link to="/video">면접</router-link>
  </div>
</template>

<script>
export default {
  name: "FooterComponent",
}
</script>

<style>
.footer {
  position: fixed;
  bottom: 0;
  border: 1px solid var(--color-black-4);
  background: var(--color-black-5);
  width: 100%
}

.footer a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

.footer a.router-link-exact-active {
  color: var(--color-red-1);
}
</style>