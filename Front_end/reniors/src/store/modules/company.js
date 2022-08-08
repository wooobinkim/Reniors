import http from "@/api/http";
// import multipart from "@/api/multipart";
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
    companyinfo: {},
    applylist: [],
    applyuser: null,
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
    SET_COMPANY(state, data) {
      state.companyinfo = data;
    },
    SET_APPLY_LIST(state, datas) {
      datas.forEach((data) => {
        state.applylist.push(data);
      });
    },
    SET_APPLY_USER(state, data) {
      state.applyuser = data;
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
        // console.log(formData);
        axios({
            url:"https://i7b307.p.ssafy.io/api/company",
            method : "post",
            data : formData,
            headers : {
                "Content-Type": "multipart/form-data",
                // Authorization: "Bearer " + token,
            }
        }).then((res)=>{
            console.log(res);
            console.log(commit);
        }).catch((error)=>{
            console.log(error);
        })
    //    multipart
    //     .post(`/company`, formData)
    //     .then(({ data }) => {
    //       commit("SET_DATASTATE", data);
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
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
    updateApply: ({ commit }, data) => {
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
  },

  modules: {},
};
