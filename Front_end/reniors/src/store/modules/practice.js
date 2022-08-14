import axios from "axios";
import router from "@/router";
import qs from "qs";

export default {
  state: {
    questions: [],
    answer: {},
    checklist: [],
    records: [],
    selected: [],
    vitotoken: {},
    vitoId: {},
    subtitles: {},
  },

  getters: {
    questions: (state) => state.questions,
    answer: (state) => state.answer,
    checklist: (state) => {
      var list = [];
      for (let index = 0; index < state.checklist.length; index++) {
        list.push(state.checklist[index].questionId);
      }
      console.log(list.sort());
      return list.sort();
    },
    records: (state) => state.records,
    selected: (state) => state.selected,
    vitotoken: (state) => state.vitotoken,
    vitoId: (state) => state.vitoId,
    subtitles: (state) => state.subtitles,
  },

  mutations: {
    CLEAR_QUESTIONS: (state) => {
      state.questions = null;
    },
    SET_QUESTIONS: (state, questions) => {
      state.questions = questions;
    },
    SET_ANSWER: (state, answer) => (state.answer = answer),
    CLEAR_ANSWER: (state) => (state.answer = ""),
    SET_CHECKLIST: (state, checklist) => (state.checklist = checklist),
    SET_RECORDS: (state, records) => (state.records = records),
    CLEAR_SELECTED: (state, data) => {
      console.log('??');
      state.selected = data},
    SET_SELECTED: (state, selected) => {
      state.selected = selected;
    },
    PUT_SELECTED: (state, selected) => state.selected.push(selected),
    SET_VITOTOKEN: (state, token) => (state.vitotoken = token),
    SET_VITOID: (state, id) => (state.vitoId = id),
    SET_SUBTITLES: (state, sub) => (state.subtitles = sub),
  },

  actions: {
    clearSelected({commit}){
      commit("CLEAR_SELECTED", [])
    },
    fetchQuestions({ getters, commit }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api" + "/questions",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_QUESTIONS", res.data);
        })
        .catch((err) => console.error(err.response));
    },
    createAnswer({ getters, commit }, { questionId, content }) {
      axios({
        url:
          "https://i7b307.p.ssafy.io/api" +
          "/questions" +
          `/${questionId}` +
          "/answers",
        method: "post",
        data: JSON.stringify({ answer: content }),
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_ANSWER", res.data);
        router.push({ name: "QuestionList" });
      });
    },
    async fetchAnswer({ getters, commit }, questionId) {
      await axios({
        url:
          "https://i7b307.p.ssafy.io/api" +
          "/questions" +
          `/${questionId}` +
          "/answers",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_ANSWER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateAnswer({ getters, commit }, { questionId, content }) {
      axios({
        url:
          "https://i7b307.p.ssafy.io/api" +
          "/questions" +
          `/${questionId}` +
          "/answers",
        method: "put",
        data: JSON.stringify({ answer: content }),
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_ANSWER", res.data);
        router.push({ name: "QuestionList" });
      });
    },
    clearAnswer({ commit }) {
      commit("CLEAR_ANSWER");
    },
    fetchChecklist({ getters, commit }) {
      axios({
        url:
          "https://i7b307.p.ssafy.io/api" + "/questions" + "/answers" + "/list",
        method: "get",
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_CHECKLIST", res.data);
      });
    },

    pushSelected({ commit, getters }, questionId) {
      if (getters.selected.includes(questionId)) {
        let filtered = getters.selected.filter((el) => el !== questionId);
        commit("SET_SELECTED", filtered);
      } else {
        commit("PUT_SELECTED", questionId);
      }
    },

    async saveRecording({ getters, dispatch}, { fileName, URL }) {
      let reId = ''
      axios({
        url: "https://i7b307.p.ssafy.io/api/recording",
        method: "post",
        data: JSON.stringify({
          fileName: fileName,
          recordURL: URL,
        }),
        headers: getters.authHeader,
      })
      .then((res)=>{
        console.log('여기여기', res);
        reId = res.data.recordingId
        console.log(reId)
        return reId
      })
        .then((res) => {dispatch("fetchvitoId", { videoUrl: URL, recordingId:res });})
        // .then((res) =>{
        //   dispatch("putVitoId", res)

        // }
        .then(dispatch("fetchSubtitles"))
        .then(dispatch("fetchRecording"))
        .then(router.push({ name: "PracticeBox" }));
    },
    async fetchRecording({ getters, commit }) {
      console.log('4.fetchRecordinglist');
      await axios({
        url: "https://i7b307.p.ssafy.io/api/recording/list",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_RECORDS", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    async issueToken({ commit }) {
      await axios({
        url: "https://openapi.vito.ai/v1/authenticate",
        method: "post",
        data: qs.stringify({
          client_id: "3-_Raz62TK8EPV5A6-u2",
          client_secret: "aKaPD8V9F2TS3rCghHQgDodSsqVgWbWxPREBvd1R",
        }),
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
        .then((res) => {
          console.log(res.data.access_token);
          commit("SET_VITOTOKEN", res.data.access_token);
        })
        .catch((err) => console.error(err.response));
    },

    async fetchvitoId({ commit, state, getters, dispatch }, {videoUrl, recordingId}) {
      console.log('1fetchvitoId', videoUrl);
      await axios({
        url: "https://i7b307.p.ssafy.io/api/vito/videoId",
        method: "post",
        data: JSON.stringify({ token: state.vitotoken, videoUrl: videoUrl }),
        headers: getters.authHeader,
      })
        .then((res) => {
          console.log("vitoid", res.data);
          commit("SET_VITOID", res.data);
        })
        .then(dispatch("putVitoId", recordingId))
        .catch((err) => console.error(err));
    },

    async fetchSubtitles({ commit, getters, state }) {
      console.log('3.fetchSub');
      await axios({
        url: "https://i7b307.p.ssafy.io/api/vito/audioMsg",
        method: "post",
        data: JSON.stringify({
          token: state.vitotoken,
          videoId: state.vitoId.id,
        }),
        headers: getters.authHeader,
      }).then((res) => {
        console.log(res.data);
        commit("SET_SUBTITLES", res.data);
      });
    },
    async fetchSubtitles2({ commit, getters, state }, videoId) {
      await axios({
        url: "https://i7b307.p.ssafy.io/api/vito/audioMsg",
        method: "post",
        data: JSON.stringify({
          token: state.vitotoken,
          videoId: videoId,
        }),
        headers: getters.authHeader,
      }).then((res) => {
        console.log(res.data);
        console.log(res.data.results.utterances);
        commit("SET_SUBTITLES", res.data.results.utterances);
      });
    },

    async putVitoId({ getters, state }, recordingId) {
      await axios({
        url:
          "https://i7b307.p.ssafy.io/api/recording/" +
          `${recordingId}` +
          "/videoId",
        method: "put",
        headers: getters.authHeader,
        data: JSON.stringify({
          videoId: {...state.vitoId}.id,
        }),
      }).then(console.log("put", state.vitoId));
    },

    // async createSubtitles({ commit, state }, myfile) {
    //   console.log("createSubtitles 들어옴?");
    //   console.log(myfile);
    //   // await dispatch("issueToken");
    //   const fd = new FormData();

    //   const config = {
    //     diarization: {
    //       use_ars: false,
    //       use_verification: false,
    //       user_id: null,
    //       partner_id: null,
    //     },
    //     use_multi_channel: false,
    //     use_itn: false,
    //     use_disfluency_filter: false,
    //     use_profanity_filter: false,
    //     paragraph_splitter: {
    //       min: 10,
    //       max: 500,
    //     },
    //   };
    //   fd.append("config", config);
    //   fd.append("file", myfile);
    //   console.log(fd);
    //   axios({
    //     url:
    //       "https://cors-anywhere.herokuapp.com/" +
    //       "https://openapi.vito.ai/v1/transcribe",
    //     method: "post",
    //     data: fd,
    //     headers: {
    //       "Content-Type": "multipart/form-data",
    //       Authorization: `Bearer ${state.vitotoken}`,
    //       withCredentials: true,
    //     },
    //   })
    //     .then((res) => {
    //       console.log("createSubtitles 결과");
    //       commit("SET_SUBTITLESID", res.data.id);
    //     })
    //     .catch((err) => {
    //       console.log("error");
    //       console.error(err);
    //     });
    // },

    // async fetchSubtitles({ commit, getters, state }, myfile) {
    //   console.log("fetchSubtitles 들어옴?");
    //   console.log(myfile);
    //   // await dispatch("createSubtitles", myfile);
    //   const id = getters.subtitlesId;
    //   axios({
    //     url: "https://openapi.vito.ai/v1/transcribe/" + `${id}`,
    //     method: "get",
    //     headers: {
    //       Authorization: `Bearer ${state.vitotoken}`,
    //     },
    //   })
    //     .then((res) => {
    //       console.log("fetchSubtitles 결과");
    //       commit("SET_SUBTITLES", res.data);
    //     })
    //     .catch((err) => console.error(err.response));
    // },
  },
};
