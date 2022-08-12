<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="img-div" class="header-logo">
        <img src="@/assets/logo.png" />
      </div>
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>화상면접을 진행합니다.</h1>
        <div class="form-group">
          <p>
            <label>참가자명</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
              readonly
            />
          </p>
          <p>
            <label>세션번호</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
              readonly
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              면접방 들어가기
            </button>
          </p>
        </div>
      </div>
    </div>

    <div id="session" v-if="session">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <!-- <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        /> -->
      </div>
      <!-- <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div> -->
      <div id="video-container" class="col-md-6">
        <user-video
          :stream-manager="publisher"
          @click="updateMainVideoStreamManager(publisher)"
        />
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)"
        />
      </div>
      <template v-if="videoflag">
        <button @click="videoonoff()">비디오끄기</button>
      </template>
      <template v-if="!videoflag">
        <button @click="videoonoff()">비디오켜기</button>
      </template>
      <template v-if="audioflag">
        <button @click="audioonoff()">마이크끄기</button>
      </template>
      <template v-if="!audioflag">
        <button @click="audioonoff()">마이크켜기</button>
      </template>
      <button @click="chatopen()">채팅</button>

      <div>지원자 : {{ interviewer }}</div>
      <template v-if="tab == 'resume'">
        <div>
          <resume-view></resume-view>
        </div>
      </template>
      <template v-if="tab == 'eval'">
        <div>
          <openvidu-eval-list></openvidu-eval-list>
        </div>
      </template>
      <div>
        <span @click="changeresume()">이력서보기 | </span>
        <span @click="changeeval()">평가하기</span>
      </div>
      <div v-if="chatopenclose">
        <input type="text" v-model="msg" />
        <button @click="sendchat()">보내기</button>
        <div><textarea v-model="receivemsg" /></div>
      </div>
      <input
        class="btn btn-large btn-danger"
        type="button"
        id="buttonLeaveSession"
        @click="leaveSession"
        value="Leave session"
      />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/UserVideo.vue";
import ResumeView from "@/components/Company/interview/ResumeView.vue";
import { mapActions, mapGetters } from "vuex";
import OpenviduEvalList from "@/components/Company/interview/OpenviduEvalList.vue";
// import { mapActions } from "vuex";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + "i7b307openvidu.ssafy.io" + ":4443";
// const OPENVIDU_SERVER_URL2 = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "reniors";

export default {
  name: "App",
  props: {
    userId: Number,
    jobOpeningId: Number,
  },

  components: {
    UserVideo,
    OpenviduEvalList,
    ResumeView,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "InterviewSession" + this.$route.params.no,
      myUserName: "",
      videoflag: true,
      audioflag: false,
      tab: "resume",
      sendmsg: "",
      receivemsg: "",
      msgflag: true,
      chatopenclose: false,
      //   myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  computed: {
    ...mapGetters("company", ["companyinfo", "interviewer"]),
  },
  watch: {
    companyinfo: function (data) {
      this.myUserName = data.name;
    },
    session: function () {
      this.session.on("signal", (event) => {
        this.receivemsg += event.from + event.data + "\n";
      });
    },
  },
  created() {
    this.getCompany();
  },
  methods: {
    ...mapActions("company", ["getCompany"]),
    changeresume() {
      this.tab = "resume";
    },
    changeeval() {
      this.tab = "eval";
    },
    videoonoff() {
      this.videoflag = !this.videoflag;
      this.publisher.publishVideo(this.videoflag);
      // if (this.videoflag) {
      //   this.session.publishVideo(this.publisher);
      // } else {
      //   this.session.unpublishVideo(this.publisher);
      // }
    },
    sendchat() {
      this.session
        .signal({
          data: this.sendmsg,
          to: [],
          type: "my-chat",
        })
        .then(() => {
          this.msgflag = !this.msgflag;
          this.sendmsg = "";
        })
        .catch((error) => {
          console.log(error);
        });
    },
    chatopen() {
      this.chatopenclose = !this.chatopenclose;
    },
    audioonoff() {
      this.audioflag = !this.audioflag;
      this.publisher.publishAudio(this.audioflag);
    },
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: false, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
};
</script>
<style>
.header-logo {
  width: 80%;
  height: 120px;
}

.header-logo > img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}
</style>
