<template>
  <div class="home">
    <h1 class="home-header">comm on</h1>
    <HomeNotice />
    <HomeInfo />
    <HomeJobopeningList type="hot" :jobopenings="hotJobopenings" />
    <HomeJobopeningList type="new" :jobopenings="newJobopenings" />
  </div>
</template>

<script>
import HomeNotice from '../components/HomeNotice.vue'
import HomeInfo from '../components/HomeInfo.vue'
import HomeJobopeningList from '../components/HomeJobopeningList.vue'

export default {
  name: 'HomeView',
  components: {
    HomeNotice, HomeInfo, HomeJobopeningList,
  },
  data() {
    return {
      hotJobopenings: [
        {
          id: 1,
          name: '핫한 채용공고 첫 번째',
          context: 'dsgsdsjdgoihwghgerg'
        },
        {
          id: 2,
          name: 'second hot jobopening',
          context: 'asdigaoghrughguiehguigj'
        }
      ],
      newJobopenings: [
        {
          id: 1,
          name: 'new 채용공고 첫 번째',
          context: 'dsgsdsjdgoihwghgerg'
        },
        {
          id: 2,
          name: 'second new jobopening',
          context: 'asdigaoghrughguiehguigj'
        }
      ]
    }
  }
}
</script>

<style>
.home-header {
  color: var(--color-red-1);
}
</style>
