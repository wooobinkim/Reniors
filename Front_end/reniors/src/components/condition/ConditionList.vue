<template>
  <div>
    <hr>
    <Splide class="condition-list" :options="options">
      <SplideSlide class="condition-item" v-for="(condition, index) in conditions" :key="index" @click="routeResult">
        <div class="condition-item-header">
          <p class="condition-item-number">맞춤공고{{ index+1 }}</p>
          <div>
            <i>edit</i>
            <i>delete</i>
          </div>
        </div>
        <div class="condition-item-preview">
          <p>{{ condition.region }}</p>
          <p>직종: 몰라</p>
        </div>
        <button class="condition-item-button" @click="popover">더 보기</button>
        <div class="condition-item-popover">
          <p>고용형태</p>
          <p>{{ condition.typeEmployment }}</p>
          <p>최종학력</p>
          <p>{{ condition.typeEmployment }}</p>
          <p>경력</p>
          <p>{{ condition.typeEmployment }}</p>
          <p>근무일수</p>
          <p>{{ condition.typeEmployment }}</p>
        </div>
      </SplideSlide>
      <SplideSlide class="condition-item-create" @click="routeCreate">
        <i>아이콘</i>
        <p>당신의 맞춤 공고를 만들어 보세요 !</p>
      </SplideSlide>
    </Splide>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { Splide, SplideSlide } from '@splidejs/vue-splide'
import _ from 'lodash'

export default {
  name: 'ConditionList',
  components: {
    Splide, SplideSlide,
  },
  setup() {
    const store = useStore()
    const router = useRouter()

    const routeCreate = (event) => {
      if (event.currentTarget.classList.contains('is-active')) router.push({ name: 'ConditionCreate' })
    }
    const routeResult = (event) => {
      if (event.currentTarget.classList.contains('is-active')) router.push({ name: 'ConditionResult' })
    }

    const fetchConditions = () => store.dispatch('condition/fetchConditions')
    fetchConditions()
    const conditions = computed(() => {
      const datas = store.getters['condition/conditions']
      if (_.isEmpty(datas?.value)) {
        return [{ region: '서울', typeEmployment: '정규직' }, { region: '아산', typeEmployment: '비정규직' }, { region: '대전', typeEmployment: '노예' },]
      }
      else return datas
    })

    const popover = (event) => {
      if (event.target.innerText === '더 보기') event.target.innerText = '닫기'
      else event.target.innerText = '더 보기'
      event.target.parentElement.classList.toggle('popover-active')
      event.target.classList.toggle('active')
      event.target.nextSibling.classList.toggle('active')
    }

    const options = {
      padding: 50,
      arrows: false,
      pagination: false,
      isNavigation: true,
      width : 360,
      gap : '1rem',
    }

    return {
      popover, routeCreate, routeResult,
      conditions, options,
    }
  },
}
</script>

<style>
@import '@splidejs/splide/dist/css/themes/splide-default.min.css';

.splide__track--nav>.splide__list>.splide__slide {
  position: relative;
  border: none;
  border-radius: 0.5rem;
  height: 140px;
  margin: 5px;
  margin-bottom: 15px;
  padding: 10px;
  transition: height ease 0.5s;
}

.splide__track--nav>.splide__list>.splide__slide.is-active {
  border: none;
  box-shadow: 0px 0px 3px var(--color-black-1);
}
.splide__track--nav>.splide__list>.splide__slide.popover-active {
  height: 225px;
}

.splide__track--nav>.splide__list>.splide__slide.is-active>button {
  box-shadow: 0px 1.5px 2px var(--color-black-2);
}

.condition-list {
  margin-bottom: 10px;
}

.condition-item {
  background-color: var(--color-orange-2);
}

.condition-item-header {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
}

.condition-item-create {
  display: flex;
  flex-direction: column;
  justify-content: center;
  border: 1px dashed black !important;
  background-color: var(--color-green-4);
  font-weight: bold;
}

.condition-item-popover {
  display: none;
  border-radius: 0.4rem;
  background-color: white;
  padding: 10px 20px;
}

.condition-item-popover.active {
  display: grid;
  grid-template-columns: 80px 140px;
}

.condition-item-popover p {
  margin: 0;
  font-size: 12px;
  text-align: start;
}

.condition-item-button {
  position: absolute;
  bottom: 0;
  width: 56px;
  height: 26px;
  transform: translate(-50%, 50%);
  border: 1px solid var(--color-yellow-1);
  border-radius: 1rem;
  background-color: white;
  font-size: 12px;
}
</style>