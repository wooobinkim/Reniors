import axios from "axios";
import _ from "lodash";
import http from "@/api/http";

const YOUTUBE_API_KEY = "AIzaSyAF4F4t4ryCtxtxMrF0LgKNNXCITQVyi7E";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    hotJobopenings: [],
    recommendJobopenings: [],
    youtubes: [],
    isYoutube: false,
    notices: [],
    noticeNotReaded: null,
  },
  getters: {
    isLogin: (state) => state.isLogin,
    hotJobopenings: (state) => state.hotJobopenings,
    recommendJobopenings: (state) => state.recommendJobopenings,
    youtubes: (state) => state.youtubes,
    isYoutube: (state) => !_.isEmpty(state.youtubes),
    notices: (state) => state.notices,
    noticeNotReaded: (state) => state.noticeNotReaded,
  },
  mutations: {
    IS_LOGIN: (state, value) => (state.isLogin = value),
    YOUTUBES: (state, youtubes) => (state.youtubes = youtubes),
    HOTJOBOPENINGS: (state, hots) => (state.hotJobopenings = hots),
    NOTICES: (state, notices) => (state.notices = notices),
    noticeNotReaded: (state, noticeNotReaded) =>
      (state.noticeNotReaded = noticeNotReaded),
    DUMMY: () => 0,
  },
  actions: {
    async createNotice({ commit }, notice) {
      const response = await http.post("/notification", notice);
      commit("NOTICES", response.data);
    },
    async readNotice({ dispatch }, notificationId) {
      await http.get(`/notification/${notificationId}`);
      dispatch("fetchNotices");
    },
    async deleteNotice({ dispatch }, notificationId) {
      await http.delete(`/notification/${notificationId}`);
      dispatch("fetchNotices");
    },
    async fetchYoutubes({ commit }, keyword) {
      commit("YOUTUBES", []);
      const response = await axios.get(
        "https://www.googleapis.com/youtube/v3/search",
        {
          params: {
            part: "snippet",
            type: "video",
            q: keyword,
            key: YOUTUBE_API_KEY,
          },
        },
      );
      const youtubes = response.data.items;
      commit("YOUTUBES", youtubes);
    },
    async fetchHot({ commit }) {
      const response = await http.get("/jobopening/search/viewsDesc");
      commit("HOTJOBOPENINGS", response.data);
    },
    async search({ commit }, keyword) {
      console.log(keyword);
      commit("DUMMY");
    },
    async fetchNotices({ commit }) {
      const response = await http.get("/notification");
      commit("NOTICES", response.data);
      let notReadNotices = 0;
      response.data.forEach((notice) => {
        if (notice.isRead === "NOT_READ") {
          notReadNotices += 1;
        }
      });
      commit("noticeNotReaded", notReadNotices);
    },
  },
};
