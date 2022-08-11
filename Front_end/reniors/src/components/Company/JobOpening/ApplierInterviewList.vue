<template>
  <div>
    <template v-for="apply in applies" :key="apply.id">
      <template v-if="apply.jobOpeningProcess == '면접심사중' || apply.jobOpeningProcess == '면접불합격'">
        <div>
          <input :value="apply.id" type="checkbox" v-model="passUser" />
          <applier-interview-list-item
            :apply="apply"
            :jobOpeningId="this.$route.params.no"
          >
          </applier-interview-list-item>
        </div>
        <hr>
      </template>
    </template>
      <button @click="interviewpass()">최종합격</button>
    <!-- <div for="check">이름 :{{ apply.userId }}</div>

    <button @click="resumeview()">이력서보기</button> -->
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapState } from "vuex";
</script>
<script>
import ApplierInterviewListItem from "./ApplierInterviewListItem.vue";
export default {
  components: {
    ApplierInterviewListItem,
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
        applies:null,
      //   passuserId: [],
      // flag: false,
    };
  },
  watch:{
    applylist:function (datas) {
      this.applies = [];
      datas.forEach(data => {
        data.interviewDate = new Date(data.interviewDate);
        this.applies.push(data);
      });
    }
  },
  created(){
    this.getapplylist(this.$route.params.no);
  },
  computed: {
    ...mapState("company", ["jobopening","applylist"]),
  },
  methods: {
    ...mapActions("company", ["getapplylist", "progressJobOpening","updateApply"]),
    interviewpass(){
      this.passUser.forEach((data) => {
        this.updateApply({
          jobOpeningId: this.jobopeningdetail.id,
          applyId: data,
          apply: {
            jobOpeningProcess: "최종합격",
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
            jobOpeningProcess: "면접불합격",
          },
        });
      });

      let data = {
        no : this.jobopeningdetail.id,
        progress:{
            jobOpeningProcess : "최종합격"
        }
      }
    this.progressJobOpening(data)
      this.$router.go();
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
