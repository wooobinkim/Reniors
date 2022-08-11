<template>
  <div>
    <div>이름 :{{ apply.userId }}</div>
    <div>면접날짜 : {{ apply.interviewDate.getFullYear() }}-{{apply.interviewDate.getMonth()+1 }}-{{
        apply.interviewDate.getDate()
      }} {{apply.interviewDate.getHours()}}시 {{apply.interviewDate.getMinutes()==0?null:apply.interviewDate.getMinutes()+"분"}}</div>

    <button @click="resumeview()">이력서보기</button>
    <button @click="interviewflag()">면접일정잡기</button>

     <div v-if="this.flag">
      <datepicker v-model="applyinfo.interviewDate" />
      <button @click="updateapply()">수정</button>
    </div>


  </div>
</template>

<script setup>
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
// import { log } from "console";
// import { ref } from "vue";
import { mapActions, mapState } from "vuex";
// import moment from "moment";
</script>
<script>
export default {
  components: {
    Datepicker
  },
  props: {
    apply: Object,
  },
  data() {
    return {
flag: false,
applyinfo: {
        jobOpeningProcess: null,
        interviewDate: new Date(),
      },

    };
  },
  computed: {
    ...mapState("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["updateApply", "registRoom"]),
    updateapply() {
      this.applyinfo.jobOpeningProcess = "면접";
      // console.log(this.applyinfo);
      let data = {
        jobOpeningId: this.jobopening.id,
        applyId: this.apply.id,
        apply: this.applyinfo,
      };
      this.updateApply(data);

      let room = {
        isActive: "CLOSE",
        sessionId: "InterviewSession" + this.jobopening.id,
        userId: this.apply.userId,
        jobOpeningId: this.jobopening.id,
      };

      this.registRoom(room);
      this.$router.go();
    },
    interviewflag() {
      this.flag = !this.flag;
    },
    resumeview() {
      this.$router.push({
        name: "resumeview",
        params: { no: this.apply.userId },
      });
    },
   
  },
};
</script>

<style></style>
