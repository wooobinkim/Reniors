import http from "@/api/http";
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
    jobopeninglist: [],
    jobopening: null,
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
    SET_JOBOPENING_LIST(state, data) {
      state.jobopeninglist = data;
    },
    SET_JOBOPENING(state, data) {
      state.jobopening = data;
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

    // error 커밋 추가
    async signup(credentials) {
      await axios({
        // url 수정
        url: "",
        method: "post",
        data: credentials,
      })
        // .then(res) < 수정
        .then(() => {
          // 더 로직이 있을지도..
          router.push({ name: "login" });
        });
      // error 부분 추가
    },

    registJobOpening: ({ commit }, jobopening) => {
      http
        .post(`/company/jobopening`, JSON.stringify(jobopening))
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
  },

  modules: {},
};
