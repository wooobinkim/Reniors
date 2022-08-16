<template>
  <div class="condition-container">
    <HeaderComponent />
    <ConditionList />
    <router-view></router-view>
  </div>
</template>

<script>
import { useStore } from "vuex";
import HeaderComponent from "@/components/HeaderComponent.vue";
import ConditionList from "@/components/condition/ConditionList.vue";

export default {
  name: "ConditionView",
  components: {
    HeaderComponent,
    ConditionList,
  },
  setup() {
    const store = useStore();

    const fetchParents = () => store.dispatch("category/getJobParent");
    const fetchSido = () => store.dispatch("category/getSido");
    fetchParents();
    fetchSido();
  },
};
</script>

<style scoped>
.condition-container {
  height: 1500px;
}
</style>
