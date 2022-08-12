<template>
  <div>
    <div>평가항목 : {{ usereval.contents }}</div>
    <div>평가점수 : {{ usereval.score }}</div>
    <div>비고 : {{ usereval.memo }}</div>
  </div>
</template>

<script>
export default {
  props: {
    usereval: Object,
  },
};
</script>

<style></style>
