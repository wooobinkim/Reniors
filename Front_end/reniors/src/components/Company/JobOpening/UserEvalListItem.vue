<template>
  <div class="usereval-list-item">
    <div class="eval-number">평가 번호 : {{ idx + 1 }}</div>
    <div>평가 항목 : {{ usereval.contents }}</div>
    <div>평가 점수 : {{ usereval.score }}</div>
    <div>비고 :</div>
    <textarea v-model="memo" readonly></textarea>
  </div>
  <hr />
</template>

<script>
export default {
  props: {
    usereval: Object,
    idx: Number,
  },
  data() {
    return {
      memo: this.$props.usereval.memo,
    };
  },
};
</script>

<style scope>
.usereval-list-item {
  width: 328px;
  padding: 10px 15px;
  font-size: 16px;
  text-align: left;
  background-color: var(--color-green-3);
  border-radius: 8px;
  border: 2px solid var(--color-black-3);
  box-shadow: inset 0 0 1px 1px var(--color-black-3),
    0 0 5px var(--color-black-3);
}
.usereval-list-item > .eval-number {
  width: 100%;
  margin-bottom: 3px;
  text-align: left;
  color: var(--color-red-2);
}
.usereval-list-item > textarea {
  width: 100%;
  border-radius: 5px;
  background-color: var(--color-orange-4);
}
</style>
