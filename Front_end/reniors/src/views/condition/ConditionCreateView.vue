<template>
  <div class="condition-create-view">
    <form action="" @submit.prevent="submit(payload)" :key="payload.name">
      <button class="condition-create-init" @click.prevent="initPayload(payload)"><span>⟳</span> 초기화</button>
      <label for="name">맞춤공고 설정</label><br>
      <input type="text" v-model="payload.name" name="name" id="name">
      <hr>
      <label for="region">지역 설정</label><br>
      <select name="region" id="region" @change="selectHopearea(this)">
        <option v-for="(sido, index) in sidos" :key="index" :value="sido.value">{{ sido.text }}</option>
      </select>
      <hr>
      <label for="parent">직종 설정</label><br>
      <select v-model="payload.parent" name="parent" id="parent" @change="fetchChilds(payload.parent)">
        <option v-for="(parent, index) in parents" :key="index" :value="parent.value">{{ parent.text }}</option>
      </select>
      <hr>
      <label for="parent">직종 설정 (세부업무)</label><br>
      <select v-model="payload.child" name="child" id="child">
        <option v-for="(child, index) in childs" :key="index" :value="child.value">{{ child.text }}</option>
      </select>
      <hr>
      <label>고용 형태</label>
      <div class="condition-create-type-employment">
        <div @click="selectType(type, $event)" id="type1">정규직</div>
        <div @click="selectType(type, $event)" id="type2">계약직</div>
        <div @click="selectType(type, $event)" id="type3">인턴</div>
        <div @click="selectType(type, $event)" id="type4">프리랜서</div>
        <div @click="selectType(type, $event)" id="type5">파견직</div>
        <div @click="selectType(type, $event)" id="type6">아르바이트</div>
      </div>
      <hr>
      <label for="lastEdu">최종 학력</label><br>
      <select v-model="payload.lastEdu" name="lastEdu" id="lastEdu">
        <option value="">최종 학력을 선택해주세요.</option>
        <option v-for="(lastEdu, index) in lastEdus" :key="index" :value="lastEdu.id">{{ lastEdu.name }}</option>
      </select>
      <hr>
      <label for="minCareer">최소 경력</label><br>
      <select v-model="payload.minCareer" name="minCareer" id="minCareer">
        <option value="">최소 경력을 선택해주세요.</option>
        <option v-for="(minCareer, index) in minCareers" :key="index" :value="minCareer.id">{{ minCareer.name }}</option>
      </select>
      <hr>
      <label>근무일수</label>
      <div class="condition-create-type-employment">
        <div @click="selectDay(day, $event)" id="day1">주 1일</div>
        <div @click="selectDay(day, $event)" id="day2">주 2일</div>
        <div @click="selectDay(day, $event)" id="day3">주 3일</div>
        <div @click="selectDay(day, $event)" id="day4">주 4일</div>
        <div @click="selectDay(day, $event)" id="day5">주 5일</div>
        <div @click="selectDay(day, $event)" id="day6">주 6일</div>
      </div>
      <hr>
      <label for="minSalary">급여</label><br>
      <input type="number" v-model="payload.minSalary" name="minSalary" id="minSalary" placeholder="숫자만 입력 가능합니다.">
      <div class="condition-create-info">
        <div class="condition-create-info-icon">
          <font-awesome-icon icon="fa-solid fa-scale-balanced" style="color: var(--color-red-1); font-size: 20px;"/>
        </div>
        <p>리니어즈는 고용상 성차별 모집 방지를 위해<br>성별, 연령 조건 설정 기능을 제공하지 않고 있습니다.</p>
      </div>
      <div class="condition-create-submit">
        <button type="submit">적용하기</button>
      </div>
    </form>
  </div>
</template>

