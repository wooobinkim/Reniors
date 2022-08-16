<template>
  <div class="jobopening-main-page-btn">
    <div class="jobopening-main-page-btn-top">
      <div @click="whole()" class="jobopening-main-page-btn-top-item">
        <div>
          <i class="bi bi-file-earmark-text"></i>
        </div>
        <div>전체공고</div>
      </div>

      <div @click="condition()" class="jobopening-main-page-btn-top-item">
        <div>
          <i class="bi bi-file-earmark-text"></i>
        </div>
        <div>맞춤공고</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    whole() {
      this.$router.push({ name: "WholeJobOpening" });
    },
    condition() {
      this.$router.push({ name: "ConditionJobOpening" });
    },
  },
};
</script>

<style>
.jobopening-main-page-btn-top {
  display: flex;
  justify-content: center;
}
.jobopening-main-page-btn-top-item {
  /* position: relative; */
  width: 150px;
  height: 200px;
  margin: 12px;
  background-color: #f28a07;
  color: #eaeaea;
  border-radius: 12px;
  font-weight: bold;
  box-shadow: 0.4px 0.4px 0.4px 0.4px gray;
  cursor: pointer;
}
.jobopening-main-page-btn-top-item > div:first-child {
  margin-top: 50px;
}
.jobopening-main-page-btn-top-item > div > i {
  font-size: 44px;
}
.jobopening-main-page-btn-top-item > div > div {
  font-size: 20px;
}
</style>
