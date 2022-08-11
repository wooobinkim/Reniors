<template>
  <div class="container">
    <header style="width: 360px;">
    <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
      <span class="title">관심 설정</span>
    </div>
  </header>
  
  <div>
    <select class="form-select" size="10" aria-label="size 3 select example" v-model="jobparents">
      <option class="m-2" v-for="parent in parents" :key="parent" :value="parent.id">{{ parent.name }}</option>
    </select>

    <select class="form-select" size="10" aria-label="size 3 select example" v-model="recommend.jobChildCategoryId">
      <option class="m-2" v-for="child in jobchilds" :key="child" :value="child.id">{{ child.name }}</option>
    </select>
  </div>

  <div>
    <select class="form-select" size="10" aria-label="size 3 select example" v-model="area">
      <option class="m-2" v-for="sido in sidos" :key="sido" :value="sido.id">{{ sido.name }}</option>
    </select>
    <select class="form-select" size="10" aria-label="size 3 select example" v-model="recommend.gugunId">
      <option class="m-2" v-for="gugun in guguns" :key="gugun" :value="gugun.id">{{ gugun.name }}</option>
    </select>
  </div>



  </div>
</template>
<script>
import { mapActions, mapGetters, mapState } from "vuex";

export default {
  name: "PreferSetting",
  components: {},
  data() {
    return {
      jobparents: null,
      area: null,
      recommend: {
        gugunId: null,
        jobChildCategoryId: null,
        minSalary: null,
        workingDay: null,
      }
    }
  },
  computed: {
    ...mapGetters(['parents']),
    ...mapState('category', ['jobchilds', 'sidos', 'guguns'])
  },

  watch: {
    jobparents(data){
      this.getJobChild(data)
    },
    area(data){
      this.getGugun(data)
    }

  },
  setup() {},
  created() {
    this.getSido()
    this.fetchParents()
  },

  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchParents']),
    ...mapActions('category', ['getJobChild', 'getSido', 'getGugun']),
    
    
  }
}
</script>

<style scoped>



</style>