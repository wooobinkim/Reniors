<template>
  <i @click="prevpage()" class="bi bi-arrow-left-circle"></i>
  {{this.header}}
  <i @click="mainpage()" class="bi bi-house"></i>
  <!-- <button @click="logout()">로그아웃</button> -->
  <img
    :src="this.companyimg"
    alt="test"
    class="header-img"
  />
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed:{
    ...mapGetters("company",["header","companyimg"]),
  },
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

<style>
.header-img{
  width: 30px;
  height: 30px;
}
</style>
