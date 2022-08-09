<template>
  <div class="company-header shadow-sm pt-2 mb-3 rounded">
    <company-header></company-header>
  </div>
  <!-- <div class="interview-header shadow-sm mb-3 rounded">
    <div>
      <span @click="interviewpage()">면접일정 | </span>
      <span @click="evalpage()">평가관리</span>
    </div>
  </div> -->
  <router-view></router-view>
</template>

<script>
import CompanyHeader from "@/components/common/CompanyHeader.vue";
// import InterviewHeader from "@/components/Company/interview/InterviewHeader.vue";
export default {
  components: {
    CompanyHeader,
    // InterviewHeader,
  },
  // created() {
  //   this.$router.push({ name: "jobopeninginterviewlist" });
  // },
  // methods: {
  //   interviewpage() {
  //     this.$router.push({ name: "jobopeninginterviewlist" });
  //   },
  //   evalpage() {
  //     this.$router.push({ name: "companyeval" });
  //   },
  // },
};
</script>

<style>
.company-header {
  background-color: #ffffff;
  height: 40px;
  width: 100%;
}
.interview-header {
  background-color: #37bf99;
  height: 20px;
  width: 100%;
}
</style>
