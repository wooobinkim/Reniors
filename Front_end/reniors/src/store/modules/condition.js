import drf from '@/api/drf'
import axios from 'axios'
import http from '@/api/http'

export default {
  namespaced: true,
  state: {
    conditions: [],
  },
  getters: {
    conditions: state => state.conditions
  },
  mutations: {
    CONDITIONS: (state, conditions) => state.conditions = conditions,
  },
  actions: {
    async fetchConditions({ commit }) {
      const response = await http.get(`/jobopening/condition`)
      console.log(response.data)
      const data = response.data.content
      console.log(data)
      commit('CONDITIONS', data)
    },
    async createCondition({ dispatch }, payload) {
      const data = {
        name: payload.name,
        jobParentCategoryId: payload.parent,
        lastEdu: payload.lastEdu,
        minCareer: payload.minCareer,
        minSalary: payload.minSalary,
        typeEmployment: '정규직',
        workingDay: payload.day
      }
      console.log(data)
      const response = await http.post('/jobopening/condition', data)
      // payload.hopeareas.map((hopearea) => {

      // })
      console.log(response)
      dispatch('fetchConditions')
    },
    async deleteCondition({ dispatch }, conditionId) {
      const response = await axios.delete(drf.condition.detail(conditionId))
      console.log(response)
      dispatch('condition/fetchConditions')
    },
  },
}