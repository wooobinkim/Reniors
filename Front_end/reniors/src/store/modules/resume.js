
import axios from 'axios'
import drf from '@/api/drf'

export default {
  state: {
    careers : [],
  },

  getters: {
    careers : state => state.careers,

  },

  mutations: {
    SET_CAREERS : (state, careers) => state.careers = careers,
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
      })
      .catch((err) => {
        console.log(err)
      })
    },

    updateCareer({ dispatch, getters }, career ){
      console.log('확인!')
      console.log(career)
      axios({
        url: drf.resume.careerEdit(career.id),
        method: 'put',
        data: JSON.stringify(career),
        headers: getters.authHeader,
      })
      .then(() => {
        dispatch('fetchCareer')
      })
      .catch((err) => {
        console.log(err)
      })
    },

    deleteCareer({ dispatch , getters }, careerPk){
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
        commit('SET_CAREERS', res.data)
      })
    },
    
  },

  

  modules: {
  }

}