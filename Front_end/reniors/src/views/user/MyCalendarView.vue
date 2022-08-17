<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">나의 일정</span>
      </div>
    </header>
    <br>
    <my-calendar :interview="interview" :bookmarksdate="bookmarksdate"></my-calendar>


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
  ...mapGetters('jobopening', ['interview', 'bookmarksdate'])
  },
  setup() {},
  created() {
    this.fetchApply()
    this.fetchBookmark()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions('jobopening', ['fetchApply', 'fetchBookmark']),
  }
}
</script>

<style scoped>

  .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }



</style>