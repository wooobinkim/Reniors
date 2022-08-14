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
        <video :class="{smallVideo:isShow}" autoplay :src="this.record.recordURL" controls></video>
      </div>
      <!-- 발음확인 창 -->
      <div class="sttTotal">
        <div class="sttScript" v-if="isShow">
          <p v-for="(subtitle,index) in subtitles" :key="index" style="margin:2px; text-align: left;">
            {{subtitle.msg}}
          </p>
        </div>
      </div>

      <!-- footer -->
      <div class="submit">
        <div>
          <button v-if="!isShow" class="showBtn" @click="isShowFunc">발음 확인하기</button>
          <button v-if="isShow" class="showBtn" @click="isShowFunc">발음 확인 닫기</button>
        </div>
      </div>

      
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
      isShow: false,

    };
  },
  computed: {
    ...mapGetters(["records", "vitotoken", "subtitles"]),
  },
  created() {
    this.fetchRecording();
    
  },
  watch: {
    records: function (data) {
      this.myrecords = { ...data };
      this.record = { ...data }[this.idx];
      this.issueToken();
    },
    vitotoken: function(){
      this.fetchSubtitles2(this.record.videoId)
    }
  },
  methods: {
    ...mapActions([
      "fetchRecording",
      "fetchSubtitles2",
      "issueToken",
    ]),
    isShowFunc(){
      this.isShow = !this.isShow
    }

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
.sttTotal{
  display: flex;
  justify-content: center;
  animation: fadeInUp 1s;
}
@keyframes fadeInUp{
    from{
        opacity: 0;
        transform: translateY(20px);
    }
    to{
        opacity: 1;
        transform: none;
    }
}
.sttScript{
  background-color: white;
    overflow: scroll;
    padding: 4px;
    /* position: fixed; */
    bottom: 120px;
    height: 150px;
    width: 344px;
    border: solid #D9D9D9 1px;
    border-radius: 5px;
}
.submit{
    position: fixed;
    bottom: 50px;
    width: 100%;
    margin: 0;
    background-color: white;
    border-top: solid 0.5px #FFEDBF;
    display: flex;
    justify-content: space-around;

}
.showBtn{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
    width: 320px;
    margin: 8px 0;
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 16px;
    background-color: #ff843e;

    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
}
.smallVideo{
	background-color: white;
	width: 344px;
	height: 400px;
	margin: 8px auto 4px auto;
	border: solid #FFD39B 1px;
	border-radius: 5px;
}
video{
	background-color: white;
	width: 344px;
	height: 520px;
	margin: 8px auto 4px auto;
	border: solid #FFD39B 1px;
	border-radius: 5px;
}
</style>
