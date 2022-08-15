<template>
  <div id="main-container" >
    <div id="join" v-if="!session" class="join row">
      <!-- left -->
        <div class="col-6 lefttop">
            <div class="left">
                <div class="header-logo">
                  <img src="@/assets/logo.png" />
                  <p><span style="color:#37BF99">{{companyName}}</span>의 면접입니다.</p>
                  <br>
                  <div class="tips">
                    <div>
                        <p style="font-size:16px; margin:8px 24px;">🙂화상면접 Tips🙂</p>
                        <p>1. 카메라 위치 및 조명을 조정해보세요:) </p>
                        <p>2. 깔끔한 배경과 조용한 공간이 바람직합니다:) </p>
                        <p>3. 카메라를 집중력있게 응시한다면 자신감을 충분히 전달할 수 있어요:)</p>
                        <p>4. 깔끔한 복장은 좋은 인상을 주는데 도움이 됩니다:)</p>
                        <p>5. <span style="color: #FF843E">리니어즈</span>의 화상면접 연습을 활용해보세요:)</p>
                    </div>
                  </div>
                </div>
            </div>
        </div>

    <!-- right -->
        <div class="righttop col-6">
            <div class="right">
                <div class="fomrs">
                    <div style="margin: 16px 0;">
                        <p class="label">지원자명</p>
                        <input
                            v-model="myUserName"
                            class="rightinput"
                            type="text"
                            required
                            readonly
                        />
                    </div>
                    <div style="margin: 16px 0; ">
                        <p class="label">면접방 번호</p>
                        <input
                            v-model="mySessionId"
                            class="rightinput"
                            type="text"
                            required
                            readonly
                        />
                    </div>
                    <div class="submitBtn">
                        <button @click="joinSession()">면접방 들어가기</button>
                    </div>
                </div>
            </div>
        </div>
      
    </div>

    <div id="session" v-if="session" class="insession row">
        <!-- left -->
        <div class="col-6">
            <div id="video-container" >
                <user-video
                :stream-manager="publisher"
                @click="updateMainVideoStreamManager(publisher)"
                class="myvideo"
                />
            </div>
            <div>
                <user-video
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                @click="updateMainVideoStreamManager(sub)"
                class="myvideo"
                />
            </div>
      
      </div>

      <!-- right -->
      <div class="col-6" style="margin:0; padding:0;">
        <div class="userSTT" v-if="!chatopenclose">

        </div>
        <div class="chatbox" v-if="chatopenclose">
            <template v-for="msg in receivemsg" :key="msg">
          <div>{{msg}}</div>
        </template>

            <!-- <div class="chatlist"><textarea v-model="receivemsg" /></div> -->
            <div class="chatform">
                <p style="width: 1vw">  </p>
                <input class="chatinput" @keyup.enter="sendchat()" type="text" v-model="sendmsg" />
                <button class="chatsubmit" @click="sendchat()"><i class="bi bi-send"></i></button>    

            </div>
        </div>

        <div class="rightbtn">
            <button @click="chatopen()" class="chatbtn"><i class="bi bi-chat-dots-fill"></i></button>
            <template v-if="audioflag">
                <button @click="audioonoff()" class="videobtn"><i class="bi bi-mic"></i></button>
            </template>
            <template v-if="!audioflag">
                <button @click="audioonoff()" class="videobtn"><i class="bi bi-mic-mute"></i></button>
            </template>
            <template v-if="videoflag">
                <button @click="videoonoff()" class="videobtn"><i class="bi bi-camera-video"></i></button>
            </template>
            <template v-if="!videoflag">
                <button @click="videoonoff()" class="videobtn"><i class="bi bi-camera-video-off"></i></button>
            </template>
            
            
            
            <button @click="leaveSession" class="leavebtn">
                <span><i class="bi bi-box-arrow-right"></i> 퇴장</span>
            </button>
        </div>
      </div>
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
  name: "UserInterview",
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
      receivemsg: [],
      msgflag: true,
      chatopenclose: false,
      companyName: '',
      sessionleave:false,

      tips:[
        "카메라 위치 및 조명을 조정하면 더 좋습니다!",  
        "깔끔한 배경과 조용한 공간이 바람직합니다:)", 
        "카메라를 집중력있게 응시한다면 자신감을 충분히 전달할 수 있어요:)",
        "깔끔한 복장은 좋은 인상을 주는데 도움이 됩니다:)",
        ],
      
    
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
      if(!this.sessionleave){
      this.session.on("signal", (event) => {
          let name = event.from.data;
          name = name.substr(15);
          name = name.substring(0,name.length-2);
        this.receivemsg.push(name +" : "+ event.data);
        });
      }
    },
  },
  created() {
        this.fetchCurrentUser(),
        this.myUserName = this.currentUser.name
        this.fetchRooms(),
        this.mySessionId = this.rooms[0].sessionId
        this.companyName = this.rooms[0].companyName

  },
  methods: {
    ...mapActions(['fetchCurrentUser', 'fetchRooms']),
    getTip(){
        
    },
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
      this.sessionleave = true;
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