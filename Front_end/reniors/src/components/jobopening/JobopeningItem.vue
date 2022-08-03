<template>
  <div class="jobopening-item">
    <p>어쩌고 회사이름</p>
    <h3>채용공고제목</h3>
    <p>날짜</p>
  </div>
</template>

<script>
export default {
  name: 'JobopeningItem',
  props: {
    jobopening: Object,
  }
}
</script>

<style>
.jobopening-item {
  box-sizing: border-box;
  box-shadow: 1px 1px 1px 1px var(--color-black-3);
  background-color: white;
  margin: 0;
}
</style>