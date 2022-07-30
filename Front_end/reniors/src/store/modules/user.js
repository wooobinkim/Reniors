import router from '@/router'
import axios from 'axios'

export const user = {
    state: {
      token: localStorage.getItem('token') || '',
      currentUser: {},
      profile: {},
      authError: null,
    },

    getters: {
      isLogginedIn: state => !!state.token,
      currentUser: state => state.currentUser,
      profile: state => state.profile,
      authError: state => state.authError

    },

    mutations: {
      SET_TOKEN: (state, token) => state.token = token,
      SET_CURRENT_USER: (state, user) => state.currentUser = user,
      SET_PROFILE: (state, profile) => state.profile = profile,
      SET_AUTH_ERROR: (state, error) => state.authError = error,
    },

    actions: {
      saveToken({ commit }, token){
        commit('SET_TOKEN', token)
        localStorage.setItem('token', token)
      },

      // error 커밋 추가
      login({ dispatch }, credentials){
        axios({
          // url 수정
          url: '',
          method: 'post',
          data: credentials
        })
        .then(res => {
          const token = res.data.key
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          // router 수정
          router.push({ name: 'home'})
        })
          // error 부분 추가
      },

      fetchCurrentUser(){

      }
    },

    modules: {
    }
  }
  