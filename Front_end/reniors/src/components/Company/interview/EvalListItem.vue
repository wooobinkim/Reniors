<template>
  <div class="eval-question-list-item">
    <div class="section1">
      <span class="question-number">
        평가 순번 : {{ idx + 1 }}
      </span>
      <i class="bi bi-pencil-square"></i>
      <i class="bi bi-trash"></i>
    </div>
    <div class="section2">
      <span class="question-contents">
        평가 항목 : {{ evalquestion.contents }}
      </span>
    </div>
  </div>
  <hr />
</template>

<script>

export default {
  props: {
    evalquestion: Object,
    idx : Number,
  },
};
</script>

<style scope>
.eval-question-list-item {
  width: 328px;
  padding: 5px 10px;
  font-size: 16px;
}
.eval-question-list-item > .section1 {
  display: flex;
  margin-bottom: 3px;
}
.eval-question-list-item > .section1 > .question-number {
  width: 100%;
  text-align: left;
  color: var(--color-red-2);
}
.eval-question-list-item > .section1 > i {
  color: var(--color-black-2);
  margin: auto 3px;
}
.eval-question-list-item > .section2 {
  width: 100%;
  text-align: left;
}
</style>
