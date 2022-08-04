<template>
  <div class="condition-create-view">
    <form action="" @submit.prevent="submit">
      <button class="condition-create-init"><span>⟳</span> 초기화</button>
      <label for="name">맞춤공고 설정</label><br>
      <input type="text" v-model="name" name="name" id="name">
      <hr>
      <label for="region">지역 설정</label><br>
      <input type="text" v-model="region" name="region" id="region">
      <hr>
      <label for="job">직종 설정</label><br>
      <input type="text" v-model="job" name="job" id="job">
      <hr>
    </form>
  </div>
</template>

<script>
export default {
  name: 'ConditionCreateView',
  // props: {
  //   condition: Object,
  // },
  setup() {
    const submit = () => console.log()

    let name = ''
    let region = ''
    let job = ''

    return {
      submit, name, region, job
    }
  }
}
</script>

<style>
.condition-create-view {
  position: relative;
  text-align: start;
}

.condition-create-init {
  position: absolute;
  border: none;
  background-color: transparent;
  top: 0;
  right: 0;
}

.condition-create-init > span {
  font-size: 20px;
  font-weight: bolder;
}

.condition-create-view input {
  border: 1px solid var(--color-black-3);
  border-radius: 0.4rem;
  padding: 5px 10px;
}
</style>