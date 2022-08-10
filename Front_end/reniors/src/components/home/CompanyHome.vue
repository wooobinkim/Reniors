<template>
  <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
  <button @click="logout()">로그아웃</button>
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
export default {
  methods: {
    logout() {
      localStorage.removeItem("vuex");
      localStorage.removeItem("token");
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style></style>
