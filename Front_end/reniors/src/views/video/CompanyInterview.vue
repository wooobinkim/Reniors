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

    <div id="session" v-if="session" class="insession row">
      <!-- <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
      </div>
     <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div> -->
      <div class="col-6">
        <div id="video-container" class="col-md-6">
          <user-video
            :stream-manager="publisher"
            @click="updateMainVideoStreamManager(publisher)"
            class="myvideo"
          />
          <user-video
            v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click="updateMainVideoStreamManager(sub)"
            class="myvideo"
          />
        </div>
      </div>

<div class="col-6" style="margin:0; padding:0;">
      <div class="userSTT" v-if="!chatopenclose"></div>

      <div class="chatbox" v-if="chatopenclose">

        <template v-for="msg in receivemsg" :key="msg">
          <div class="chatlist">{{msg}}</div>
        </template>

        <div class="chatform">
          <p style="width: 1vw">  </p>
          <input class="chatinput" @keyup.enter="sendchat()" type="text" v-model="sendmsg" />
          <button class="chatsubmit" @click="sendchat()">보내기</button>
        </div>

      </div>

      <div class="rightbtn">
        <button @click="chatopen()" class="chatbtn"><i class="bi bi-chat-dots-fill"></i></button>
        <template v-if="audioflag">
          <button @click="audioonoff()"  class="videobtn"><i class="bi bi-mic"></i></button>
        </template>
        <template v-if="!audioflag">
          <button @click="audioonoff()"  class="videobtn"><i class="bi bi-mic-mute"></i></button>
        </template>
        <template v-if="videoflag">
          <button @click="videoonoff()"  class="videobtn"><i class="bi bi-camera-video"></i></button>
        </template>
        <template v-if="!videoflag">
          <button @click="videoonoff()"  class="videobtn"><i class="bi bi-camera-video-off"></i></button>
        </template>
      </div>

      <div>지원자 : {{ interviewer }}</div>
      <template v-if="tab == 'resume'">
        <div>
          <resume-view :applyinfo="this.applyinfo"></resume-view>
        </div>
      </template>
      <template v-if="tab == 'eval'">
        <div>
          <openvidu-eval-list :applyinfo="this.applyinfo"></openvidu-eval-list>
        </div>
      </template>
      <div>
        <span @click="changeresume()">이력서보기 | </span>
        <span @click="changeeval()">평가하기</span>
      </div>
      
      <button @click="leaveSession" class="leavebtn">
                <span><i class="bi bi-box-arrow-right"></i> 퇴장</span>
            </button>
</div>
      <!-- <input
        class="btn btn-large btn-danger"
        type="button"
        id="buttonLeaveSession"
        @click="leaveSession"
        value="Leave session"
      /> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/UserVideo.vue";
