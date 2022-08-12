<template>
  <div>
    <div class="jobopening-box">
      <h3>기업정보</h3>
      <hr>
      <img :src="jobopening.baseURL + jobopening.companyId" alt="">
      <h5>{{ jobopening.companyName }}</h5>
      <ConditionItem left="설립연도" :right="jobopening.companyEstablishedAt" />
      <ConditionItem left="기업주소" :right="jobopening.companyAddress" />
      <ConditionItem left="홈페이지" :right="jobopening.companyUrl" />
      <h4>이 기업의 다른 채용 공고</h4>
      <hr class="small">
      <div class="jobopening-detail-content" v-for="(jobopening, index) in relatedJobopenings" :key="index">
        <p class="jobopening-detail-content-title">{{ jobopening.title }}</p>
        <p class="jobopening-detail-content-condition">{{ jobopening.jobPosition }} | {{ jobopening.minCareer }} | {{ jobopening.lastEdu }}</p>
        <p class="jobopening-detail-content-period">~ {{ jobopening.finishedDate }}</p>
      </div>
      <h4>뉴스</h4>
      <hr class="small">
      <div class="jobopening-detail-news">
        <div class="jobopening-detail-content"></div>
        <div class="jobopening-detail-content"></div>
        <div class="jobopening-detail-content"></div>
        <div class="jobopening-detail-content"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import ConditionItem from './ConditionItem.vue'

export default {
  name: 'DetailInfo',
  components: {
    ConditionItem,
  },
  setup() {
    const store = useStore()

    const jobopening = computed(() => store.getters['jobopening/selectedJobopening'])
    const company = computed(() => jobopening.value?.companyDto)

    return {
      jobopening, company
    }
  }
}
</script>

<style>
/* same as DetailCondition.vue */
.jobopening-box h5 {
  font-weight: bold;
  margin: 0;
}

.jobopening-box h4 {
  font-size: 16px;
  margin: 0;
  margin-top: 10px;
}

.jobopening-box > hr.small {
  width: 260px;
  transform: translateX(0px);
  border: 1px solid var(--color-red-1);
  margin: 0 0 5px 0;
}

.jobopening-detail-content {
  box-sizing: border-box;
  background-color: white;
  border: 1px solid var(--color-yellow-1);
  border-radius: 0.5rem;
  padding: 10px 15px;
  margin: 5px;
}

.jobopening-detail-content-title {
  font-weight: bold;
}

.jobopening-detail-content-condition {
  color: var(--color-black-2);
}

.jobopening-detail-content-period {
  color: red;
}

.jobopening-detail-news {
  display: grid;
  grid-template-columns: 160px 160px;
}
</style>