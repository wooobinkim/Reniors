<template>
  <div class="condition-result-item">
    <h3>어쩌구 회사이름</h3>
    <div>{{jobopening}}</div>
    <router-link :to="{ name: 'JobopeningDetail', params: { jobopeningId: jobopening.id } }">공고링크</router-link>
    <p class="condition-result-item-title">채용공고제목</p>
    <div class="condition-result-item-job">
      <p>정규직</p>
      <p>연봉 <span>4000만원</span></p>
    </div>
    <div class="condition-result-item-region">
      <font-awesome-icon icon="fa-solid fa-location-dot" /><p>유성구 봉명동</p>
      <font-awesome-icon icon="fa-regular fa-clock" /><p>주 5일 9:00~18:00</p>
    </div>
    <font-awesome-icon icon="fa-regular fa-bookmark" class="condition-result-item-bookmark" @click="bookmark"/>
  </div>
</template>

<script>
export default {
  name: 'ConditionResultItem',
  props: {
    jobopening: Object,
  },
  setup() {
    const bookmark = () => console.log('bookmark')

    return {
      bookmark,
    }
  }
}
</script>

<style>
.condition-result-item {
  position: relative;
  border-radius: 0.5rem;
  background-color: var(--color-red-4);
  padding: 20px;
  box-shadow: 0px 0px 3px var(--color-black-2);
  text-align: start;
}

.condition-result-item p {
  margin: 0;
}

.condition-result-item h3 {
  margin: 0;
  font-weight: bolder;
  font-size: 14px;
  margin: 5px 0;
}

.condition-result-item-title {
  font-size: 20px;
}

.condition-result-item-job {
  display: flex;
  justify-content: flex-start;
  font-weight: bold;
}

.condition-result-item-job > p:first-child {
  color: var(--color-red-1);
  margin-right: 10px;
}

.condition-result-item-job > p:last-child {
  color: var(--color-green-1);
}

.condition-result-item-job > p:last-child > span {
  color: black;
}

.condition-result-item-region {
  display: grid;
  grid-template-columns: 30px auto;
  color: var(--color-black-2);
  margin-top: 10px;
}

.condition-result-item-bookmark {
  position: absolute;
  bottom: 20px;
  right: 20px;
}

.condition-result-item-bookmark:hover {
  cursor: pointer;
}
</style>