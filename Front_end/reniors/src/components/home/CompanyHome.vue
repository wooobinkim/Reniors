<template>
  <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
  <div>
    <router-link :to="{ name: 'companyjobopening' }">채용관리</router-link>
  </div>
  <div>
    <router-link :to="{ name: 'companyinterview' }">면접</router-link>
  </div>
  <div>
    <router-link :to="{ name: 'companymypage' }">마이페이지</router-link>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
