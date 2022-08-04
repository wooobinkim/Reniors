<template>
  <div class="footer">
    <router-link class="footroute" active-class="active" to="/">
      <i class="bi bi-house"></i>
      <p>home</p>
    </router-link> 
    <router-link class="footroute" to="/jobopening">
      <i class="bi bi-file-earmark-text"></i>
      <p>채용공고</p>
    </router-link> 
    <router-link class="footroute" to="/mypage">
      <i class="bi bi-person"></i>
      <p>MyPage</p>
    </router-link> 
    <router-link class="footroute" to="/board">
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> 
    <router-link class="footroute" to="/video">
      <i class="bi bi-camera-video"></i>
      <p>면접/연습</p>
    </router-link>
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