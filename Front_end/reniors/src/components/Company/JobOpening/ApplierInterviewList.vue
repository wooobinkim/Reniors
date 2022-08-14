<template>
  <div>
    <template v-for="apply in applies" :key="apply.id">
      <template
        v-if="
          apply.jobOpeningProcess == '면접' ||
          apply.jobOpeningProcess == '면접심사중' ||
          apply.jobOpeningProcess == '면접불합격'
        "
      >
        <div>
          <input
            :value="apply.id"
            type="checkbox"
            v-model="passUser"
            class="apply-interview-list-checkbox"
          />
          <applier-interview-list-item
            :apply="apply"
            :jobOpeningId="this.$route.params.no"
          >
          </applier-interview-list-item>
        </div>
      </template>
    </template>
    <button @click="interviewpass()" class="apply-interview-pass-btn">
      최종합격
    </button>
    <!-- <div for="check">이름 :{{ apply.userId }}</div>

    <button @click="resumeview()">이력서보기</button> -->
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapGetters } from "vuex";
</script>
<script>
import ApplierInterviewListItem from "./ApplierInterviewListItem.vue";
export default {
  components: {
    ApplierInterviewListItem,
  },
  props: {
    // apply: Object,
    jobopeningdetail: Object,
  },
  data() {
    return {
      // applyinfo: {
      //   // jobOpeningProcess: null,
      //   // interviewDate: new Date(),
      // },
      passUser: [],
      applies: null,
      //   passuserId: [],
      // flag: false,
    };
  },
  watch: {
    applylist: function (datas) {
      this.applies = [];
      datas.forEach((data) => {
        data.interviewDate = new Date(data.interviewDate);
        this.applies.push(data);
      });
    },
  },
  async created() {
    console.log(this.$route.params.no);
    await this.getapplylist(this.$route.params.no);
  },
  computed: {
    ...mapGetters("company", ["jobopening", "applylist"]),
  },
  methods: {
    ...mapActions("company", [
      "getapplylist",
      "progressJobOpening",
      "updateApply",
    ]),
    interviewpass() {
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
      this.applylist.forEach((apply) => {
        if (
          apply.jobOpeningProcess == "면접" ||
          apply.jobOpeningProcess == "면접심사중"
        )
          tmparr.push(apply.id);
      });
      let unpassUser = tmparr.filter((x) => !this.passUser.includes(x));
      unpassUser.forEach((data) => {
        this.updateApply({
          jobOpeningId: this.jobopeningdetail.id,
          applyId: data,
          apply: {
            jobOpeningProcess: "면접불합격",
          },
        });
      });

      let data = {
        no: this.jobopeningdetail.id,
        progress: {
          jobOpeningProcess: "최종합격",
        },
      };
      this.progressJobOpening(data);
      this.$router.go();
    },
  },
};
</script>

<style scoped>
.apply-interview-list-checkbox {
  float: right;
  margin-right: 10px;
  margin-top: 10px;
  width: 24px;
  height: 24px;
  cursor: pointer;
  border-radius: 5px;
  -webkit-appearance: none;
  border: 2px solid var(--color-black-3);
  position: relative;
  display: inline-block;
}
.apply-interview-list-checkbox:checked {
  background-color: white;
}
.apply-interview-list-checkbox:checked::after {
  content: "✔";
  font-size: 20px;
  width: 20px;
  height: 20px;
  text-align: center;
  position: absolute;
  left: 0;
  top: 0;
  padding: auto;
  line-height: 24px;
  color: var(--color-yellow-1);
}
.apply-interview-pass-btn {
  position: fixed;
  bottom: 70px;
  left: 50%;
  transform: translate(-50%, 0);
  border: none;
  padding: 6px 40px;
  font-size: 20px;
  font-weight: bold;
  border-radius: 10px;
  background-color: var(--color-green-1);
  color: white;
}
</style>
