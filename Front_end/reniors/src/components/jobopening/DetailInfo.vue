<template>
  <div>
    <div class="jobopening-box">
      <h3>기업정보</h3>
      <hr>
      <img :src="company.baseURL + company.companyProfile" alt="">
      <h5>{{ jobopening.companyName }}</h5>
      <ConditionItem left="설립연도" :right="company.establishedAt" />
      <ConditionItem left="기업주소" :right="company.address" />
      <ConditionItem left="홈페이지" :right="company.companyUrl" />
      <h4>이 기업의 다른 채용 공고</h4>
      <hr class="small">
      <div class="jobopening-detail-list">
        <div class="jobopening-detail-content" v-for="(jobopening, index) in jobopening.companyJobOpening" :key="index" @click="movejobopening(jobopening.id)">
          <p class="jobopening-detail-content-title">{{ jobopening.title }}</p>
          <p class="jobopening-detail-content-period">~ {{ jobopening.finishedDate?.split('T')[0] }}</p>
        </div>
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
    const company = computed(() => jobopening.value?.companyResponse)

    return {
      jobopening, company
    }
  },
  methods:{
    movejobopening(data){
      // console.log(data);
      this.$router.push({ name: 'JobopeningDetail', params: { jobopeningId: data} });
    }
  }
}
</script>

<style scoped>
/* same as DetailCondition.vue */
.jobopening-box img{
/* width: 180px; */
height: 180px;
margin-bottom: 20px;
}
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

.jobopening-detail-list {
  display: grid;
  grid-template-columns: 160px 160px;
}

@media screen and (min-width: 720px) {
  .jobopening-detail-list {
    grid-template-columns: 22vh 22vh 22vh;
  }
}
</style>