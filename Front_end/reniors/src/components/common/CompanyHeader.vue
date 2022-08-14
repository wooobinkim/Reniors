<template>
  <div class="company-header">
    <div class="company-left-header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle"></i>
      {{this.header}}
    </div>
    <div class="company-right-header">
      <i @click="mainpage()" class="bi bi-house"></i>
      <!-- <button @click="logout()">로그아웃</button> -->
      <img
        :src="this.companyimg"
        alt="test"
        class="company-header-img"
      />
    </div>
  </div>
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

<style scoped>
.company-header-img{
  width: 40px;
  height: 40px;
}
.company-header{
  display: flex;
  justify-content: space-between;
  width: 360px;
}
.company-header > div > .bi{
  width: 40px;
  height: 40px;
  color: red;
  font-size: 40px;
}
.company-left-header{
display: flex;
  justify-content: space-between;
  width: 100px;
}
.company-right-header{
  display: flex;
  justify-content: space-between;
  width: 100px;
}
</style>
