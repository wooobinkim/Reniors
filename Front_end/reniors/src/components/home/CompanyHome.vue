<template>
  <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
  <button @click="logout()">로그아웃</button>
  <div>
    <router-link @click="this.setheader('채용관리')" :to="{ name: 'companyjobopening' }">채용관리</router-link>
  </div>
  <div>
    <router-link @click="this.setheader('면접')" :to="{ name: 'companyinterview' }">면접</router-link>
  </div>
  <div>
    <router-link @click="this.setheader('마이페이지')" :to="{ name: 'companymypage' }">마이페이지</router-link>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  methods: {
    ...mapActions("company",["setheader","getCompany"]),
    logout() {
      localStorage.removeItem("vuex");
      localStorage.removeItem("token");
      this.$router.push({ name: "Login" });
    },
  },
  created(){
    this.getCompany();
  },
};
</script>

<style></style>
