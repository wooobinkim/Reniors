<template>
  <div class="home">
    <PreloginHome v-if="!isLogin" />
    <LoginHome v-else />
  </div>
</template>

<script>
import PreloginHome from '../../components/home/PreloginHome.vue'
import LoginHome from '../../components/home/LoginHome.vue'

export default {
  name: 'HomeView',
  components: {
    PreloginHome, LoginHome
  },
  data() {
    return {
      isLogin: false,
    }
  }
}
</script>

<style>
.home-header {
  color: var(--color-red-1);
}
</style>
