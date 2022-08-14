<template>
  <div class="apply-interview-item-info-box">
    <div>이름 : {{ apply.name }}</div>
    <template v-if="new Date(apply.interviewDate).getFullYear() == 1970">
      <div>면접날짜 : 미정</div>
    </template>
    <template v-else>
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
    </template>
    <div>채용현황 : {{ apply.jobOpeningProcess }}</div>
    <div class="apply-interview-btn-box">
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
    </div>

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

<style scoped>
.apply-interview-item-info-box {
  width: 100%;
  border-bottom: 2px solid var(--color-black-3);
  padding: 10px;
}
.apply-interview-item-info-box > div:not(:last-child) {
  width: 100%;
  text-align: left;
  margin-left: 10px;
  font-weight: bold;
  font-size: 20px;
}
.apply-interview-btn-box {
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
}
.apply-interview-item-info-box button {
  border: none;
  background-color: var(--color-green-2);
  padding: 6px 20px;
  color: black;
  border-radius: 5px;
  font-weight: bold;
  font-size: 14px;
  margin: 0;
}
</style>
