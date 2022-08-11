<template>
  <div>
    제목 : {{ jobopening.title }} / {{ jobopening.jobChildCategoryName }}
  </div>
  <div>채용상황 : {{jobopening.jobOpeningProcess}}</div>

  <router-link :to="{ name: 'applylist', params: { no: jobopening.id } }"
    >지원자 수 :{{ jobopening.applies }}</router-link
  >

  <div>
    <router-link
      :to="{ name: 'companyjobopeningdetail', params: { no: jobopening.id } }"
      >상세보기</router-link
    >
    <!-- <router-link>지원자n명</router-link> -->
  </div>
</template>

<script>
export default {
  props: {
    jobopening: Object,
  },
};
</script>

<style></style>
