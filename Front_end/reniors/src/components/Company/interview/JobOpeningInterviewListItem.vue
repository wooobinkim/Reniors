<template>
  <div class="company-jobOpening-ing-list-item">
    <div class="title">제목 : {{ jobopening.title }}</div>
    <div class="job-category">{{ jobopening.jobChildCategoryName }}</div>

    <div class="jobOpening-detail-btn-box">
      <router-link
        :to="{ name: 'companyjobopeningdetail', params: { no: jobopening.id } }"
      >
        <i class="bi bi-gear"></i>
      </router-link>
      <router-link
        :to="{ name: 'companyinterviewlist', params: { no: jobopening.id } }"
        class="to-apply-list-btn"
      >
        면접일정 관리
      </router-link>
      <router-link
        :to="{ name: 'companyeval', params: { no: jobopening.id } }"
        class="to-apply-list-btn"
      >
        면접평가 관리
      </router-link>

      <!-- <button class="to-apply-list-btn" @click="interview()">
        면접일정 관리
      </button>
      <button class="to-apply-list-btn" @click="eval()">면접평가 관리</button> -->
    </div>
  </div>
</template>

<script>
export default {
  props: {
    jobopening: Object,
  },
  methods: {
    interview() {
      this.$router.push({
        name: "companyinterviewlist",
        params: { no: this.jobopening.id },
      });
    },
    eval() {
      this.$router.push({
        name: "companyeval",
        params: { no: this.jobopening.id },
      });
    },
  },
};
</script>

<style></style>
