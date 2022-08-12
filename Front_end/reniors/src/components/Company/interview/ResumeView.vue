<template>
  <div>
    <div>이름 : {{ resume.name }}</div>
    <div>
      생일 : {{ new Date(resume.birth).getFullYear() }}-{{
        new Date(resume.birth).getMonth()
      }}-{{ new Date(resume.birth).getDate() }}
    </div>
    <div>성별 : {{ resume.gender == "F" ? "여자" : "남자" }}</div>
    <div>전화번호 : {{ resume.phone }}</div>
    <div>경력 : {{ resume.totalCareer }}년</div>
    <div>주소 : {{ resume.address }} {{ resume.extraAddress }}</div>
    <div>최종학력 : {{ resume.lastEdu }}</div>
    <!-- <img src="https://i7b307.p.ssafy.io/images/user/{{resume.id}}" /> -->
    <div v-if="resume.careerDetails.length != 0">
      <hr />
      <p class="subtitle mb-3">경력 사항</p>
      <p
        class="box pt-3 pb-3"
        v-for="career in resume.careerDetails"
        :key="career"
      >
        <span style="color: #8a8a8a">기업명</span
        ><span>&emsp;{{ career.companyName }}<br /></span>
        <span style="color: #8a8a8a">입사일</span
        ><span>&emsp;{{ career.startedAt.slice(0, 10) }}<br /></span>
        <span style="color: #8a8a8a">퇴사일</span
        ><span>&emsp;{{ career.finishedAt.slice(0, 10) }}<br /></span>
        <span style="color: #8a8a8a"></span><br /><span
          >{{ career.jobContents }}<br
        /></span>
      </p>
    </div>

    <div v-if="resume.licenses.length != 0">
      <hr />
      <p class="subtitle mb-3">자격증</p>
      <p
        class="box pt-3 pb-3"
        v-for="(license, index) in resume.licenses"
        :key="index"
      >
        <span style="color: #8a8a8a">자격증</span
        ><span>&emsp;{{ license.name }}<br /></span>
        <span style="color: #8a8a8a">취득일</span
        ><span>&emsp;{{ license.passedAt.slice(0, 10) }}<br /></span>
        <span style="color: #8a8a8a">등급</span
        ><span>&emsp;{{ license.grade }}<br /></span>
      </p>
    </div>

    <div v-if="resume.awards.length != 0">
      <hr />
      <p class="subtitle mb-3">수상 내역</p>
      <p v-for="award in resume.awards" :key="award">
        <span style="color: #8a8a8a">수상명</span
        ><span>&emsp;{{ award.name }}<br /></span>
        <span style="color: #8a8a8a">수상일</span
        ><span>&emsp;{{ award.awardedAt.slice(0, 10) }}<br /></span>
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
// import { mapActions } from "vuex";
export default {
  data() {
    return {
      birth: null,
      resumeinfo: null,
    };
  },
  watch: {
    resume: function (data) {
      this.birth = new Date(data.birth);
      this.resumeinfo = data;
      console.log(this.resumeinfo);
    },
  },
  computed: {
    ...mapState("company", ["resume", "interviewer"]),
    ...mapMutations("company", ["CLEAR_INTERVIEWER"]),
  },
  created() {
    // if (this.interviewer) this.getResume(this.interviewer.id);
    this.getResume(this.$route.params.no);
    // this.birth = new Date(this.resume.birth);
    this.CLEAR_INTERVIEWER;
  },
  methods: {
    ...mapActions("company", ["getResume"]),
  },
};
</script>

<style></style>
