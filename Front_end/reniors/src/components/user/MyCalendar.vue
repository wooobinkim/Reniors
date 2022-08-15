<template>
  <div class="p-3">
    <FullCalendar :options="calendarOptions" />
  </div>
  
</template>

<script>
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import koLocale from '@fullcalendar/core/locales/ko'

// import esLocale from '@fullcalendar/core/locales/es';


export default {
  name: 'MyCalendar',
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  props: {
    interview: Array,
  },
  data() {
    return {
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin ],
        initialView: 'dayGridMonth',
        // locales: [koLocale, esLocale],
        locale: koLocale,
        events: this.interview,
        height: 500,
      
      },

    }
  },

  created(){
  },
  methods: {
  }
}
</script>

<style>

  .fc-col-header-cell-cushion  {
    text-decoration: none;
    color: #37BF99
  }

  .fc-daygrid-day-number {
    text-decoration: none;
    color: #6D6D6D;
    font-size: 12px;
  }

  .fc-event-title {
    font-size: 12px;
  }
</style>