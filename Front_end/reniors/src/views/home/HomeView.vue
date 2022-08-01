<template>
  <div class="home">
    <PreloginHome v-if="!isLogin" />
    <LoginHome v-else />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import PreloginHome from '../../components/home/PreloginHome.vue'
import LoginHome from '../../components/home/LoginHome.vue'

export default {
  name: 'HomeView',
  components: {
    PreloginHome, LoginHome
  },
  setup() {
    const store = useStore()

    const isLogin = computed(() => store.getters['home/isLogin'])

    return {
      isLogin,
    }

  }
}
</script>

<style>
.home-header {
  color: var(--color-red-1);
}
</style>
