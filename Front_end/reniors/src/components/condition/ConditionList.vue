<template>
  <div>
    <hr>
    <Splide class="condition-list" :options="options">
      <SplideSlide class="condition-item" v-for="(condition, index) in conditions" :key="index">
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
          <p>고용형태 {{ condition.typeEmployment }}</p>
        </div>
      </SplideSlide>
      <SplideSlide class="condition-item-create">
        <i>아이콘</i>
        <p>당신의 맞춤 공고를 만들어 보세요 !</p>
      </SplideSlide>
    </Splide>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { Splide, SplideSlide } from '@splidejs/vue-splide'
import _ from 'lodash'

export default {
  name: 'ConditionList',
  components: {
    Splide, SplideSlide,
  },
  setup() {
    const store = useStore()

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
      console.log(event)
    }

    const options = {
      padding: 50,
      arrows: false,
      pagination: false,
      wheel: true,
      wheelSleep: 500,
      isNavigation: true,
      width : 360,
      gap : '1rem',
    }

    return {
      popover,
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
  transition: all ease 1s;
}

.splide__track--nav>.splide__list>.splide__slide.is-active {
  border: none;
  box-shadow: 0px 0px 3px var(--color-black-1);
}
.splide__track--nav>.splide__list>.splide__slide.is-active.popover-active {
  height: 200px;
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
}

.condition-item-popover.active {
  display: block;
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