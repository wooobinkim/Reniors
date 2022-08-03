<template>
  <div>
    <NavBar />
    <b-tabs pills class="jobopening-detail-tab"
      active-nav-item-class="bg-transparent"
      content-class="jobopening-detail-tab-item"
    >
      <b-tab title="모집조건"><DetailCondition /></b-tab>
      <b-tab title="상세모집내용"><DetailContent /></b-tab>
      <b-tab title="기업정보"><DetailInfo /></b-tab>
    </b-tabs>
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import NavBar from '@/components/jobopening/NavBar.vue'
import DetailCondition from '@/components/jobopening/DetailCondition.vue'
import DetailContent from '@/components/jobopening/DetailContent.vue'
import DetailInfo from '@/components/jobopening/DetailInfo.vue'

export default {
  name: 'JobopeningDetailView',
  components: {
    NavBar, DetailCondition, DetailContent, DetailInfo,
  },
  setup() {
    const jobopeningId = useRoute().params.jobopeningId

    return {
      jobopeningId,
    }
  }
}
</script>

<style>
.jobopening-detail-tab .nav {
  display: flex;
  justify-content: space-evenly;
  background-color: var(--color-red-1);
}

.jobopening-detail-tab button {
  color: white;
}

.jobopening-detail-tab button:hover {
  color: white;
}

.jobopening-detail-tab button.active {
  font-weight: bold;
}

.jobopening-detail-tab-item {
  background-color: var(--color-red-3);
}
</style>