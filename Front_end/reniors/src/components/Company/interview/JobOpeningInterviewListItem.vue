<template>
  <div class="company-jobOpening-ing-list-item">
    <div class="title">
      {{ jobopening.title }}
    </div>
    <div class="job-category">
      {{ jobopening.jobChildCategoryName }}
    </div>
    <div class="period">
      {{ dateFormat(jobopening.createdDate) }} ~ {{ dateFormat(jobopening.finishedDate) }}
    </div>

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
import dayjs from 'dayjs';

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
    dateFormat(val) {
      return dayjs(val).format("YYYY-MM-DD");
    },
  },
};
</script>

<style scoped>
.company-jobOpening-ing-list-item {
  width: 328px;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 16px;
  border-color: var(--color-black-2); 
  box-shadow: inset 0 0 1px 1px var(--color-black-3), 0 0 5px var(--color-black-3);
}
.company-jobOpening-ing-list-item > .title {
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 16px;
}
.company-jobOpening-ing-list-item > .job-category {
  width: 100%;
  text-align: right;
  font-size: 12px;
  color: var(--color-orange-2);
}
.company-jobOpening-ing-list-item > .period {
  width: 100%;
  text-align: right;
  font-size: 12px;
  color: var(--color-orange-2);
}
.jobOpening-detail-btn-box{
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
.jobOpening-detail-btn-box > a {
  text-decoration: none;
  color: var(--color-black-2);
}
.jobOpening-detail-btn-box > a > .bi-gear {
  font-size: 20px;
}
.to-apply-list-btn:link {
  color: white;
}
.to-apply-list-btn:visited {
  color: white;
}
.to-apply-list-btn{
  padding: 5px 10px;
  background-color: var(--color-red-1);
  border-radius: 5px;
  font-size: 15px;
}
</style>
