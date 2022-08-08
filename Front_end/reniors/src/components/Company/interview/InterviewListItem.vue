<template>
  <div>
    <div>{{ interviewapply.name }}</div>
    <div>{{ interviewapply.interviewDate }}</div>
    <button>면접보기</button>
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
