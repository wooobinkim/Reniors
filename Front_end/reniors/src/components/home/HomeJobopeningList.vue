<template>
  <div>
    <h2 class="home-jobopening-type">{{ type }}</h2>
    <ul class="home-jobopening-list">
      <router-link class="home-jobopening-item" :to="{ name: 'JobopeningDetail', params: { jobopeningId: jobopening.id } }" v-for="jobopening in jobopenings" :key="jobopening.id">
        <h4>{{ jobopening.companyName }}</h4>
        <p>{{ jobopening.title }}</p>
        <p>~ {{ jobopening.finishedDate.split('T')[0] }}</p>
      </router-link>
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
.home-jobopening-type {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
  font-size: 20px;
}

.home-jobopening-list {
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
}

.home-jobopening-item {
  background-color: white;
  border: 1px solid var(--color-orange-1);
  border-radius: 0.3rem;
  margin: 10px;
  padding: 10px;
  list-style: none;
  flex: 0 0 auto;
  text-decoration: none;
  width: 120px;
}

.home-jobopening-item p, .home-jobopening-item h4 {
  margin: 0;
  text-align: start;
  color: black;
}

.home-jobopening-item h4 {
  font-weight: bold;
  font-size: 16px;
}
</style>