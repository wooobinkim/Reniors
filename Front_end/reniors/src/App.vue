<template>
  <div class="container">
    <b-container :toast="{root: true}" fluid="sm" position="position-fixed" style="z-index: 999;"></b-container>
    <router-view class="app-view" />
    <FooterComponent />
  </div>
</template>

<script>
import FooterComponent from "./components/FooterComponent.vue";

export default {
  name: "App",
  components: {
    FooterComponent,
  },
};
</script>

<style>
:root {
  --color-red-1: #f3620f;
  --color-red-2: #ff843e;
  --color-red-3: #ffe2d5;
  --color-red-4: #fff5f0;
  --color-orange-1: #f28a07;
  --color-orange-2: #ffb252;
  --color-orange-3: #ffd39b;
  --color-orange-4: #ffe5c5;
  --color-orange-5: #fff5e9;
  --color-yellow-1: #ffb400;
  --color-yellow-2: #fecc4e;
  --color-yellow-3: #ffedbf;
  --color-yellow-4: #fff7e4;
  --color-green-1: #37bf99;
  --color-green-2: #8cd6c1;
  --color-green-3: #bcf1e3;
  --color-green-4: #ecfffa;
  --color-black-1: #6d6d6d;
  --color-black-2: #9b9b9b;
  --color-black-3: #c5c5c5;
  --color-black-4: #eaeaea;
  --color-black-5: #f9f9f9;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 0 64px 0;
}

.app-view {
  width: 360px;
}

@media screen and (min-width: 720px) {
  .app-view {
    width: 70vh;
  }
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}
</style>
