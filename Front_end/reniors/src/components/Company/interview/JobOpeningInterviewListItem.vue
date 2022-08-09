<template>
  <div>
    제목 : {{ jobopening.title }}
    <button @click="interview()">면접일정 관리</button>
    <button @click="eval()">면접평가 관리</button>
  </div>
</template>

<script>
export default {
  props: {
    jobopening: Object,
  },
  methods: {
    interview() {
      this.$router.push({
        name: "companyinterviewlist",
        params: { no: this.jobopening.id },
      });
    },
    eval() {
      this.$router.push({
        name: "companyeval",
        params: { no: this.jobopening.id },
      });
    },
  },
};
</script>

<style></style>
