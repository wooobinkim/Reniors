<template>
  <div>
    <h2>{{ type }} 채용 공고</h2>
    <div class="jobopening-list">
      <ul class="jobopening-item" v-for="jobopening in jobopenings" :key="jobopening.id">
        <li>
          <p>{{ jobopening.name }}</p>
          <p>{{ jobopening.context }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeJobopeningList',
  props: {
    type: String,
    jobopenings: Array,
  }
}
</script>

<style>
.jobopening-list {
  display: flex;
}

.jobopening-item {
  border: 1px solid;
  margin: 10px;
  padding: 10px;
  list-style: none;
}
</style>