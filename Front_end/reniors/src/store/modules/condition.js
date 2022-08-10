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
    async deleteCondition({ dispatch }, conditionId) {
      const response = await axios.delete(drf.condition.detail(conditionId))
      console.log(response)
      dispatch('condition/fetchConditions')
    },
  },
}