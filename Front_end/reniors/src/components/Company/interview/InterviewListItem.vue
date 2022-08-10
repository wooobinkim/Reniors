<template>
  <div>
    <div>{{ interviewapply.name }}</div>
    <div>{{ interviewapply.interviewDate }}</div>
    <template v-if="interviewapply.jobOpeningProcess == '면접심사중'">
      <router-link :to="{ name: 'companyopenvidu' }">
        <button @click="regist()">평가보기</button>
      </router-link>
    </template>
    <template v-else>
      <router-link :to="{ name: 'companyopenvidu' }">
        <button @click="regist()">면접보기</button>
      </router-link>
    </template>
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
