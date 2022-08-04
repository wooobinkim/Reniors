<template>
  <div class="jobopening-item">
    <div>
      <p class="jobopening-item-company">{{ jobopening.company_name }}</p>
      <p class="jobopening-item-title">{{ jobopening.title }}</p>
      <p class="jobopening-item-contents">{{ jobopening.contents }}</p>
    </div>
    <p class="jobopening-item-period">{{ jobopening.started_at }} ~ {{ jobopening.finished_at }}</p>
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
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  background-color: white;
  border-radius: 0.4rem;
  margin: 10px 10px;
  padding: 10px;
  height: 150px;
}

.jobopening-item p {
  margin: 0;
  text-align: start;
}

.jobopening-item-company, .jobopening-item-title, .jobopening-item-contents {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 1;
}

.jobopening-item-title {
  text-decoration-line: underline;
  font-weight: bold;
}

.jobopening-item-contents {
  -webkit-line-clamp: 2;
  font-weight: bold;
}

.jobopening-item-period {
  margin-top: 10px;
  color: var(--color-green-1);
  font-size: 10px;
}
</style>