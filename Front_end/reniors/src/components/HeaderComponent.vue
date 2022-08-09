<template>
  <div class="header-component">
    <router-link class="header-logo" :to="{ name: 'home' }">
      <img src="@/assets/logo.png" alt="logo">
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'HeaderComponent',
}
</script>

<style>
.header-component {
  display: flex;
  justify-content: center;
}

.header-logo {
  min-width: 288px;
  height: 120px;
}

.header-logo > img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}
</style>