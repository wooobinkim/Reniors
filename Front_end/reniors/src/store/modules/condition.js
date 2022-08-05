import drf from '@/api/drf'
import axios from 'axios'

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
      const response = await axios.get(drf.condition.get())
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