<template>
  <div>
    <div class="jobopening-box">
      <h4>({{ jobopening.title }}) {{ jobopening.contents }}</h4>
      <p>{{ jobopening.companyDto?.name }}</p>
      <div class="jobopening-box-date">
        <div class="start-badge">시작</div>
        <p>{{ jobopening.createdDate }}</p>
      </div>
      <div class="jobopening-box-date">
        <div class="end-badge">종료</div>
        <p class="end-date">{{ jobopening.finishedDate }}</p>
      </div>
    </div>
    <div class="jobopening-box">
      <h3>모집조건</h3>
      <hr>
      <ConditionItem left="모집기간" :right="period" />
      <ConditionItem left="경력" :right="jobopening.minCareer" />
      <ConditionItem left="학력" :right="jobopening.lastEdu" />
      <ConditionItem left="성별" right="정보없음" />
    </div>
    <div class="jobopening-box">
      <h3>근무조건</h3>
      <hr>
      <ConditionItem left="급여" :right="jobopening.minSalary + '원'" />
      <ConditionItem left="지역" right="정보없음" />
      <ConditionItem left="근무기간" right="정보없음" />
      <ConditionItem left="근무요일" :right="jobopening.workingDay" />
      <ConditionItem left="근무시간" right="정보없음" />
    </div>
    <div class="jobopening-box">
      <h3>모집내용</h3>
      <hr>
      <ConditionItem left="직책" right="정보없음" />
      <ConditionItem left="직무" :right="jobopening.jobPosition" />
      <ConditionItem left="고용형태" right="정보없음" />
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
    const period = jobopening.value.createdDate + ' ~ ' + jobopening.value.finishedDate

    return {
      jobopening, period
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

.start-badge, .end-badge {
  width: 50px;
  border-radius: 1rem;
  text-align: center;
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