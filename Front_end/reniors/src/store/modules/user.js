import drf from "@/api/drf";
import multipart from "@/api/multipart";
import router from "@/router";
import axios from "axios";
// import jwt_decode from 'jwt-decode'
// import { login, findById } from '@/api/user.js'

export const user = {
  state: {
    token: localStorage.getItem("token") || "",
    currentUser: {},
    authError: null,

    profile: {},
    prefer: {},
    id: "",

    rooms: [],

    // 코드추가
    // isLogin: false,
    // isLoginError: false,
    // userInfo: null,
  },

  getters: {
    isLogginedIn: (state, _, rootState) =>
      !!state.token || !!rootState.company.token,
    currentUser: (state) => state.currentUser,
    authError: (state) => state.authError,
    // Authorization: `Token ${state.token}`
    authHeader: (state) => ({
      Authorization: `Bearer ${state.token}`,
      "Content-type": "Application/JSON",
    }),

    profile: (state) => state.profile,
    prefer: (state) => state.prefer,
    id: (state) => state.id,

    rooms: (state) => state.rooms,
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),

    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_PREFER: (state, prefer) => (state.prefer = prefer),
    SET_ID: (state, id) => (state.id = id),

    SET_ROOMS: (state, rooms) => (state.rooms = rooms),

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

    async removeToken({ commit, getters }) {
      await commit("SET_TOKEN", "");
      await commit("SET_CURRENT_USER",{});
      console.log("currentUser", getters.currentUser);
      localStorage.setItem("token", "");

    },

    // error 커밋 추가
    login({ dispatch }, credentials) {
      axios({
        // url 수정
        url: "https://i7b307.p.ssafy.io/api/users/login",
        method: "post",
        data: credentials,
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          // router 수정
          router.push({ name: "home" });
        })
        .catch(() => {
          alert(
            "아이디 또는 비밀번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
        });
    },

    updateUser({ dispatch }, formData) {
      multipart
        .put(`/users`, formData)
        .then(() => {
          dispatch("fetchCurrentUser");
          router.push({ name: "ResumeStepTwo" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    fetchCurrentUser({ commit, getters, dispatch }) {
      if (getters.isLogginedIn) {
        axios({
          // url 수정
          url: "https://i7b307.p.ssafy.io/api/users/",
          method: "get",
          headers: getters.authHeader,
        })
          .then((res) => {
            commit("SET_CURRENT_USER", res.data);
            // dispatch('fetchMypage', res.data.user_id)
          })
          .catch((err) => {
            if (err.response.status === 401) {
              dispatch("removeToken");
              router.push({ name: "Login" });
            }
          });
      }
    },
    kakaoRegist({ dispatch }, formData) {
      multipart
        .post(`/users/kakao/regist`, formData)
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({ name: "home" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    registUser({ commit }, formData) {
      console.log(commit);
      multipart
        .post(`/users/regist`, formData)
        .then(() => {
          router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    fetchPrefer({ commit, getters }) {
      axios({
        url: drf.recommendcondition.recommend(),
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          commit("SET_PREFER", res.data);
        })
        .catch((err) => {
          commit("SET_PREFER", {
            id: null,
            jobParentCategoryResponse: { id: null, name: null },
            gugunResponse: { id: null, name: null, code: null },
            workingDay: null,
            minSalary: null,
          });
          console.log(err);
        });
    },

    createPrefer({ getters, dispatch }, data) {
      axios({
        url: drf.recommendcondition.recommend(),
        method: "post",
        data: JSON.stringify(data),
        headers: getters.authHeader,
      })
        .then((res) => {
          dispatch("fetchPrefer");
          router.push({ name: "MyPage" });
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updatePrefer({ getters, dispatch }, data) {
      axios({
        url: drf.recommendcondition.recommend(),
        method: "put",
        data: JSON.stringify(data),
        headers: getters.authHeader,
      })
        .then((res) => {
          dispatch("fetchPrefer");
          router.push({ name: "MyPage" });
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    findId({ commit }, credentials) {
      axios({
        url: drf.user.userid(credentials.name, credentials.phone),
        method: "get",
      })
        .then((res) => {
          commit("SET_ID", res.data);
          router.push({ name: "FindUsernameResult" });
        })
        .catch(() => {
          alert(
            "이름 또는 전화번호를 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
        });
    },

    findPwd({ commit }, credentials) {
      axios({
        url: drf.user.userpwd(credentials.name, credentials.userAppId),
        method: "get",
      })
        .then((res) => {
          console.log(res.data);
          console.log(commit);
          router.push({ name: "FindPasswordResult" });
        })
        .catch(() => {
          alert(
            "이름 또는 이메일을 잘못 입력하셨습니다.\n입력하신 내용을 다시 확인해주세요.",
          );
        });
    },

    changePassword({ getters }, data) {
      axios({
        url: drf.user.changePwd(data),
        method: "put",
        headers: getters.authHeader,
      })
        .then((res) => {
          alert("변경되었습니다!");
          console.log(res);
        })
        .catch((err) => console.log(err));
    },

    fetchRooms({ getters, commit }) {
      axios({
        url: "https://i7b307.p.ssafy.io/api" + "/jobopening/apply/dateAsc",
        method: "get",
        headers: getters.authHeader,
      })
        .then((res) => {
          console.log('요ㅕ기',res);
          commit("SET_ROOMS", res.data);
        })
        .catch((err) => console.error(err.response));
    },

    kakaologin({ dispatch }, forms) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/users/kakao/login",
        method: "post",
        data: JSON.stringify(forms),
      })
        .then((res) => {
          const token = res.headers["authorization"];
          dispatch("saveToken", token);
          dispatch("fetchCurrentUser");
          router.push({ name: "home" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteUser({ dispatch, getters }) {
      axios({
        url: drf.user.delete(),
        method: "delete",
        headers: getters.authHeader,
      })
      .then(() => {
        dispatch("removeToken")
        router.push({ name: "home" })
      })
      .catch((err) => {
        console.log(err)
      })
    }
  },

  modules: {},
};
