<template>
  <div class="condition-create-view">
    <form action="" @submit.prevent="submit">
      <button class="condition-create-init" @click="initPayload"><span>⟳</span> 초기화</button>
      <label for="name">맞춤공고 설정</label><br>
      <input type="text" v-model="payload.name" name="name" id="name">
      <hr>
      <label for="region">지역 설정</label><br>
      <select v-model="payload.region" name="region" id="region">
        <option value="">지역을 선택해주세요.</option>
        <option v-for="(sido, index) in sidos" :key="index" :value="sido.id">{{ sido.name }}</option>
      </select>
      <hr>
      <label for="parent">직종 설정</label><br>
      <select v-model="payload.parent" name="parent" id="parent" @change="fetchChilds(parent)">
        <option value="">직무를 선택해주세요.</option>
        <option v-for="(parent, index) in parents" :key="index" :value="parent.id">{{ parent.name }}</option>
      </select>
      <hr>
      <label for="parent">직종 설정 (세부업무)</label><br>
      <select v-model="payload.child" name="child" id="child">
        <option value="">세부업무를 선택해주세요.</option>
        <option v-for="(child, index) in childs" :key="index" :value="child.id">{{ child.name }}</option>
      </select>
      <hr>
    </form>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'ConditionCreateView',
  // props: {
  //   condition: Object,
  // },
  setup() {
    const store = useStore()

    const fetchParents = () => store.dispatch('fetchParents')
    fetchParents()
    const fetchChilds = (parentId) => store.dispatch('fetchChilds', parentId)
    const fetchSido = () => store.dispatch('fetchSido')
    fetchSido()
    const submit = () => console.log(payload)

    const parents = computed(() => store.getters['parents'])
    const childs = computed(() => store.getters['childs'])
    const sidos = computed(() => store.getters['sido'])
    console.log(sidos)

    // props로 대체 (수정을 위해서)
    const payload = {
      name: '',
      regions: '',
      parent: '',
    }
    const initPayload = () => {
      payload.name = '',
      payload.regions = '',
      payload.parent = '',
      console.log(payload)
    }
    initPayload()

    return {
      fetchChilds, submit, initPayload,
      parents, childs, sidos, 
      payload,
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

.condition-create-view input, .condition-create-view select {
  border: 1px solid var(--color-black-3);
  border-radius: 0.4rem;
  width: 100%;
  padding: 5px 10px;
}
</style>