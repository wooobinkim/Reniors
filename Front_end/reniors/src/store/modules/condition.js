import http from "@/api/http";
// import localtest from "@/api/localtest";

export default {
  namespaced: true,
  state: {
    conditions: [],
    jobopenings: [],
    editData: {},
  },
  getters: {
    conditions: (state) => state.conditions,
    jobopenings: (state) => state.jobopenings,
    editData: (state) => state.editData,
  },
  mutations: {
    CONDITIONS: (state, conditions) => (state.conditions = conditions),
    JOBOPENINGS: (state, jobopenings) => (state.jobopenings = jobopenings),
    EDITDATA: (state, data) => (state.editData = data),
  },
  actions: {
    async fetchConditions({ commit }) {
      console.log("??");
      const response = await http.get(`/jobopening/condition`);
      // const response = await localtest.get(
      //   `http://localhost:8080/jobopening/condition`
      // );
      console.log(response.data);
      commit("CONDITIONS", response.data);
    },
    async createCondition({ dispatch }, payload) {
      const data = {
        name: payload.name,
        jobParentCategoryId: payload.parent,
        lastEdu: payload.lastEdu,
        minCareer: payload.minCareer,
        minSalary: payload.minSalary,
        typeEmployment: "정규직",
        workingDay: payload.day,
      };
      console.log(data);
      const response = await http.post(`/jobopening/condition`, data);
      // const response = await localtest.post(`http://localhost:8080/jobopening/condition`)
      const conditionId = response.data;
      payload.hopeareas.map(async (hopearea) => {
        let res = await http.post(
          `/jobopening/condition/${conditionId}/hopearea`,
          {
            gugunId: hopearea,
            id: payload.selectSido.data,
          }
        );
        console.log(res);
      });
      console.log(response);
      dispatch("fetchConditions");
    },
    async deleteCondition({ dispatch }, conditionId) {
      const response = await http.delete(
        `/jobopening/condition/${conditionId}`
      );
      console.log(response);
      dispatch("fetchConditions");
    },
    async fetchEdit({ commit }, id) {
      const response = await http.get(`/jobopening/condition/${id}`);
      console.log(response.data);
      commit("EDITDATA", response.data);
    },

    // jobopenings
    async search({ commit }, conditionId) {
      const response = await http.get(`/jobopening/search/${conditionId}`);
      console.log(response);
      console.log(`fetch by ${conditionId}th condition`);
      commit("JOBOPENINGS", response.data);
    },
  },
};
