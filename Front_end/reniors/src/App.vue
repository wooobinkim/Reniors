<template>
  <div class="container" >
    <router-view style="width: 360px;" />
    <FooterComponent />
  </div>
</template>

<script>
import FooterComponent from './components/FooterComponent.vue'

export default {
  name: "App",
  components: {
    FooterComponent,
  }
}
</script>

<style>


:root {
  --color-red-1: #f3620f;
  --color-red-2: #FF843E;
  --color-red-3: #FFE2D5;
  --color-red-4: #FFF5F0;
  --color-orange-1: #F28A07;
  --color-orange-2: #FFB252;
  --color-orange-3: #FFD39B;
  --color-orange-4: #FFE5C5;
  --color-orange-5: #FFF5E9;
  --color-yellow-1: #FFB400;
  --color-yellow-2: #FECC4E;
  --color-yellow-3: #FFEDBF;
  --color-yellow-4: #FFF7E4;
  --color-green-1: #37BF99;
  --color-green-2: #8CD6C1;
  --color-green-3: #BCF1E3;
  --color-green-4: #ECFFFA;
  --color-black-1: #6D6D6D;
  --color-black-2: #9B9B9B;
  --color-black-3: #C5C5C5;
  --color-black-4: #EAEAEA;
  --color-black-5: #F9F9F9;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
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

a{
  text-decoration: none;
}
</style>
