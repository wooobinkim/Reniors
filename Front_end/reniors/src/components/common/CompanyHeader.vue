<template>
  <button @click="prevpage()">이전</button>
  헤더헤더
  <button @click="mainpage()">홈</button>
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
  },
};
</script>

<style></style>
