<template>
  <div>
    <hr>
    <div class="condition-list">
      <div class="condition-item" v-for="(condition, index) in conditions" :key="index">
        <div class="condition-item-header">
          <p class="condition-item-number">맞춤공고{{ index+1 }}</p>
          <div>
            <i>edit</i>
            <i>delete</i>
          </div>
        </div>
        <p>{{ condition.region }}</p>
      </div>
      <div class="condition-item-create">
        <i>아이콘</i>
        <p>당신의 맞춤 공고를 만들어 보세요 !</p>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'ConditionList',
  setup() {
    const store = useStore()

    const fetchConditions = () => store.dispatch('condition/fetchConditions')
    fetchConditions()
    const conditions = computed(() => store.getters['condition/conditions'])

    return {
      conditions,
    }
  },
}
</script>

<style>
.condition-list {
  display: flex;
  flex-wrap: no-wrap;
  overflow-x: scroll;
}

.condition-list > div {
  border-radius: 0.5rem;
  height: 140px;
  width: 300px;
  flex: 0 0 auto;
  padding: 10px;
  margin: 5px;
}

.condition-item {
  background-color: var(--color-orange-2);
}

.condition-item-header {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
}

.condition-item-create {
  display: flex;
  flex-direction: column;
  justify-content: center;
  border: 1px dashed black;
  background-color: var(--color-green-4);
  font-weight: bold;
}
</style>