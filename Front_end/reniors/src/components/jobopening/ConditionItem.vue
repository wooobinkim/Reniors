<template>
  <div class="condition-item">
    <p class="condition-item-left">{{ left }}</p>
    <p class="condition-item-value">{{ right }}</p>
  </div>
</template>

<script>
export default {
  name: 'ConditionItem',
  props: {
    left: String,
    right: [String, Number],
  }
}
</script>

<style>
.condition-item {
  display: grid;
  grid-template-columns: 120px 200px;
  text-align: start;
}

.condition-item-left {
  color: var(--color-black-2)
}

.condition-item-right {
  font-weight: bold;
}
</style>