<template>
  <div>
    <NavBar :jobopeningId="jobopeningId"/>
    <b-tabs pills class="jobopening-detail-tab"
      active-nav-item-class="bg-transparent"
    >
      <b-tab title="모집조건"><DetailCondition /></b-tab>
      <b-tab title="상세모집내용"><DetailContent /></b-tab>
      <!-- 회사정보가 상세조회에서 넘어오지않음 -->
      <b-tab title="기업정보"><DetailInfo v-if="false"/></b-tab>
    </b-tabs>
    <DetailApplyButton :jobopeningId="jobopeningId"/>
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import NavBar from '@/components/jobopening/NavBar.vue'
import DetailCondition from '@/components/jobopening/DetailCondition.vue'
import DetailContent from '@/components/jobopening/DetailContent.vue'
import DetailInfo from '@/components/jobopening/DetailInfo.vue'
import DetailApplyButton from '@/components/jobopening/DetailApplyButton.vue'

export default {
  name: 'JobopeningDetailView',
  components: {
    NavBar, DetailCondition, DetailContent, DetailInfo, DetailApplyButton,
  },
  setup() {
    const store = useStore()
    const jobopeningId = useRoute().params.jobopeningId

    const selectJobopening = () => store.dispatch('jobopening/selectJobopening', jobopeningId)
    selectJobopening()

    return {
      jobopeningId, selectJobopening
    }
  }
}
</script>

<style>
.jobopening-detail-tab {
  background-color: var(--color-red-4);
  padding-bottom: 5px;
}

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
</style>