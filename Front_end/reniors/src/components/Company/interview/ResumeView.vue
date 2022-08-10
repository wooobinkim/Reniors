<template>
  <div></div>
</template>

<script>
import { mapActions } from "vuex";
// import { mapActions } from "vuex";
export default {
  computed: {},
  created() {},
  methods: {
    ...mapActions("resume", ["fetchCareer", "fetchLicense", "fetchAward"]),
  },
};
</script>

<style></style>
