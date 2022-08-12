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

      </div>

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
import { mapActions, mapGetters} from "vuex";
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
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: '',
      myUserName: "",
      videoflag: true,
      audioflag: false,
      sendmsg: "",
      receivemsg: "",
      msgflag: true,
      chatopenclose: false,

    };
  },
  computed: {
    ...mapGetters(['currentUser', 'rooms'])
    // ...mapState("company", ["companyinfo", "interviewer"]),
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
        this.fetchCurrentUser(),
        this.myUserName = this.currentUser.name
        this.fetchRooms(),
        this.mySessionId = this.rooms[0].sessionId

  },
  methods: {
    ...mapActions(['fetchCurrentUser', 'fetchRooms']),
    videoonoff() {
      this.videoflag = !this.videoflag;
      this.publisher.publishVideo(this.videoflag);

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
      this.OV = new OpenVidu();


      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

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


    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

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