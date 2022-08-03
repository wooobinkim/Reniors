import router from "@/router";
import axios from "axios";
// import jwt_decode from 'jwt-decode'
// import { login, findById } from '@/api/user.js'

export const user = {
  state: {
    token: localStorage.getItem("token") || "",
    currentUser: {},
    profile: {},
    authError: null,
    // 코드추가
    // isLogin: false,
    // isLoginError: false,
    // userInfo: null,
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
    // 추가
    // SET_IS_LOGIN: (state, isLogin) => state.isLogin = isLogin,
    // SET_IS_LOGIN_ERROR: (state, isLoginError) => {
    //   state.isLoginError = isLoginError
    // },
    // SET_USER_INFO: (state, userInfo) => {
    //   state.isLogin = true;
    //   state.userInfo = userInfo;
    // }
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
    login({ dispatch }, credentials) {
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

    // 추가
    // async userConfirm({ commit }, credentials){
    //   await login(credentials, (response)=> {
    //     console.log('check!!!!!')
    //     console.log(response)
    //     if (response.data.message === "success" ){
    //       let token = response.data["access-token"]
    //       console.log(token)
    //       commit("SET_IS_LOGIN", true)
    //       // error 부분 추가
    //       commit("SET_IS_LOGIN_ERROR", false)
    //       sessionStorage.setItem("access-token", token);
    //     } else {
    //       commit("SET_IS_LOGIN", false);
    //       commit("SET_IS_LOGIN_ERROR", true)
    //     }
    //   },
    //   () => {
    //     console.log('실패!')
    //   }
    //   )
    // },
    // getUserInfo({ commit }, token){
    //   let decode_token = jwt_decode(token);
    //   findById(decode_token.id, (response) => {
    //     if (response.data.message === "success"){
    //       commit("SET_USER_INFO", response.data.userInfo)
    //     }
    //   })
    // },

    // error 커밋 추가
    signup(credentials) {
      axios({
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

    fetchMypage({ commit, getters }, user_id) {
      axios({
        // url 수정
        url: user_id,
        method: "get",
        headers: getters.authHeader,
      }).then((res) => {
        commit("SET_PROFILE", res.data);
      });
    },

    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLogginedIn) {
        console.log(getters.authHeader);
        axios({
          // url 수정
          url: "https://i7b307.p.ssafy.io/api/users/",
          method: "get",
          headers: getters.authHeader,
        })
          .then((res) => {
            console.log(res.data);
            commit("SET_CURRENT_USER", res.data);
            console.log(getters.currentUser);
            dispatch("fetchMypage", res.data.user_id);
          })
          .catch((err) => {
            if (err.response.status === 401) {
              dispatch("removeToken");
              router.push({ name: "Login" });
            }
          });
      }
    },
  },

  modules: {},
};