<script>
import { computed, getCurrentInstance } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'ConditionCreateView',
  // props: {
  //   condition: Object,
  // },
  setup() {
    const store = useStore()
    const instance = getCurrentInstance()

    const hopearea = []

    const fetchParents = () => store.dispatch('category/getJobParent')
    fetchParents()
    const fetchChilds = (parent) => store.dispatch('category/getJobChild', parent)
    const fetchSido = () => store.dispatch('category/getSido')
    fetchSido()

    const parents = computed(() => store.state.category.jobparents)
    const childs = computed(() => store.state.category.jobchilds)
    const sidos = computed(() => store.state.category.sidos)

    const selectHopearea = (event) => {
      hopearea.push(event.value)
      console.log(hopearea)
    }

    const type = {
      type1: false,
      type2: false,
      type3: false,
      type4: false,
      type5: false,
      type6: false,
    }
    const selectType = (type, event) => {
      type[event.target.id] = !type[event.target.id]
      event.target.classList.toggle('active')
      console.log(type)
    }

    const lastEdus = [
      { id: '1', name: '고졸' },
      { id: '2', name: '대졸' },
      { id: '3', name: '석사' },
      { id: '4', name: '박사' },
    ]
    const minCareers = [
      { id: '1', name: '경력무관' },
      { id: '2', name: '아르바이트' },
      { id: '3', name: '1년 미만' },
      { id: '4', name: '1년 이상' },
    ]

    const day = {
      day1: false,
      day2: false,
      day3: false,
      day4: false,
      day5: false,
      day6: false,
    }
    const selectDay = (day, event) => {
      day[event.target.id] = !day[event.target.id]
      event.target.classList.toggle('active')
      console.log(day)
    }

    // props로 대체 (수정을 위해서)
    // type을 typeEmployment를 action에서 처리해야됨 (true인것마다 axios요청 또 보내기?)
    const payload = {
      name: '',
      parent: '',
      child: '',
      type: '',
      lastEdu: '',
      minSalary: '',
    }
    const initPayload = (payload) => {
      payload.name = '',
      payload.parent = '',
      payload.child = '',
      payload.type = '',
      payload.lastEdu = '',
      payload.minSalary = '',
      instance?.proxy?.$forceUpdate()
    }

    const submit = (payload) => console.log(payload)

    return {
      fetchChilds, submit, initPayload, selectType, selectDay, selectHopearea,
      parents, childs, sidos, type, lastEdus, minCareers, day,
      payload,
    }
  }
}
</script>

<style>
.condition-create-view {
  position: relative;
  text-align: start;
}

.condition-create-submit {
  display: flex;
  justify-content: center;
  background-color: white;
  padding: 20px;
  width: 100%;
}

.condition-create-submit > button {
  background-color: var(--color-green-1);
  border: none;
  border-radius: 1rem;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  color: white;
  width: 140px;
}

.condition-create-init {
  position: absolute;
  border: none;
  background-color: transparent;
  top: 0;
  right: 0;
}

.condition-create-init > span {
  font-size: 20px;
  font-weight: bolder;
}

.condition-create-view input, .condition-create-view select {
  border: 1px solid var(--color-black-3);
  border-radius: 0.4rem;
  width: 100%;
  padding: 5px 10px;
}

.condition-create-type-employment {
  display: grid;
  grid-template-columns: 120px 120px 120px;
}

.condition-create-type-employment div {
  border: 1px solid var(--color-black-2);
  border-radius: 0.5rem;
  text-align: center;
  color: var(--color-black-2);
  margin: 5px;
  padding: 5px;
  cursor: pointer;
}

.condition-create-type-employment div.active {
  background-color: var(--color-red-3);
  border-color: var(--color-red-1);
  color: var(--color-red-1);
}

.condition-create-info {
  display: flex;
  border-radius: 0.2rem;
  background-color: var(--color-orange-4);
  margin-top: 20px;
  padding: 15px 30px 15px 30px;
}

.condition-create-info-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 2rem;
  background-color: white;
  height: 40px;
  width: 40px;
}

.condition-create-info p {
  margin: 0;
  margin-left: 10px;
  padding-top: 2px;
  font-size: 12px;
  font-weight: bold;
}
</style>