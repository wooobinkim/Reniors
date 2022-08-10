<template>
  <div>
    제목 : {{ jobopening.title }} / {{ jobopening.jobChildCategoryName }}
  </div>

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
