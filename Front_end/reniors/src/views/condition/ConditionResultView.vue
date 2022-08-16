<template>
  <template v-if="!jobopenings">
    <div class="condition-result-view">
      <p>총 <span>0</span>건의 공고가 있어요 😄</p>
      <!-- <ConditionResultItem v-for="(jobopening, index) in jobopenings" :key="index" :jobopening="jobopening"/> -->
    </div>
  </template>
  <template v-else>
    <div class="condition-result-view">
      <p>
        총 <span>{{ jobopenings.length }}</span
        >건의 공고가 있어요 😄
      </p>
      <ConditionResultItem
        v-for="(jobopening, index) in jobopenings"
        :key="index"
        :jobopening="jobopening"
      />
    </div>
  </template>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
import ConditionResultItem from "@/components/condition/ConditionResultItem.vue";

export default {
  name: "ConditionResultView",
  components: {
    ConditionResultItem,
  },
  setup() {
    const store = useStore();

    const jobopenings = computed(() => store.getters["condition/jobopenings"]);

    return {
      jobopenings,
    };
  },
};
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
