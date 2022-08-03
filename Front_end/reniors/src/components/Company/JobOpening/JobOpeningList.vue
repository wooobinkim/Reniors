<template>
  <div>회사 공고페이지</div>
  <button @click="regjobopening()">공고올리기</button>
</template>

<script>
export default {
  methods: {
    regjobopening() {
      this.$router.push({ name: "companyjobopeningregist" });
    },
  },
};
</script>

<style></style>
