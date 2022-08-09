<template>
  <div>
    <div v-if="registflag"><eval-regist></eval-regist></div>
    <button @click="changeflag()">평가항목 추가</button>
  </div>
</template>

<script>
import EvalRegist from "./EvalRegist.vue";
export default {
  components: {
    EvalRegist,
  },
  data() {
    return {
      registflag: false,
    };
  },
  methods: {
    changeflag() {
      this.registflag = !this.registflag;
    },
  },
};
</script>

<style></style>
