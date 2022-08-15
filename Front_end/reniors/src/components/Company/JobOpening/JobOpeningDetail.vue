<template>
  <div class="company-jobOpening-detail-box">
    <div class="title">{{ jobopeningdetail.title }}</div>
    <div>{{ jobopeningdetail.contents }}dqw</div>
    <router-link :to="{name: 'companyjobopeningupdate', params: { no: this.$route.params.no },}" class="detail-btn">
      수정
    </router-link>
    <button @click="deletejobopening()" class="detail-btn">삭제</button>
    <button @click="finish()" class="detail-btn">채용 종료</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
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
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", [
      "getJobOpening",
      "deleteJobOpening",
      "finishJobOpening",
    ]),
    deletejobopening() {
      this.deleteJobOpening(this.$route.params.no);
      this.$router.push({ name: "companyjobopeninglist" });
    },
    finish() {
      this.finishJobOpening(this.jobopening.id);
      this.$router.push({ name: "companyjobopeninglist" });
    },
  },
};
</script>

<style scoped>

.company-jobOpening-detail-box{
  width: 100%;
  padding: 20px;
  margin-top: 20px;
  border-radius: 10px;
  border-color: var(--color-black-2); 
  box-shadow: inset 0 0 1px 1px var(--color-black-3), 0 0 5px var(--color-black-3);
}
.company-jobOpening-detail-box > .title {
  font-size: 40px;
  font-weight: bold;
}
.company-jobOpening-detail-box > .detail-btn{
  width: 320px;
  display: block;
  margin: auto;
  margin-top: 10px;
  background-color: var(--color-green-1);
  border-radius: 5px;
  border: none;
  padding: 5px;
  color: white;
  font-size: 20px;
  font-weight: bold;
  text-decoration: none;
}
.company-jobOpening-detail-box > .detail-btn:link{
  color: white;
}
.company-jobOpening-detail-box > .detail-btn:visited{
  color: white;
}
</style>
