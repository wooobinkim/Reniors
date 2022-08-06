<template>
  <div>
    <HeaderComponent />
    <ConditionList />
    <router-view></router-view>
  </div>
</template>

<script>
import ConditionCreateView from './ConditionCreateView.vue'
import ConditionResultView from './ConditionResultView.vue'
import HeaderComponent from '@/components/HeaderComponent.vue'
import ConditionList from '@/components/condition/ConditionList.vue'

export default {
  name: 'ConditionView',
  components: {
    HeaderComponent, ConditionList, ConditionCreateView, ConditionResultView,
  },
}
</script>

<style>

</style>