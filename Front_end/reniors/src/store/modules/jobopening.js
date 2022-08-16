import axios from "axios";
import drf from "@/api/drf";
import _ from "lodash";
import http from "@/api/http";
import router from "@/router";
import { user } from "./user";

export default {
  namespaced: true,
  state: {
    tags: ["#서울 송파구", "#연봉 3000", "#주5일", "#웹디자인", "#백엔드"],
    jobopenings: [],
    selectedJobopening: {},
    recommendJobopenings: [],
    isrecommend: null,
    isrecommendheight: null,
    conditionJobopenings: [],
    bookmarks: [],
    applies: [],
    interview: [],
    bookmarksdate: [],
  },
  getters: {
    tags: (state) => state.tags,
    isJobopenings: (state) => !_.isEmpty(state.jobopenings),
    jobopenings: (state) => state.jobopenings,
    authHeader: () => ({
      Authorization: `Bearer ${user.state.token}`,
      "Content-type": "Application/JSON",
    }),
    recommendJobopenings: (state) => state.recommendJobopenings,
    isrecommend: (state) => state.isrecommend,
    isrecommendheight: (state) => state.isrecommendheight,
    conditionJobopenings: (state) => state.conditionJobopenings,
    selectedJobopening: (state) => state.selectedJobopening,
    bookmarks: (state) => state.bookmarks,
    bookmarkId: (state) =>
      state.bookmarks.find(
        (bookmark) =>
          bookmark.jobOpeningResponse.id === state.selectedJobopening.id
      )?.id,
    applies: (state) => state.applies,
    isApply: (state) => {
      if (
        state.applies.find(
          (apply) => apply.jobOpeningId === state.selectedJobopening.id
        ) === undefined
      )
        return false;
      else return true;
    },
    interview: (state) => state.interview,
    bookmarksdate: (state) => state.bookmarksdate,
  },
  mutations: {
    TAGS: (state, tags) => (state.tags = tags),
    JOBOPENINGS: (state, jobopenings) => (state.jobopenings = jobopenings),
    RECOMMENDCLEAR: (state) => {
      (state.isrecommend = null), (state.isrecommendheight = null);
      state.recommendJobopenings = [];
    },
    RECOMMENDJOBOPENINGS: (state, recommendJobopenings) => {
      state.recommendJobopenings = recommendJobopenings;
      if (!recommendJobopenings) {
        console.log("폴스");
        state.isrecommend = false;
        state.isrecommendheight = 76;
      } else {
        console.log("트루");
        state.isrecommend = true;
        state.isrecommendheight = 380;
      }
    },
    CONDITIONJOBOPENINGS: (state, conditionJobopenings) =>
      (state.conditionJobopenings = conditionJobopenings),
    SELECTJOB: (state, jobopening) => (state.selectedJobopening = jobopening),
    BOOKMARKS: (state, bookmarks) => (state.bookmarks = bookmarks),
    APPLIES: (state, applies) => (state.applies = applies),
    INTERVIEW: (state, applies) => {
      state.interview = [];
      applies.forEach((apply) => {

        const object = {
          title: apply.jobOpeningTitle,
          date: apply.interviewDate,
          child: apply.jobChildCategoryName,
        };

        // object.replace (/"/g,'')

        // const json = JSON.stringify(object)
        // const unquoted = object.replace(/"([^"]+)":/g, '$1:')
        state.interview.push(object);
      });
    },
    BOOKMARKSDATE: (state, bookmarks) => {
      state.bookmarksdate = [];
      bookmarks.forEach((bookmark) => {
        console.log(bookmark);
        const object1 = {
          title: bookmark.jobOpeningResponse.title,
          date: bookmark.jobOpeningResponse.finishedDate,
          company: bookmark.jobOpeningResponse.companyName,
          link:
            "https://i7b307.p.ssafy.io/jobopening/" +
            bookmark.jobOpeningResponse.id,
        };
        console.log(object1);

        state.bookmarksdate.push(object1);
      });
    },
  },
  actions: {
    // all jobopenings
    async fetchJobopenings({ commit }) {
      const response = await axios.get(drf.jobopening.get());
      const data = response.data.content;
      commit("JOBOPENINGS", data);
    },

    //추천조건으로 공고 가져오기
    async clearRecommend({ commit }) {
      commit("RECOMMENDCLEAR");
    },

    async fetchRecommend({ getters, dispatch }) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/recommendcondition`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(async ({ data }) => {
          await dispatch("fetchJobOpeningRecommend", data.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async fetchJobOpeningRecommend({ commit, getters }, data) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/search/recommend/${data}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("RECOMMENDJOBOPENINGS", data.content);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    //검색조건으로 공고 가져오기
    async fetchJobOpeningCondition({ commit, getters }, data) {
      await axios({
        url: `https://i7b307.p.ssafy.io/api/jobopening/search/${data}`,
        method: "get",
        headers: getters.authHeader,
      })
        .then(({ data }) => {
          commit("CONDITIONJOBOPENINGS", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // applied jobopenings
    async fetchApplied({ commit }) {
      commit("JOBOPENINGS", []);
      const response = await http.get("/jobopening/apply");
      console.log(response);
      commit("JOBOPENINGS", response.data);
    },

    // bookmarked jobopenings
    async fetchBookmarked({ commit }) {
      commit("JOBOPENINGS", []);
      const response = await http.get("/jobopening/bookmark");
      commit("JOBOPENINGS", response.data);
    },
    async selectJobopening({ commit }, id) {
      console.log("select jobopening");
      const response = await axios.get(drf.jobopening.detail(id));
      const data = response.data;
      console.log(data);
      commit("SELECTJOB", data);
    },
    async fetchApply({ commit }) {
      const response = await http.get("/jobopening/apply");
      console.log(response);
      commit("APPLIES", response.data);
      commit("INTERVIEW", response.data);
    },
    async apply(_, jobopeningId) {
      const response = await http.post(`/jobopening/${jobopeningId}/apply`);
      console.log(response);
      alert("지원 성공!");
      router.go(0);
    },
    async fetchBookmark({ commit }) {
      const response = await http.get("/jobopening/bookmark");
      console.log(response);
      commit("BOOKMARKS", response.data);
      commit("BOOKMARKSDATE", response.data);
    },
    async addBookmark({ dispatch }, id) {
      const response = await http.post(`/jobopening/bookmark/${id}`);
      console.log(response);
      dispatch("fetchBookmark");
    },
    async deleteBookmark({ dispatch }, id) {
      const response = await http.delete(`/jobopening/bookmark/${id}`);
      console.log(response);
      dispatch("fetchBookmark");
    },
  },
};
