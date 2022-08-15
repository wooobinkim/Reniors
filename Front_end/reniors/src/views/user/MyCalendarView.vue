<template>
  <div>
    <my-calendar :interview="interview"></my-calendar>

  </div>
</template>
<script>
import MyCalendar from '@/components/user/MyCalendar.vue'
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'MyCalendarView',
  components: { MyCalendar },
  data() {
    return {
      example: '',
    }
  },
  computed: {
  ...mapGetters('jobopening', ['interview'])
  },
  setup() {},
  created() {
    this.fetchApply()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions('jobopening', ['fetchApply']),
  }
}
</script>