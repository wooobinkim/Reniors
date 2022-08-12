<template>
  <div style="min-width:360px">
    <!-- header -->
    <div class="head2">
        <router-link  class="mx-3 rl" :to="{name: 'QuestionList'}">답변 작성</router-link>
        <router-link style="font-weight:bold" class="mx-3 rl" :to="{name: 'VideoPracticeList'}">화상 연습</router-link>
    </div>

    <!-- video -->
    <!-- session & user confirm -->
    <div v-if="!session">
        <div style="margin: auto auto; background-color: #FFF5F0; height: 1000px ;display: flex; justify-content: center;">
        <div>

            <div class="subscript">
                <i class="bi bi-camera-reels" style="color:#FF843E; font-size: 40px; margin: 16px auto; font-weight: bold;"></i>
                <p style="margin: 4px 4px 0 4px"> 이름을 확인하고</p>
                <p style="margin: 4px 4px 0 4px">연습하기 버튼을 눌러주세요 !</p>
            </div>
            <div>
                <p >
                    <label>Name</label>
                    <input v-model="myUserName" class="form-control form1" type="text" required>
                </p>
                <p>
                    <label>Session</label>
                    <input v-model="mySessionId" class="form-control form1" type="text" required>
                </p>
                <p class="text-center">
                    <button class="Btn1" @click="joinSession()">연습하기</button>
                </p>
            </div>
        </div>
        </div>
    </div>

    <!-- go practice -->
    <div id="session" v-if="session" >
        <div style="background-color: #FFF5F0; height: 600px;" >

            <div id="video-container" >
                <div class="videoclass">
                    <user-video :stream-manager="publisher" :isAnswer="isAnswer" @click="updateMainVideoStreamManager(publisher)"/>
                </div>
                <i class="bi bi-x-circle-fill leaveBtn" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session"></i>
                <div v-if="isAnswer" class="questionTag">
                    <p >{{idx + 1}}. {{ question }}</p>
                </div>
                <div v-if="!isAnswer" class="questionTag2">
                    <p >{{idx + 1}}. {{ question }}</p>
                </div>
                <i v-if="!this.isStart" class="bi bi-arrow-left-circle-fill preBtn" @click="preQ"></i>
                <i v-if="!this.isEnd" class="bi bi-arrow-right-circle-fill nextBtn"  @click="nextQ"></i>
            </div>
			<div id="session-header" v-if="isAnswer">
                <answer-script
                    :idx="this.idx"
                    :checklist="this.checklist"
                    :selectedQ="this.selectedQ"
                ></answer-script>

            </div>
			
            <modal-pop 
                v-if="isModal == true"
                :url="url"
            ></modal-pop>

            <!-- footer -->
            <div class="submit">
                <!-- 답변 열기 숨기기 -->
                <div>
                    <button v-if="isAnswer" class="Btn3" style="background-color:#FFD39B" @click="isAnswerFun">답변 숨기기</button>
                    <button v-if="!isAnswer" class="Btn3" style="background-color:#FFD39B" @click="isAnswerFun">답변 보기</button>
                </div>
                <!-- 녹화버튼 -->
                <div>
                    <input v-if="!isRecording" class="Btn3" type="button" id="buttonRecording" @click="startRecording(session)" value="Recording" style="background-color:var(--color-red-2);">
                    <input v-if="isRecording" class="Btn3 " type="button" id="buttonRecording" @click="[stopRecording(nowRecordingId), reactModal()]"  value="Stop" style="background-color:var(--color-red-2);">
                </div>
                
            </div>
        </div>
	</div>

        



  </div>
</template>
<script>
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser';
import { mapActions, mapGetters, mapMutations } from 'vuex';
import UserVideo from '@/components/practice/UserVideo.vue';
import ModalPop from '@/components/practice/ModalPop.vue';
import AnswerScript from '@/components/practice/AnswerScript.vue';


const OPENVIDU_SERVER_URL = "https://" + 'i7b307openvidu.ssafy.io' + ":4443";
const OPENVIDU_SERVER_SECRET = "reniors";

