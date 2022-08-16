// import http from "@/api/http";
// import multipart from "@/api/multipart";
import router from "@/router";
import axios from "axios";
// import jobopening from "./jobopening";

export default {
  namespaced: true,
  state: {
    token: localStorage.getItem("token") || "",
    isCompanyLogin:null,
    header:"",
    currentUser: {},
    profile: {},
    authError: null,
    datastate: "",
    jobopeninglisting: [],
    jobopeninglisted: [],
    jobopening: null,
    companyinfo: {},
    companyimg: "",
    applylist: [],
    apply: null,
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
    isCompanyLogin:(state)=>state.isCompanyLogin,
    profile: (state) => state.profile,
    authError: (state) => state.authError,
    // Authorization: `Token ${state.token}`
    token:(state) => state.token,
    authHeader: (state) => ({ Authorization: `Bearer ${state.token}` }),
    header: (state) => state.header,
    jobopeninglisting: (state) => state.jobopeninglisting,
    jobopeninglisted: (state) => state.jobopeninglisted,
    jobopening: (state) => state.jobopening,
    companyinfo: (state) => state.companyinfo,
    companyimg: (state) => state.companyimg,
    applylist: (state) => state.applylist,
    apply: (state) => state.apply,
    applyuser: (state) => state.applyuser,
    evalquestionlist: (state) => state.evalquestionlist,
    userevallist: (state) => state.userevallist,
    interviewer: (state) => state.interviewer,
    resume: (state) => state.resume,
    interviewapplylist: (state) => state.interviewapplylist,
    interviewapplylistasc: (state) => state.interviewapplylistasc,
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    GET_TOKEN: (state) => console.log(state.token),
    IS_COMPANY_LOGIN_TRUE:(state)=>(state.isCompanyLogin=true),
    IS_COMPANY_LOGIN_FALSE:(state)=>(state.isCompanyLogin=false),
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
      state.companyimg = data.baseURL + data.companyProfile;
    },
    SET_APPLY_LIST(state, data) {
      state.applylist = data;
    },
    SET_APPLY(state, data) {
      state.apply = data;
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
    SET_INTERVIEWER(state, data) {
      state.interviewer = data;
    },
    SET_RESUME(state, data) {
      state.resume = data;
    },
    SET_HEADER(state, data) {
      state.header = data;
    },
  },

  actions: {
    setheader({ commit }, data) {
      commit("SET_HEADER", data);
    },
    saveToken({ commit }, token) {
      commit("SET_TOKEN", token);
      commit("IS_COMPANY_LOGIN_TRUE");
      localStorage.setItem("token", token);
    },

    removeToken({ commit }) {
      commit("SET_TOKEN", "");
      commit("IS_COMPANY_LOGIN_FALSE");
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
      }).then(async (res) => {
        const token = res.headers["authorization"];
        await dispatch("saveToken", token);
        // dispatch("fetchCurrentUser");
        // router 수정
        router.push({ name: "company" });
      });
      // error 부분 추가
    },

    companylogout:({dispatch})=>{
      dispatch("removeToken");
    },

    registCompany: ({ commit,getters }, formData) => {
      // multipart
      //   .post(`/company`, formData)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company`,
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: getters.authHeader,
        },
        data: formData,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCompany: ({ commit, getters }) => {
      // http
      //   .get(`/company`)
      axios({
        url: "https://i7b307.p.ssafy.io/api/company",
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_COMPANY", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateCompany: ({ commit, getters }, formData) => {
      // multipart
      //   .put(`/company`, JSON.stringify(formData))
      axios({
        url: `https://i7b307.p.ssafy.io/api/company`,
        method: "put",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: getters.authHeader,
        },
        data: formData,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    registJobOpening: ({ commit, getters }, formData) => {
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening`,
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: "Bearer "+getters.token,
        },
        data: formData,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateJobOpening: ({ commit, getters }, formData) => {
      // http
      //   .put(`/company/jobopening/${data.no}`, data.jobopening)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening`,
        method: "put",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: getters.authHeader,
        },
        data: formData,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getJobOpeningList: ({ commit, getters }) => {
      // http
      //   .get(`/company/jobopening`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_JOBOPENING_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getJobOpening: ({ commit, getters }, no) => {
      // http
      //   .get(`/company/jobopening/${no}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_JOBOPENING", data);
        })

        .catch((error) => {
          console.log(error);
        });
    },
    deleteJobOpening: ({ commit, getters }, no) => {
      // http
      //   .delete(`/company/jobopening/${no}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}`,
        method: "delete",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_JOBOPENING", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    finishJobOpening: ({ commit, getters }, no) => {
      // http
      //   .put(`/company/jobopening/${no}/finish`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/finish`,
        method: "put",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    progressJobOpening: ({ commit, getters }, data) => {
      // http
      //   .put(`/company/progress/${data.no}`, data.progress)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/progress/${data.no}`,
        method: "put",
        headers: getters.authHeader,
        data: data.progress,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getapplylist: ({ commit, getters }, no) => {
      // http
      //   .get(`/company/jobopening/${no}/apply`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/apply`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_APPLY_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getapply: ({ commit, getters }, data) => {
      // http
      //   .get(`/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_APPLY", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getinterviewapplylist: ({ commit, getters }, no) => {
      // http
      //   .get(`/company/jobopening/${no}/apply`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/apply`,
        method: "get",
        headers: getters.authHeader,
        // data:data.jobopening,
      })
        .then(({ data }) => {
          commit("SET_INTERVIEW_APPLY_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getinterviewapplylistasc: ({ commit, getters }, no) => {
      // http
      //   .get(`/company/jobopening/${no}/apply/dateAsc`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${no}/apply/dateAsc`,
        method: "get",
        headers: getters.authHeader,
        // data:data.jobopening,
      })
        .then(({ data }) => {
          commit("SET_INTERVIEW_APPLY_LIST_ASC", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateApply: ({ commit, getters }, data) => {
      // http
      //   .put(
      //     `/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`,
      //     data.apply
      //   )
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}`,
        method: "put",
        headers: getters.authHeader,
        data: data.apply,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    finishInterview: ({ commit, getters }, data) => {
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/jobopening/${data.jobOpeningId}/apply/${data.applyId}/finishInterview`,
        method: "put",
        headers: getters.authHeader,
        // data:data.apply,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getResume: ({ commit, getters }, no) => {
      // http
      //   .get(`/company/userdetail/${no}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/company/userdetail/${no}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_RESUME", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registRoom: ({ commit, getters }, data) => {
      // http
      //   .post(`/room/company`, JSON.stringify(data))
      axios({
        url: `https://i7b307.p.ssafy.io/api/room/company`,
        method: "post",
        headers: getters.authHeader,
        data: data,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registEvalQuestion: ({ commit, getters }, evalquestion) => {
      // http
      //   .post(`/eval`, JSON.stringify(evalquestion))
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval`,
        method: "post",
        headers: getters.authHeader,
        data: evalquestion,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getEvalQuestionList: ({ commit, getters }, no) => {
      // http
      //   .get(`/eval/search/${no}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval/search/${no}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_EVAL_QUESTION_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateEvalQuestion: ({ commit, getters }, data) => {
      // http
      //   .put(`/eval/${data.no}`, JSON.stringify(data.evalquestion))
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.no}`,
        method: "put",
        headers: getters.authHeader,
        data: data.evalquestion,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteEvalQuestion: ({ commit, getters }, no) => {
      // http
      //   .delete(`/eval/${no}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${no}`,
        method: "delete",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    registUserEval: ({ commit, getters }, data) => {
      // http
      //   .post(
      //     `/eval/${data.jobOpeningId}/usereval/${data.userId}`,
      //     JSON.stringify(data.usereval)
      //   )
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.jobOpeningId}/usereval/${data.userId}`,
        method: "post",
        headers: getters.authHeader,
        data: data.usereval,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getUserEvalList: ({ commit, getters }, data) => {
      // http
      //   .get(`/eval/${data.jobOpeningId}/usereval/${data.userId}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.jobOpeningId}/usereval/${data.userId}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_USER_EVAL_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteUserEvalList: ({ commit, getters }, data) => {
      // http
      //   .delete(`/eval/${data.jobOpeningId}/usereval/${data.userId}`)
      axios({
        url: `https://i7b307.p.ssafy.io/api/eval/${data.jobOpeningId}/usereval/${data.userId}`,
        method: "delete",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("SET_DATASTATE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setInterviewer: ({ commit }, data) => {
      commit("SET_INTERVIEWER", data);
    },
  },

  modules: {},
};
