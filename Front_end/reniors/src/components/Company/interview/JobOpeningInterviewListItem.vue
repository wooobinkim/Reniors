<template>
  <div>
    <router-link
      :to="{ name: 'companyinterviewlist', params: { no: jobopening.id } }"
      >제목 : {{ jobopening.title }}</router-link
    >
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
