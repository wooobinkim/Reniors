<template>
  <div>
    <div>이름 :{{apply.userId}}</div>
    <div>채용과정 : {{ apply.jobOpeningProcess }}</div>
    <div>면접날짜 : {{ apply.interviewDate }}</div>
    <button @click="interviewflag()">면접일정잡기</button>
    <div v-if="this.flag">
      <datepicker v-model="applyinfo.interviewDate" />
      <button @click="updateapply()">수정</button>
    </div>
    <button>이력서보기</button>
  </div>
</template>

<script setup>
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
// import { ref } from "vue";
import { mapActions, mapState } from "vuex";
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
      applyinfo: {
        jobOpeningProcess: null,
        interviewDate: new Date(),
      },
      flag: false,
    };
  },
  computed: {
    ...mapState("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["updateApply"]),
    updateapply() {
        // this.applyinfo.interviewDate = new Date(moment(
        //   this.applyinfo.interviewDate
        // ).format("YYYY-MM-DD hh:mm"));
        console.log(this.applyinfo.interviewDate);
      this.applyinfo.jobOpeningProcess = "면접";
      let data = {
        jobOpeningId: this.jobopening.id,
        applyId: this.apply.id,
        apply: this.applyinfo,
      };
      this.updateApply(data);
      // this.$router.go();
    },
    interviewflag() {
      this.flag = !this.flag;
    },
  },
};
</script>

<style></style>
