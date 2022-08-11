<template>
  <div>
    <template v-for="apply in applylist" :key="apply.id">
      <template v-if="apply.jobOpeningProcess == '서류심사중' || apply.jobOpeningProcess == '서류불합격'">
        <div>
          <input :value="apply.id" type="checkbox" v-model="passUser" />
          <applier-resume-list-item
            :apply="apply"
            :jobOpeningId="this.$route.params.no"
          >
          </applier-resume-list-item>
        </div>
        <hr>
      </template>
    </template>
      <button @click="resumepass()">서류합격</button>
    <!-- <div for="check">이름 :{{ apply.userId }}</div>

    <button @click="resumeview()">이력서보기</button> -->
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapState } from "vuex";
</script>
<script>
import ApplierResumeListItem from "./ApplierResumeListItem.vue";
export default {
  components: {
    ApplierResumeListItem,
  },
  props: {
    // apply: Object,
    jobopeningdetail:Object,
  },
  data() {
    return {
      // applyinfo: {
      //   // jobOpeningProcess: null,
      //   // interviewDate: new Date(),
      // },
        passUser: [],
      //   passuserId: [],
      // flag: false,
    };
  },
  created(){
    this.getapplylist(this.$route.params.no);
  },
  computed: {
    ...mapState("company", ["jobopening","applylist"]),
  },
  methods: {
    ...mapActions("company", ["getapplylist", "progressJobOpening","updateApply"]),
    resumepass(){
      this.passUser.forEach((data) => {
        this.updateApply({
          jobOpeningId: this.jobopeningdetail.id,
          applyId: data,
          apply: {
            jobOpeningProcess: "면접심사중",
          },
        });
      });
      
      let tmparr = [];
      this.applylist.forEach(apply => {
        tmparr.push(apply.id);
      });
      let unpassUser = tmparr.filter(x=>!this.passUser.includes(x));
      unpassUser.forEach(data => {
        this.updateApply({
          jobOpeningId: this.jobopeningdetail.id,
          applyId: data,
          apply: {
            jobOpeningProcess: "서류불합격",
          },
        });
      });

      let data = {
        no : this.jobopeningdetail.id,
        progress:{
            jobOpeningProcess : "면접심사중"
        }
      }
    this.progressJobOpening(data)
      // this.$router.go();
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
