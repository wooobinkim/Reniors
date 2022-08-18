<template>
  <div>
    <header>
        <router-link class="header-logo" :to="{ name: 'home' }">
          <img src="@/assets/logo_cut.png" alt="logo">
        </router-link>
    </header>
    <br>
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

  header {
    width: 100%;
    height: 48px;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  header img{
    height: 38px;
    margin: auto;
    width: auto;
  }



</style>