<template>
  <div>
    <div>이름 :{{ apply.userId }}</div>
    <div>{{ apply.jobOpeningProcess }}</div>

    <button @click="resumeview()">이력서보기</button>
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
// import { ref } from "vue";
// import { mapActions, mapState } from "vuex";
// import moment from "moment";
</script>
<script>
export default {
  components: {},
  props: {
    apply: Object,
  },
  data() {
    return {};
  },
  computed: {
    // ...mapState("company", ["jobopening"]),
  },
  methods: {
    resumeview() {
      this.$router.push({
        name: "resumeview",
        params: { no: this.apply.userId },
      });
    },
    // ...mapActions("company", ["updateApply", "registRoom"]),
  },
};
</script>

<style></style>
