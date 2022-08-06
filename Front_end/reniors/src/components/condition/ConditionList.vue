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
        <p>{{ condition.region }}</p>
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
        return [{ region: '서울' }]
      }
      else return datas
    })

    const options = {
      padding: 50,
      arrows: false,
      pagination: false,
      wheel: true,
      wheelSleep: 500,
      isNavigation: true,
      width : 360,
      height : 140,
      gap : '1rem',
    }

    return {
      conditions, options,
    }
  },
}
</script>

<style>
@import '@splidejs/splide/dist/css/themes/splide-default.min.css';

.splide__track--nav>.splide__list>.splide__slide {
  border: none;
  padding: 10px;
}

.splide__track--nav>.splide__list>.splide__slide.is-active {
  border: none;
}

.condition-item {
  background-color: var(--color-orange-2);
}

.condition-item-header {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
}

.condition-list li {
  border-radius: 0.5rem;
}

.condition-item-create {
  display: flex;
  flex-direction: column;
  justify-content: center;
  border: 1px dashed black !important;
  background-color: var(--color-green-4);
  font-weight: bold;
}
</style>