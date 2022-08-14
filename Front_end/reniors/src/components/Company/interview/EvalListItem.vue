<template>
  <div>
    {{evalquestion.id}}
    {{ evalquestion.contents }}
  </div>
</template>

<script>
export default {
  props: {
    evalquestion: Object,
  },
};
</script>

<style></style>
