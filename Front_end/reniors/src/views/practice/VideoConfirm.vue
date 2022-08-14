<template>
  <div>
    <div class="total">
      <!-- header -->
      <div class="head">
        <router-link :to="{ name: 'PracticeBox' }">
          <i
            class="bi bi-arrow-left-circle-fill"
            style="color: #ffd39b; font-size: 24px; margin: 0 16px"
          ></i>
        </router-link>
        <p>내 연습함</p>
      </div>
      <!-- content -->
      <div class="content">
        <div class="contentHead">
          <p class="p1">{{ record.fileName }}</p>
          <p class="p2">
            {{ record.updatedAt.slice(0, 4) }}.{{
              record.updatedAt.slice(5, 7)
            }}.{{ record.updatedAt.slice(8, 10) }}
          </p>
        </div>
        <video autoplay :src="this.record.recordURL" controls></video>
        <div class="sttScript"></div>
      </div>

      <button @click="test()">테스트</button>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "VideoConfirm",
  components: {},
  data() {
    return {
      myrecords: [],
      idx: this.$route.params.videoId,
      record: {},
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
      this.record = { ...data }[this.idx];
      this.issueToken();
      console.log(this.record.recordURL);
      //   this.fetchSubtitles(this.record.recordURL);
    },
  },
  methods: {
    ...mapActions([
      "fetchRecording",
      "fetchSubtitles",
      "createSubtitles",
      "issueToken",
    ]),
    test() {
      this.createSubtitles(this.record.recordURL);
    },
  },
};
</script>

<style scoped>
.total {
  background-color: #fff5f0;
  height: 100vh;
  width: 100vw;
  margin: 0;
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
.contentHead {
  display: flex;
  justify-content: left;
  align-items: baseline;
  margin: 4px 16px;
}
.p1 {
  font-size: 24px;
  font-weight: bold;
  margin: 0 4px;
}
.p2 {
  font-size: 16px;
  margin: 0 4px;
}
video {
  width: 344px;
  height: 344px;
  border: solid gray 1px;
  border-radius: 5px;
  background-color: white;
}
</style>
