import http from "@/api/http";
import router from "@/router";

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
      const response = await http.get(`/jobopening/condition`);
      commit("CONDITIONS", response.data);
      router.push({ name: "Condition" });
    },
    async createCondition({ dispatch }, payload) {
      const data = {
        name: payload.name,
        jobParentCategoryId: payload.parent,
        lastEdu: payload.lastEdu,
        minCareer: payload.minCareer,
        minSalary: payload.minSalary,
        typeEmployment: payload.type,
        workingDay: payload.day,
      };
      const response = await http.post("/jobopening/condition", data);
      const conditionId = response.data;
      payload.hopeareas.map(async (hopearea) => {
        await http.post(`/jobopening/condition/${conditionId}/hopearea`, {
          gugunId: hopearea,
          id: payload.selectSido.data,
        });
      });
      dispatch("fetchConditions");
    },
    async deleteCondition({ dispatch }, conditionId) {
      await http.delete(`/jobopening/condition/${conditionId}`);
      dispatch("fetchConditions");
    },
    async fetchEdit({ commit }, id) {
      const response = await http.get(`/jobopening/condition/${id}`);
      commit("EDITDATA", response.data);
    },

    // jobopenings
    async search({ commit }, conditionId) {
      const response = await http.get(`/jobopening/search/${conditionId}`);
      commit("JOBOPENINGS", response.data.content);
    },
  },
};
