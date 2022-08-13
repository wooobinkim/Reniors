<template>
  <div>
    <div>이름 :{{ apply.userId }}</div>
    <div>
      면접날짜 : {{ new Date(apply.interviewDate).getFullYear() }}-{{
        new Date(apply.interviewDate).getMonth() + 1
      }}-{{ new Date(apply.interviewDate).getDate() }}
      {{ new Date(apply.interviewDate).getHours() }}시
      {{
        new Date(apply.interviewDate).getMinutes() == 0
          ? null
          : new Date(apply.interviewDate).getMinutes() + "분"
      }}
    </div>
    <div>{{ apply.jobOpeningProcess }}</div>
    <button @click="resumeview()">이력서보기</button>
    <template v-if="apply.jobOpeningProcess == '면접'">
      <button @click="interviewflag()">면접일정잡기</button>
    </template>
    <template v-if="apply.jobOpeningProcess == '면접심사중'">
      <router-link
        :to="{ name: 'usereval', params: { no: this.apply.userId } }"
      >
        <button>면접평가보기</button></router-link
      >
    </template>

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
import { mapActions, mapGetters } from "vuex";
// import moment from "moment";
</script>
<script>
export default {
  components: {
    Datepicker,
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
        sessionId: null,
      },
    };
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["updateApply", "registRoom"]),
    updateapply() {
      this.applyinfo.jobOpeningProcess = "면접";
      // console.log(this.applyinfo);
      this.applyinfo.sessionId = this.jobopening.title + this.jobopening.id;
      let data = {
        jobOpeningId: this.jobopening.id,
        applyId: this.apply.id,
        apply: this.applyinfo,
      };
      console.log(data);
      this.updateApply(data);

      // let room = {
      //   isActive: "CLOSE",
      //   sessionId: "InterviewSession" + this.jobopening.id,
      //   userId: this.apply.userId,
      //   jobOpeningId: this.jobopening.id,
      // };

      // this.registRoom(room);
      // this.$router.go();
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
