<template>
  <div class="detail-apply-button">
    <button @click="apply(isLogin)" 
      :class="isActive ? 'apply-active' : 'apply-deactive'" 
    >{{ isApply ? '지원완료' : '지원하기' }}</button>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'DetailApplyButton',
  props: {
    jobopeningId: [String, Number],
  },
  setup(props) {
    const store = useStore()

    const apply = (login) => {
      if (login === true) store.dispatch('jobopening/apply', props.jobopeningId)
    }
    const isLogin = computed(() => store.getters['isLogginedIn'])
    const isApply = computed(() => store.getters['jobopening/isApply'])
    const isActive = computed(() => !isApply.value && isLogin.value)
    const fetchApply = () => store.dispatch('jobopening/fetchApply')
    fetchApply()

    return {
      apply, isLogin, isApply, isActive
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
  color: white;
}

.detail-apply-button > button.apply-active {
  background-color: var(--color-red-1);
}

.detail-apply-button > button.apply-deactive {
  background-color: var(--color-red-3);
}

.detail-apply-button > button.apply-deactive:hover {
  cursor: default;
}
</style>