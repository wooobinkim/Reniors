import http from "@/api/http";
import multipart from "@/api/multipart";
import router from "@/router";
import axios from "axios";
// import jobopening from "./jobopening";

export default {
  namespaced: true,
  state: {
    token: localStorage.getItem("token") || "",
    currentUser: {},
    profile: {},
    authError: null,
    datastate: "",
    jobopeninglisting: [],
    jobopeninglisted: [],
    jobopening: null,
    companyinfo: {},
    applylist: [],
    interviewapplylist: [],
    interviewapplylistasc: [],
    applyuser: null,
    evalquestionlist: [],
    userevallist: [],
    interviewer: null,
    resume: null,
  },

  getters: {
    isLogginedIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
    profile: (state) => state.profile,
    authError: (state) => state.authError,
    // Authorization: `Token ${state.token}`
    authHeader: (state) => ({ Authorization: `Bearer ${state.token}` }),
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),
    SET_DATASTATE(state, data) {
      state.datastate = data;
    },
    CLEAR_JOBOPENING_LIST(state) {
      state.jobopeninglisting = [];
      state.jobopeninglisted = [];
    },
    SET_JOBOPENING_LIST(state, datas) {
      datas.forEach((data) => {
        if (data.isFinish == "F") {
          state.jobopeninglisting.push(data);
        } else {
          state.jobopeninglisted.push(data);
        }
      });
    },
    SET_JOBOPENING(state, data) {
      state.jobopening = data;
    },
    SET_COMPANY(state, data) {
      state.companyinfo = data;
    },
    SET_APPLY_LIST(state, datas) {
      state.applylist = datas;
    },
    SET_APPLY_USER(state, data) {
      state.applyuser = data;
    },
    CLEAR_INTERVIEW_APPLY_LIST(state) {
      state.interviewapplylist = [];
    },
    SET_INTERVIEW_APPLY_LIST(state, datas) {
      datas.forEach((data) => {
        if (
          data.jobOpeningProcess == "면접" ||
          data.jobOpeningProcess == "면접심사중"
        ) {
          state.interviewapplylist.push(data);
        }
      });
      // state.interviewapplylist = datas;
    },
    CLEAR_INTERVIEW_APPLY_LIST_ASC(state) {
      state.interviewapplylistasc = [];
    },
    SET_INTERVIEW_APPLY_LIST_ASC(state, datas) {
      datas.forEach((data) => {
        if (
          data.jobOpeningProcess == "면접" ||
          data.jobOpeningProcess == "면접심사중"
        ) {
          state.interviewapplylistasc.push(data);
        }
      });
      // state.interviewapplylist = datas;
    },
    SET_EVAL_QUESTION_LIST(state, datas) {
      state.evalquestionlist = datas;
    },
    SET_USER_EVAL_LIST(state, datas) {
      state.userevallist = datas;
    },
    CLEAR_INTERVIEWER(state) {
      state.interviewer = null;
    },
    SET_INTERVIEWER_LIST(state, data) {
      state.interviewer = data;
    },
    SET_RESUME(state, data) {
      state.resume = data;
    },
  },

  actions: {
    saveToken({ commit }, token) {
      commit("SET_TOKEN", token);
      localStorage.setItem("token", token);
    },

    removeToken({ commit }) {
      commit("SET_TOKEN", "");
      localStorage.setItem("token", "");
    },

    // error 커밋 추가
    companylogin({ dispatch }, credentials) {
      console.log(credentials);
      axios({
        // url 수정

        url: "https://i7b307.p.ssafy.io/api/company/login",
        method: "post",
        data: credentials,
      }).then((res) => {
        const token = res.headers["authorization"];
        dispatch("saveToken", token);
        // dispatch("fetchCurrentUser");
        // router 수정
        router.push({ name: "company" });
      });
      // error 부분 추가
    },

    registCompany: ({ commit }, formData) => {
      // axios({
      //     url:"https://i7b307.p.ssafy.io/api/company",
      //     method : "post",
      //     data : formData,
      //     headers : {
      //         "Content-Type": "multipart/form-data",
      //         // Authorization: "Bearer " + token,
      //     }
      // }).then((res)=>{
      //     console.log(res);
      //     console.log(commit);
      // }).catch((error)=>{
      //     console.log(error);
      // })
      multipart
        .post(`/company`, formData)
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCompany: ({ commit }) => {
      http
        .get(`/company`)
        .then(({ data }) => {
          commit("SET_COMPANY", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateCompany: ({ commit }, company) => {
      http
        .put(`/company`, JSON.stringify(company))
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    registJobOpening: ({ commit }, formData) => {
      multipart
        .post(`/company/jobopening`, formData)
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateJobOpening: ({ commit }, data) => {
      http
        .put(`/company/jobopening/${data.no}`, data.jobopening)
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getJobOpeningList: ({ commit }) => {
      http
        .get(`/company/jobopening`)
        .then(({ data }) => {
          commit("SET_JOBOPENING_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getJobOpening: ({ commit }, no) => {
      http
        .get(`/company/jobopening/${no}`)
        .then(({ data }) => {
          commit("SET_JOBOPENING", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteJobOpening: ({ commit }, no) => {
      http
        .delete(`/company/jobopening/${no}`)
        .then(({ data }) => {
          commit("SET_JOBOPENING", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    finishJobOpening: ({ commit }, no) => {
      http
        .put(`/company/jobopening/${no}/finish`)
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getapplylist: ({ commit }, no) => {
      http
        .get(`/company/jobopening/${no}/apply`)
        .then(({ data }) => {
          commit("SET_APPLY_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getinterviewapplylist: ({ commit }, no) => {
      http
        .get(`/company/jobopening/${no}/apply`)
        .then(({ data }) => {
          commit("SET_INTERVIEW_APPLY_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getinterviewapplylistasc: ({ commit }, no) => {
      http
        .get(`/company/jobopening/${no}/apply/dateAsc`)
        .then(({ data }) => {
          commit("SET_INTERVIEW_APPLY_LIST_ASC", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateApply: ({ commit }, data) => {
      // console.log(data.apply);
      // axios({
      //   // url 수정
      //   url: "http://localhost:8080/api/company/jobopening/1/apply/1",
      //   method: "put",
      //   data: data.apply,
      //   headers:{
      //     Authorization: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyLGNvbXBhbnkiLCJpYXQiOjE2NTk5NDU2NDUsImV4cCI6MTY2MTI0MTY0NX0.UWh7GHYqUG3L6P8puD-yUuG-WYhnMmXk3Z-6NuooyRE"
      //   }
      // }).then((res) => {
      //   console.log(res);
      //   console.log(commit);
      //   // const token = res.headers["authorization"];
      //   // dispatch("saveToken", token);
      //   // dispatch("fetchCurrentUser");
      //   // router 수정
      //   // router.push({ name: "company" });
      // });
      http
        .put(
          `/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`,
          data.apply
        )
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getResume: ({ commit }, no) => {
      http
        .get(`/company/userInfo/${no}`)
        .then(({ data }) => {
          commit("SET_RESUME", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registRoom: ({ commit }, data) => {
      http
        .post(`/room/company`, JSON.stringify(data))
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registEvalQuestion: ({ commit }, evalquestion) => {
      http
        .post(`/eval`, JSON.stringify(evalquestion))
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getEvalQuestionList: ({ commit }, no) => {
      http
        .get(`/eval/search/${no}`)
        .then(({ data }) => {
          console.log(data);
          commit("SET_EVAL_QUESTION_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateEvalQuestion: ({ commit }, data) => {
      http
        .put(`/eval/${data.no}`, JSON.stringify(data.evalquestion))
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteEvalQuestion: ({ commit }, no) => {
      http
        .delete(`/eval/${no}`)
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registUserEval: ({ commit }, data) => {
      http
        .post(
          `/eval/${data.jobOpeningId}/usereval/${data.userId}`,
          JSON.stringify(data.usereval)
        )
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getUserEvalList: ({ commit }, data) => {
      http
        .get(`/eval/${data.jobOpeningId}/usereval/${data.userId}`)
        .then(({ data }) => {
          commit("SET_USER_EVAL_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteUserEvalList: ({ commit }, data) => {
      http
        .delete(`/eval/${data.jobOpeningId}/usereval/${data.userId}`)
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  modules: {},
};
