<template>
  <div class="detail-apply-button">
    <button @click="apply(isLogin)" 
      :class="isLogin ? 'apply-active' : 'apply-deactive'" 
      :title="isLogin ? '지원하기' : '로그인이 필요합니다.'"
    >지원하기</button>
    {{applies}}
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
    const fetchApply = () => store.dispatch('jobopening/fetchApply')
    fetchApply()

    const applies = computed(() => store.state.jobopening.applies)

    return {
      apply, isLogin, applies
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