<template>
  <div>
    <div>{{ jobopeningdetail.title }}</div>
    <div>{{ jobopeningdetail.contents }}</div>
    <router-link
      :to="{
        name: 'companyjobopeningupdate',
        params: { no: this.$route.params.no },
      }"
      >수정</router-link
    >
    <button @click="deletejobopening()">공고올리기</button>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
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
    ...mapActions("company", ["getJobOpening", "deleteJobOpening"]),
    deletejobopening() {
      this.deleteJobOpening(this.$route.params.no);
      this.$router.push({ name: "companyjobopeninglist" });
    },
  },
};
</script>

<style></style>
