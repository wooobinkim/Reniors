
import axios from 'axios'
import drf from '@/api/drf'

export default {
  state: {
    career: [],
  },

  getters: {
    career: state => state.career,

  },

  mutations: {
    SET_CAREER: (state, career) => state.career = career,
  },

  actions: {
    createCareer({ dispatch, getters }, career){
      axios({
        url: drf.resume.career(),
        method: 'post',
        data: JSON.stringify(career),
        headers: getters.authHeader,
      })
      .then(() => {
        dispatch('fetchCareer')
      }
      )
      .catch((err) => {
        console.log(err)
      })
    },
    deleteCareer({ dispatch , getters}, careerPk){
      if (confirm('정말 삭제하시겠습니까?')){
        axios({
          url: drf.resume.careerEdit(careerPk),
          method: 'delete',
          headers: getters.authHeader,
        })
        .then(() => {
          dispatch('fetchCareer')
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },

    fetchCareer({ commit, getters }){
      axios({
        url : 'https://i7b307.p.ssafy.io/api/resume/career',
        method: 'get',
        headers: getters.authHeader
      })
      .then(res => {
        commit('SET_CAREER', res.data)
      })
    },
    
  },

  

  modules: {
  }

}