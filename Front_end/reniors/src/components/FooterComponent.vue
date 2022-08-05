<template>
  <div class="footer">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">채용공고</router-link> |
    <router-link to="/mypage">MyPage</router-link> |
    <router-link to="/community">커뮤니티</router-link> |
    <router-link to="/about">면접</router-link>
  </div>
</template>

<script>
export default {
  name: "FooterComponent",
};
</script>

<style>
.footer {
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-around;
  height: 64px;
  width: 100%;
  bottom: 0;
  border: 1px solid var(--color-black-4);
  background: var(--color-black-5);
}
.footer a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}
.footroute p {
  font-size: 12px;
  margin: 0px 0px 4px;
}

.footer a.router-link-exact-active {
  color: #f3620f;
}
</style>
