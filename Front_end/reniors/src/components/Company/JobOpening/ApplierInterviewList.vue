<template>
  <div>
    <template v-for="apply in applylist" :key="apply.id">
      <template
        v-if="
          apply.jobOpeningProcess == '면접' ||
          apply.jobOpeningProcess == '면접심사중' ||
          apply.jobOpeningProcess == '면접불합격'
        "
      >
        <div>
          <input
            :value="apply"
            type="checkbox"
            v-model="passUser"
            class="apply-interview-list-checkbox"
          />
          <applier-interview-list-item :apply="apply">
          </applier-interview-list-item>
        </div>
      </template>
    </template>
    <button @click="interviewpass()" class="apply-interview-pass-btn">
      최종합격
    </button>
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
    jobopeningdetail: Object,
  },
  data() {
    return {
      passUser: [],
    };
  },
  watch: {
    passUser(){
      console.log(this.passUser);
    }
  },
  async created() {
    await this.getapplylist(this.$route.params.no);
    console.log(this.passUser);
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
    ...mapActions("home", ["createNotice"]),
    interviewpass() {
      if (confirm("선택된 지원자의 상태를 최종 합격으로 변경하시겠습니까?")) {
        this.passUser.forEach((data) => {
          this.updateApply({
            jobOpeningId: this.jobopeningdetail.id,
            applyId: data.id,
            apply: {
              jobOpeningProcess: "최종합격",
            },
          });
          this.createNotice({
            jobOpeningProcess: "최종합격",
            userId: data.userId,
            applyId: data.id,
          });
        });

        let tmparr = [];
        this.applylist.forEach((apply) => {
          if (
            apply.jobOpeningProcess == "면접" ||
            apply.jobOpeningProcess == "면접심사중"
          )
            tmparr.push(apply);
        });
        let unpassUser = tmparr.filter((x) => !this.passUser.includes(x));
        unpassUser.forEach((data) => {
          this.updateApply({
            jobOpeningId: this.jobopeningdetail.id,
            applyId: data.id,
            apply: {
              jobOpeningProcess: "면접불합격",
            },
          });
          this.createNotice({
            jobOpeningProcess: "면접불합격",
            userId: data.userId,
            applyId: data.id,
          });
        });

        let data = {
          no: this.jobopeningdetail.id,
          progress: {
            jobOpeningProcess: "최종합격",
          },
        };
        this.progressJobOpening(data);
      }
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
  width: 328px;
  position: fixed;
  bottom: 55px;
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