export default{ 
    name:'PracticePage',
    components:{ UserVideo, ModalPop, AnswerScript },
    data(){
        return{
            OV: undefined,
            session: undefined,
            mainStremManager: undefined,
            publisher: undefined,
            subscribers: [],
            mySessionId: 'Session' ,
            myUserName: 'User',

            nowRecordingId: '',
			recodings: [],
			isRecording: false,

            isModal : false,
            url: '',
            idx: 0,
            realquestions: null,
            question: '',
            isAnswer: false,
            selectedQ: [],
            isEnd: false,
            isStart: true,
        };
    },
    watch:{
        questions:function (data) {
            const target = { ...data }
            this.realquestions = target;
            this.selectedQ= {...this.selected}         
            this.question = {...target[this.selectedQ[0]]}.question
            
        },
        idx:function(data){
            this.question = this.realquestions[this.selectedQ[data]].question
        }
    },
    created(){
        this.CLEAR_QUESTIONS
        this.fetchCurrentUser()
        this.fetchQuestions()
        this.fetchChecklist()
        this.mySessionId = 'Session' + this.currentUser.id
        this.myUserName = this.currentUser.name
        
    },
    computed:{
        ...mapGetters(['selected','currentUser', 'questions', 'checklist']),
        ...mapMutations(['CLEAR_QUESTIONS'])
    },
    methods:{
        isAnswerFun(){
            this.isAnswer = !this.isAnswer
        },
        nextQ(){
            // 질문이 두 개 이상이라 다음 버튼이 있음
            if(this.idx+2 in this.selectedQ){
                this.idx += 1
                this.isEnd = false
                this.isStart = false
            } else if(this.idx+1 in this.selectedQ){
                this.idx += 1
                this.isEnd = true
                this.isStart = false
            }
        },
        preQ(){
            if (this.idx-2 in this.selectedQ){
                this.idx -= 1
                this.isStart = false
                this.isEnd = false
            } else if(this.idx-1 in this.selectedQ){
                this.idx -=1
                this.isStart = true
                this.isEnd = false
            }
        },
        ...mapActions(['fetchCurrentUser', 'fetchQuestions', 'fetchChecklist']),
        joinSession() {
            this.OV = new OpenVidu()
            this.session = this.OV.initSession()
            this.session.on('streamCreated', ({stream}) => {
                const subscriber = this.session.subscribe(stream);
                this.subscribers.push(subscriber)
            })
            this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});
            this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
            this.getToken(this.mySessionId).then(token => {
                this.session.connect(token, { clientData: this.myUserName })
                .then(() => {
                    let publisher = this.OV.initPublisher(undefined, {
                        audioSource: undefined, // The source of audio. If undefined default microphone
                        videoSource: undefined, // The source of video. If undefined default webcam
                        publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                        publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                        resolution: '640x480',  // The resolution of your video
                        frameRate: 30,			// The frame rate of your video
                        insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                        mirror: false       	// Whether to mirror your local video or not
					});
                    this.mainStreamManager = publisher;
					this.publisher = publisher;
                    this.session.publish(this.publisher);
                })
                .catch(error => {
                    console.log('There was an error connecting to the session:', error.code, error.message);
                });
            })
            window.addEventListener('beforeunload', this.leaveSession)  
        },

        leaveSession() {
            if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
        },

        updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

        getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

        createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

        createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

        startRecording(session){
			this.isRecording = !this.isRecording
			return new Promise (() => {
				axios
				.post(
					`${OPENVIDU_SERVER_URL}/openvidu/api/recordings/start`,
					JSON.stringify({
						session : session.sessionId,
						// outputMode: "INDIVIDUAL",
						hasAudio: true,
						hasVideo: true
					}),{
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(res => {
					console.log(res)
					this.nowRecordingId = res.data.id })
					
			})
		},

        stopRecording(recodingId){
			this.isRecording = !this.isRecording
			return new Promise(() => {
				axios
				.post(
					`${OPENVIDU_SERVER_URL}/openvidu/api/recordings/stop/${recodingId}`, {},
					{
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(res => res.data)
					.then(data => {
						this.url = data.url
					})
			})
		},

        reactModal(){
            this.isModal = !this.isModal
        }

    },

}
</script>

<style scoped>
.video-container{
    position: absolute;
}
.Btn1{
    background-color: #FF843E;
    border: none;
    border-radius: 10px;
    width: 336px;
    height: 50px;
    color: white;
    font-size: 18px;
    font-weight: bold;
    margin: 16px auto;
}
.leaveBtn{
    color:#9B9B9B; font-size: 32px;
    position: fixed;
    left: 20px;
    top: 60px;
}
.preBtn{
    color:#FF843E; font-size: 32px;
    position: fixed;
    right: 60px;
    top: 60px;
}
.nextBtn{
    color:#FF843E; font-size: 32px;
    position: fixed;
    right: 20px;
    top: 60px;
}
.questionTag{
    display: flex;
    align-content: center;
    position: fixed;
    top:400px;
    left: 16px;
    width: 328px;
    height: 35px;
    margin: auto auto;
    background-color: white;
    opacity: 0.8;
    border: solid 1px #FF843E;
    box-shadow: 1px 1px 1px gray;
    border-radius: 10px;
}
.questionTag p{
    margin: auto 8px;
    color: black;
    font-size: 14px;
    font-weight: bold;
}
.questionTag2{
    display: flex;
    align-content: center;
    position: fixed;
    bottom: 200px;
    left: 16px;
    width: 328px;
    height: 35px;
    margin: auto auto;
    background-color: white;
    opacity: 0.8;
    border: solid 1px #FF843E;
    box-shadow: 1px 1px 1px gray;
    border-radius: 10px;
}
.questionTag2 p{
    margin: auto 8px;
    color: black;
    font-size: 14px;
    font-weight: bold;
}
.videoclass{
    /* position:absolute; */
    /* z-index: 1; */
    left: 8px;
    display: flex;
    justify-content: center;
}
.subscript{
    font-size: 16px;
    font-weight: bold;
    margin: 100px auto 0 auto;
}

.form1{
    width: 336px;
    margin: auto ;
}

.rl{
    text-decoration:none;
    color: white;
}
.rl:hover{
    font-weight: bold;
}

.head2{
    background-color: #FF843E;
    height: 48px;
    display: flex;
    align-items: center;
    justify-content: space-around;
}

.head2 a.router-link-exact-active{
    font-weight: bold;
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
.Btn3{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
    width: 160px;
    margin: 8px 0;
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 16px;

    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
}

</style>