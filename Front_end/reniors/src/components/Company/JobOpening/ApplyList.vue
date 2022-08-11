<template>
  <div>
    <div>
      제목 : {{ jobopeningdetail.title }} /
      {{ jobopeningdetail.jobChildCategoryName }}
    </div>
    <div>상세보기</div>
    <div>채용과정 : {{jobopeningdetail.jobOpeningProcess}}</div>
    <div>지원자 수 : {{ jobopeningdetail.applies }}</div>
  <hr>

  <template v-if="jobopeningdetail.jobOpeningProcess == '서류심사중'">
  <applier-resume-list :jobopeningdetail="jobopeningdetail"></applier-resume-list>
  </template>

  <template v-if="jobopeningdetail.jobOpeningProcess == '면접심사중'">
  <applier-interview-list :jobopeningdetail="jobopeningdetail"></applier-interview-list>
  </template>

  <template v-if="jobopeningdetail.jobOpeningProcess == '최종합격'">
  <applier-pass-list :jobopeningdetail="jobopeningdetail"></applier-pass-list>
  </template>
    
    
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
// import ApplyListItem from "./ApplyListItem.vue";
import ApplierResumeList from "./ApplierResumeList.vue";
import ApplierInterviewList from "./ApplierInterviewList.vue";
import ApplierPassList from "./ApplierPassList.vue";
export default {
  components: { ApplierResumeList,ApplierInterviewList,ApplierPassList },
  data() {
    return {
      jobopeningdetail: {},
    };
  },
  created() {
    this.getJobOpening(this.$route.params.no);
  },
  watch: {
    jobopening: function (data) {
      this.jobopeningdetail = data;
    },
  },
  computed: {
    ...mapState("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpening"]),

    // interviewpass(){
    //   this.passUser.forEach((data) => {
    //     console.log(data);
    //     this.updateApply({
    //       jobOpeningId: this.jobopeningdetail.id,
    //       applyId: data,
    //       apply: {
    //         jobOpeningProcess: "면접심사중",
    //       },
    //     });
    //   });

    //   let data = {
    //     no : this.jobopeningdetail.id,
    //     progress:{
    //         jobOpeningProcess : "면접심사중"
    //     }
    //   }
    // this.progressJobOpening(data)
    //   this.$router.go();
    // }
  },
};
</script>

<style></style>
