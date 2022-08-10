<template>
  <div>
    <div>{{ interviewapply.name }}</div>
    <div>{{ interviewapply.interviewDate }}</div>
    <router-link :to="{ name: 'companyopenvidu' }">
      <button @click="regist()">면접보기</button>
    </router-link>
  </div>
</template>

<script>
export default {
  props: {
    interviewapply: Object,
  },
  methods: {
    regist() {
      this.$store.commit(
        "company/SET_INTERVIEWER_LIST",
        this.interviewapply.userId
      );
    },
  },
};
</script>

<style></style>
