<template>
  <div>
    <div class="jobopening-box">
      <h4>{{ jobopening.title }}</h4>
      <p>{{ '회사이름이 안넘어옴 ㅠㅠ' }}</p>
      <div class="jobopening-box-date">
        <div class="start-badge">시작</div>
        <p>{{ jobopening.createdDate?.split('T')[0] }}</p>
      </div>
      <div class="jobopening-box-date">
        <div class="end-badge">종료</div>
        <p class="end-date">{{ jobopening.finishedDate?.split('T')[0] }}</p>
      </div>
    </div>
    <div class="jobopening-box">
      <h3>모집조건</h3>
      <hr>
      <ConditionItem left="모집기간" :right="period" />
      <ConditionItem left="경력" :right="jobopening.minCareer" />
      <ConditionItem left="학력" :right="jobopening.lastEdu" />
      <ConditionItem left="성별" right="null" />
    </div>
    <div class="jobopening-box">
      <h3>근무조건</h3>
      <hr>
      <ConditionItem left="급여" :right="jobopening.minSalary + '원'" />
      <ConditionItem left="지역" :right="sidos?.find((sido) => sido.value===jobopening?.sidoId)?.text + ' ' + guguns?.find((gugun) => gugun.value===jobopening?.gugunId)?.text" />
      <ConditionItem left="근무기간" right="null" />
      <ConditionItem left="근무요일" :right="jobopening.workingDay" />
      <ConditionItem left="근무시간" right="null" />
    </div>
    <div class="jobopening-box">
      <h3>모집내용</h3>
      <hr>
      <ConditionItem left="직책" :right="parents?.find((parent) => parent.value===jobopening?.jobParentCategory)?.text" />
      <ConditionItem left="직무" :right="jobopening.jobPosition" />
      <ConditionItem left="고용형태" :right="jobopening.typeEmployment" />
      <ConditionItem left="모집인원" :right="jobopening.numberPeople" />
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import ConditionItem from './ConditionItem.vue'

export default {
  name: 'DetailCondition',
  components: {
    ConditionItem,
  },
  setup() {
    const store = useStore()
    
    const jobopening = computed(() => store.getters['jobopening/selectedJobopening'])
    const period = computed(() => jobopening.value.createdDate?.split('T')[0] + ' ~ ' + jobopening.value.finishedDate?.split('T')[0])

    const fetchParents = () => store.dispatch('category/getJobParent')
    fetchParents()
    const parents = computed(() => store.state.category.jobparents)

    const fetchSido = () => store.dispatch('category/getSido')
    const fetchGugun = (sidoId) => store.dispatch('category/getGugun', sidoId)
    fetchSido()
    fetchGugun(jobopening.value.sidoId)
    const sidos = computed(() => store.state.category.sidos)
    const guguns = computed(() => store.state.category.guguns)
    return {
      jobopening, period, parents, sidos, guguns,
    }
  },
}
</script>

<style>
.jobopening-box {
  background-color: white;
  border-radius: 1rem;
  margin: 10px;
  padding: 10px;
  text-align: start;
}

.jobopening-box p, .jobopening-box h3 {
  margin: 0;
}

.jobopening-box > h3 {
  font-size: 16px;
}

.jobopening-box-date {
  display: flex;
}

.jobopening-box-date > p {
  padding-top: 4px;
}

.start-badge, .end-badge {
  width: 50px;
  border-radius: 1rem;
  text-align: center;
  margin: 3px;
  margin-right: 10px;
}

.start-badge {
  border: 1px solid var(--color-black-3);
  color: var(--color-black-3);
}

.end-badge {
  border: 1px solid red;
  color: red;
}

.jobopening-box-date > .end-date {
  color: red;
}

.jobopening-box > hr {
  width: 340px;
  transform: translateX(-10px);
  border: 1px solid var(--color-red-3);
  margin: 5px 0px;
}
</style>