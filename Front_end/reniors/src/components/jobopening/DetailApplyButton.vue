<template>
  <div class="detail-apply-button">
    <button @click="apply">지원하기</button>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  name: 'DetailApplyButton',
  setup() {
    const store = useStore()

    const apply = () => store.dispatch('jobopening/apply')

    return {
      apply
    }
  }
}
</script>

<style>
.detail-apply-button {
  position: sticky;
  border: 1px solid var(--color-yellow-3);
  background-color: white;
  bottom: 0px;
  width: 360px;
  padding: 10px;
  align-content: center;
}

.detail-apply-button > button {
  height: 30px;
  width: 100%;
  border: none;
  border-radius: 0.5rem;
  background-color: var(--color-red-1);
  color: white;
}
</style>