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
    subtitlesId: {},
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
    subtitlesId: (state) => state.subtitlesId,
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
    SET_SELECTED: (state, selected) => {
      state.selected = selected;
    },
    PUT_SELECTED: (state, selected) => state.selected.push(selected),
    SET_VITOTOKEN: (state, token) => (state.vitotoken = token),
    SET_SUBTITLESID: (state, id) => (state.subtitlesId = id),
    SET_SUBTITLES: (state, sub) => (state.subtitles = sub),
  },

  actions: {
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

    saveRecording({ getters }, { fileName, URL }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/recording",
        method: "post",
        data: JSON.stringify({
          fileName: fileName,
          recordURL: URL,
        }),
        headers: getters.authHeader,
      }).then(router.push({ name: "PracticeBox" }));
    },
    fetchRecording({ getters, commit }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/recording/list",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_RECORDS", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    issueToken({ commit, state }) {
      console.log("issueToken 들어옴?");
      axios({
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
          console.log("token", res.data.access_token);
          commit("SET_VITOTOKEN", res.data.access_token);
          console.log(state.vitotoken);
        })
        .catch((err) => console.error(err.response));
    },

    async createSubtitles({ commit, state }, myfile) {
      console.log("createSubtitles 들어옴?");
      console.log(myfile);
      // await dispatch("issueToken");
      const fd = new FormData();

      const config = {
        diarization: {
          use_ars: false,
          use_verification: false,
          user_id: null,
          partner_id: null,
        },
        use_multi_channel: false,
        use_itn: false,
        use_disfluency_filter: false,
        use_profanity_filter: false,
        paragraph_splitter: {
          min: 10,
          max: 500,
        },
      };
      fd.append("config", config);
      fd.append("file", myfile);
      console.log(fd);
      axios({
        url:
          "https://cors-anywhere.herokuapp.com/" +
          "https://openapi.vito.ai/v1/transcribe",
        method: "post",
        data: fd,
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${state.vitotoken}`,
          withCredentials: true,
        },
      })
        .then((res) => {
          console.log("createSubtitles 결과");
          commit("SET_SUBTITLESID", res.data.id);
        })
        .catch((err) => {
          console.log("error");
          console.error(err);
        });
    },

    async fetchSubtitles({ commit, getters, state }, myfile) {
      console.log("fetchSubtitles 들어옴?");
      console.log(myfile);
      // await dispatch("createSubtitles", myfile);
      const id = getters.subtitlesId;
      axios({
        url: "https://openapi.vito.ai/v1/transcribe/" + `${id}`,
        method: "get",
        headers: {
          Authorization: `Bearer ${state.vitotoken}`,
        },
      })
        .then((res) => {
          console.log("fetchSubtitles 결과");
          commit("SET_SUBTITLES", res.data);
        })
        .catch((err) => console.error(err.response));
    },
  },
};
