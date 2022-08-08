<template>
  <div class="condition-result-view">
    <p>총 <span>30</span>건의 공고가 있어요 😄</p>
    <ConditionResultItem />
  </div>
</template>

<script>
import ConditionResultItem from '@/components/condition/ConditionResultItem.vue'

export default {
  name: 'ConditionResultView',
  components: {
    ConditionResultItem,
  }
}
</script>

<style>
.condition-result-view {
  padding: 10px;
}

.condition-result-view > p {
  text-align: start;
  font-weight: bold;
}

.condition-result-view > p > span {
  color: var(--color-green-1);
}
</style>