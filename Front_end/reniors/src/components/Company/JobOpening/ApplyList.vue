<template>
  <div>
    <div>
      제목 : {{ jobopeningdetail.title }} /
      {{ jobopeningdetail.jobChildCategoryName }}
    </div>
    <div>상세보기</div>
    <div>채용과정 : {{jobopeningdetail.jobOpeningProcess}}</div>
    <div>지원자 수 : {{ jobopeningdetail.applies }}</div>

    <template v-for="apply in applylist" :key="apply.id">
      <div>
        <input :value="apply.id" type="checkbox" v-model="passUser" />
        <apply-list-item
          :apply="apply"
          :jobOpeningId="this.$route.params.no"
        ></apply-list-item>
      </div>
    </template>
    <button @click="pass()">합격자 등록</button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ApplyListItem from "./ApplyListItem.vue";
export default {
  components: { ApplyListItem },
  data() {
    return {
      jobopeningdetail: {},
      passUser: [],
    };
  },
  created() {
    this.getapplylist(this.$route.params.no);
    this.getJobOpening(this.$route.params.no);
  },
  watch: {
    jobopening: function (data) {
      this.jobopeningdetail = data;
    },
  },
  computed: {
    ...mapState("company", ["jobopening", "applylist"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpening", "getapplylist", "updateApply"]),
    pass() {
      this.passUser.forEach((data) => {
        this.updateApply({
          jobOpeningId: this.jobopeningdetail.id,
          applyId: data,
          apply: {
            jobOpeningProcess: "최종합격",
          },
        });
        this.$router.go();
      });
    },
    completeStep(payload) {
      this.demoSteps.forEach((step) => {
        if (step.name === payload.name) {
          step.completed = true;
        }
      });
    },
    // Executed when @active-step event is triggered
    isStepActive(payload) {
      this.demoSteps.forEach((step) => {
        if (step.name === payload.name) {
          if (step.completed === true) {
            step.completed = false;
          }
        }
      });
    },
  },
};
</script>

<style></style>
