<template>
  <button @click="prevpage()">이전</button>
  헤더헤더
  <button @click="mainpage()">홈</button>
  <button @click="logout()">로그아웃</button>
  <!-- <img src="https://i7b307.p.ssafy.io/images/company/2" alt="" /> -->
</template>

<script>
export default {
  methods: {
    prevpage() {
      this.$router.go(-1);
    },
    mainpage() {
      this.$router.push({ name: "company" });
    },
    logout() {
      localStorage.removeItem("vuex");
      localStorage.removeItem("token");
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style></style>
