<template>
  <div class="company-header shadow-sm pt-2 mb-3 rounded">
    <company-header></company-header>
  </div>
  <router-view></router-view>
</template>

<script>
import CompanyHeader from "@/components/common/CompanyHeader.vue";
export default {
  components: {
    CompanyHeader,
  },

};
</script>

<style scoped>
.company-header {
  background-color: #ffffff;
  height: 40px;
}
</style>
