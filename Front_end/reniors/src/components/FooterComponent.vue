<template>
  <div class="footer">
    <router-link class="footroute" active-class="active" :to="{name: 'home'}">
      <i class="bi bi-house"></i>
      <p>home</p>
    </router-link> 
    <router-link class="footroute" :to="{name:'companyjobopening'}">
      <i class="bi bi-file-earmark-text"></i>
      <p>채용공고</p>
    </router-link> 
    <router-link class="footroute" to="/mypage">
      <i class="bi bi-person"></i>
      <p>MyPage</p>
    </router-link> 
    <router-link class="footroute" :to="{name: 'boardMain'}">
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> 
    <router-link class="footroute" :to="{name: 'QuestionList'}">
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
.footroute p{
  font-size: 12px;
  margin: 0px 0px 4px;
}

.footer a.router-link-exact-active {
  color: #f3620f;
}
</style>