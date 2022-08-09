<template>
  <div>
    <div>{{ interviewapply.name }}</div>
    <div>{{ interviewapply.interviewDate }}</div>
    <router-link :to="{ name: 'companyopenvidu' }">
      <button>면접보기</button>
    </router-link>
  </div>
</template>

<script>
export default {
  props: {
    interviewapply: Object,
  },
};
</script>

<style></style>
