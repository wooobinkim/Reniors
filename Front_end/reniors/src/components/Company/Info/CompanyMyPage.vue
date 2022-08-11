<template>
  <div>회사이미지</div>
  <router-link :to="{ name: 'companyupdate' }">기업 정보 수정</router-link>
  <div>채용중{{jobopeninglisting.length}}</div>
  <div>채용완료{{jobopeninglisted.length}}</div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
export default {
  created() {
    this.CLEAR_JOBOPENING_LIST;
    this.getJobOpeningList();
  },
  computed: {
    ...mapState("company", ["jobopeninglisting", "jobopeninglisted"]),
    ...mapMutations("company", ["CLEAR_JOBOPENING_LIST"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpeningList"]),

  },
};
</script>

<style></style>
