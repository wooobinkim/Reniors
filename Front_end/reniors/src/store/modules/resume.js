
import axios from 'axios'
import drf from '@/api/drf'

export default {
  state: {
    career: [],
  },

  getters: {
    carrer: state => state.career,

  },

  mutations: {
    SET_CAREER: (state, career) => state.career = career,
  },

  actions: {
    createCareer({ commit, getters }, career){
      axios({
        url: drf.resume.career(),
        method: 'post',
        data: JSON.stringify(career),
        headers: getters.authHeader,
      })
      .then(res => {
        commit('SET_CAREER', res.data)
      })
      .catch(
        console.log('error')
      )
    }

  },

  modules: {
  }

}