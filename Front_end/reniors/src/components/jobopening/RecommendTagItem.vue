<template>
  <div class="recommend-tag-item">
    {{ name }}
  </div>
</template>

<script>
export default {
  name: 'RecommendTagItem',
  props: {
    name: String,
  }
}
</script>

<style>
.recommend-tag-item {
  display: inline-block;
  box-sizing: border-box;
  background-color: white;
  border: 1px solid var(--color-yellow-1);
  border-radius: 0.4rem;
  padding: 0 5px;
  margin: 3px;
}
</style>