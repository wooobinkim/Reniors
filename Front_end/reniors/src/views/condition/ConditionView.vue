<template>
  <div>
    <HeaderComponent />
    <ConditionList />
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderComponent from '@/components/HeaderComponent.vue'
import ConditionList from '@/components/condition/ConditionList.vue'

export default {
  name: 'ConditionView',
  components: {
    HeaderComponent, ConditionList,
  },
}
</script>

<style>

</style>