<template>
  <div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'ResumeCareerDetail',
  components: {},
  data() {
    return {
      example: '',
    }
  },
  computed: {
    ...mapGetters(['career']),
  },
  methods: {
    ...mapActions(['fetchCareer'])
  },
  setup() {},
  created() {
    this.fetchCareer()
  },
  mounted() {},
  unmounted() {},
}
</script>