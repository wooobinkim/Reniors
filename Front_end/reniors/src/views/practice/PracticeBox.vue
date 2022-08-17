<template>
  <div>
    <div class="total">
      <!-- header -->
      <div class="head">
        <router-link :to="{ name: 'VideoMain' }">
          <i
            class="bi bi-arrow-left-circle-fill"
            style="color: #ffd39b; font-size: 24px; margin: 0 16px"
          ></i>
        </router-link>
        <p>내 연습함</p>
      </div>

      <!-- content -->
      <div class="content row">
        <div
          v-for="(record, index) in this.myrecords"
          :key="index"
          style="width: 144px; padding: 0"
        >
          <!-- 비디오섬네일 -->
          <router-link :to="{ path: '/practice/box/' + index }">
            <div class="videobox col-6">
              <video :src="record.recordURL"></video>
              <p>{{ record.fileName }}</p>
            </div>
          </router-link>

          <!-- 비디오재생 -->
          <!-- <video autoplay :src='record.recordURL' controls></video> -->
          <!-- <video preload="metadata" src={{url}}></video> -->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "PracticeBox",
  components: {},
  data() {
    return {
      myrecords: [],
    };
  },
  computed: {
    ...mapGetters(["records"]),
  },
  created() {
    this.fetchRecording();
  },
  watch: {
    records: function (data) {
      this.myrecords = { ...data };
    },
  },
  methods: {
    ...mapActions(["fetchRecording"]),
  },
};
</script>

<style scoped>
.total {
  background-color: #fff5f0;
  width: 100%;
}
.head {
  height: 48px;
  width: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #ff843e;
  margin: 0;
  background-color: white;
  box-shadow: 0 0 1px gray;
}
.head p {
  font-size: 20px;
  font-weight: bold;
  color: #ffb400;
  margin: 0 16px;
}
.content {
  margin: 16px;
  display: flex;
  justify-content: space-around;
}
.videobox {
  width: 144px;
  height: 160px;
  border-radius: 10px;
  border: solid #ff843e 1px;
  background-color: white;
  margin: 8px auto;
}
video {
  width: 120px;
  height: 120px;
  border: #c5c5c5 solid 0.5px;
  border-radius: 5px;
  margin: 4px auto 2px;
}
.videobox p {
  margin: auto;
}
</style>