import ResumeView from "@/components/Company/interview/ResumeView.vue";
import { mapActions, mapGetters,mapState } from "vuex";
import OpenviduEvalList from "@/components/Company/interview/OpenviduEvalList.vue";
// import OpenViduChat from "@/components/Company/interview/OpenViduChat.vue";
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
    // OpenViduChat,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "InterviewSession" + this.$route.params.jobOpeningId,
      myUserName: "",
      videoflag: true,
      audioflag: false,
      tab: "resume",
      sendmsg: "",
      receivemsg: [],
      msgflag: true,
      chatopenclose: false,
      applyinfo:null,
      sessionleave:false,
      //   myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  computed: {
    ...mapGetters("company", ["companyinfo","interviewer"]),
    ...mapState("company",["apply"])
  },
  watch: {
    companyinfo: function (data) {
      this.myUserName = data.name;
    },
    session: function () {
      if(!this.sessionleave){
      this.session.on("signal", (event) => {
          let name = event.from.data;
          name = name.substr(15);
          name = name.substring(0,name.length-2);
        this.receivemsg.push(name +" : "+ event.data);
        console.log(this.receivemsg);
        });
      }
    },
    apply:function (data) {
      console.log(data);
      this.applyinfo = data;
    },
    // msgflag:function () {
    //   console.log("여기안와요..?");
    //   this.session.on("signal", (event) => {
    //       let name = event.from.data;
    //       name = name.substr(15);
    //       name = name.substring(0,name.length-2);
    //     this.receivemsg.push(name +" : "+ event.data);
    //     console.log(this.receivemsg);
    //     });
    // }
  },
  created() {
    this.getCompany();
    this.getapply({jobOpeningId:this.$route.params.jobOpeningId,
    applyId:this.$route.params.no})
  },
  methods: {
    ...mapActions("company", ["getCompany","getapply"]),
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
      this.sessionleave = true;
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
#main-container{
    min-height: 100vh;
    min-width: 100vw;
    background-color: #FFF5F0;
    display: flex;
    align-items: center;
    justify-content: center;

}
.join{
    width: 80vw;
    height: 80vh;
    border-radius: 20px;
    border: none;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    background: linear-gradient(90deg,  white 50%, #FF843E 50%);

}
.header-logo {
  width: 50%;
  height: auto;
  margin: 0 32px;
}
.header-logo p{
    font-weight: bold;
    font-size: 24px;
    width: 400px;
    text-align: left;
    margin:0 32px;
    
}
.tips{
    width: 500px;
    height: 200px;
    border: none;
    border-radius: 10px;
    background-color:#FFF5F0;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
    margin: 16px 0; 
    display: flex;
    align-items: center;
}
.tips p{
    width: 450px;
    font-size: 14px;
    margin: 0 0 2px 28px;
}
.lefttop{
    display: flex;
    align-items: center;
    /* justify-content: center; */
    padding: 64px
}
.left{
    display: flex;
    align-items: center;
}
.righttop{
    display: flex;
    align-items: center;
    padding: 64px;
    justify-content: center;
}
.right{
    display: flex;
    align-items: center;
    justify-content: center;
}
.rightinput{
    margin: auto;
    display: block;
    width: 480px;
    height: 48px;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.375rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.label{
    text-align: left;
    font-size: 20px;
    color:white;
    margin: 0 12px;
    font-weight: bold;

}
.submitBtn{
    margin: 8px;
}
.submitBtn button{
    width: 480px;
    height: 48px;
    background-color: #FFB400;
    opacity:0.7;
    color: white;
    font-size: 24px;
    font-weight: bold;
    border: none;
    border-radius: 5px;
    margin: 8px 0;
}
.submitBtn button:hover{
    background-color: #FFB400;
    opacity: 1;
}

.header-logo > img {
  height: 100px;
  width: 400px;
  object-fit: cover;
  margin: 16px 0;
}

/* IN session */

.insession{
    width: 80vw;
    height: 80vh;
}
.myvideo{
    width: 35vw;
    height: 38vh;
    background-color: rgba(100, 100, 111, 0.2);
    border-radius: 5px;
    border: none;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    margin: 16px 0;
}
.userSTT{
    width: 30vw;
    height: 65vh;
    border-radius: 10px;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    margin: 16px 0;

}
.chatbox{
    width: 30vw;
    height: 65vh;
    border-radius: 10px;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    margin: 16px 0;
}
.chatlist{
    width: 30vw;
    height: 59vh;
    border: none;
}
.chatlist textarea{
    width: 28vw;
    height: 56vh;
    border: none;
    margin: 1vh 1vw;
}
.chatform{
    width: 29vw;
    height: 5vh;
    border: none;
    border-radius: 30px;
    box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
    background-color: #EAEAEA;
    margin: 4px;
    display: flex;
    align-items: center;
}
.chatinput{
    width: 24vw;
    height: 4vh;
    border: none;
    margin: 0 8px 0 0;
    background-color: #EAEAEA;
    border-radius: 20px;
}
.chatinput:hover{
    border: none;
}
.chatsubmit{
    width: 2vw;
    height: 2vw;
    border: none;
    border-radius: 20px;
    margin: 2px 2px 0 2px;
    background-color: #EAEAEA;
    display: flex;
    align-items: center;
}
.chatsubmit > i{
    font-size: 28px;
    transform: rotate(45deg);
    margin: 0;
}

.rightbtn{
    width: 30vw
}
.chatbtn{
    width: 4vw;
    height: 4vw;
    border-radius: 100%;
    border: none;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    margin: 6px;
}
.chatbtn i{
    font-size: 32px;
    color: #8CD6C1;
    font-weight: bold;
    
}
.videobtn{
    width: 4vw;
    height: 4vw;
    border-radius: 100%;
    border: none;
    background-color: #8CD6C1;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    margin: 6px;
}
.videobtn i{
    font-size: 32px;
    color: white;
    font-weight: bold;
    
}
.leavebtn{
    width: 8vw;
    height: 4vw;
    border-radius: 30px;
    border: none;
    background-color: #F3620F;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    margin: 6px;
    
}
.leavebtn i{
    font-size: 32px;
    color: white;
    font-weight: bold; 
}
.leavebtn span{
    font-size: 24px;
    color: white;
}
</style>
