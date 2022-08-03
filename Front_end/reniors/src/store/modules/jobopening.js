import axios from 'axios'
import drf from '@/api/drf'


export default {
  namespaced: true,
  state: {
    jobopenings: []
  },
  getters: {
    jobopenings: state => state.jobopenings,
  },
  mutations: {
    JOBOPENINGS: (state, jobopenings) => state.jobopenings = jobopenings,
  },
  actions: {
    async fetchJobopenings({ commit }) {
      console.log('jobopening fetch execute')
      const response = await axios.get(drf.jobopening.get())
      const data = response.data.content
      console.log(data)
      commit('JOBOPENINGS', data)
    },
  },
}