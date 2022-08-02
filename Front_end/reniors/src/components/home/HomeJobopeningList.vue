<template>
  <div>
    <h2 class="jobopening-type">{{ type }}</h2>
    <ul class="jobopening-list">
      <li class="jobopening-item" v-for="jobopening in jobopenings" :key="jobopening.id">
        <p>{{ jobopening.name }}</p>
        <p>{{ jobopening.context }}</p>
      </li>
    </ul>
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
.jobopening-type {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
}

.jobopening-list {
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
}

.jobopening-item {
  background-color: var(--color-black-4);
  border-radius: 0.3rem;
  margin: 10px;
  padding: 10px;
  list-style: none;
  flex: 0 0 auto;
}
</style>