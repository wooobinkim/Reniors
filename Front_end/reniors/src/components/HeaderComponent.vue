<template>
  <div class="header-component">
    <div class="header-logo">
      <img src="@/assets/logo.png" alt="logo">
    </div>
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
  width: 80%;
  height: 120px;
}

.header-logo > img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}
</style>