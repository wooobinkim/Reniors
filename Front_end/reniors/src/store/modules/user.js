import router from '@/router'
import axios from 'axios'

export const user = {
    state: {
      token: localStorage.getItem('token') || '',
      currentUser: {},
      profile: {},
      authError: null,
      // credentials: {}
    },

    getters: {
      isLogginedIn: state => !!state.token,
      currentUser: state => state.currentUser,
      profile: state => state.profile,
      authError: state => state.authError,
      // Authorization: `Token ${state.token}`
      authHeader: state => ({ Authorization: ` ${state.token}` })
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

      removeToken({ commit }) {
        commit('SET_TOKEN', '')
        localStorage.setItem('token', '')
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
      
      // error 커밋 추가
      signup(credentials){
        axios({
          // url 수정
          url: '',
          method: 'post',
          data: credentials
        })
        // .then(res) < 수정
        .then(() => {
          // 더 로직이 있을지도..
          router.push({ name: 'login' })
        })
        // error 부분 추가
      },

      fetchMypage({ commit, getters }, user_id){
        axios({
          // url 수정
          url: user_id,
          method: 'get',
          headers: getters.authHeader,
        })
        .then(res => {
          commit('SET_PROFILE', res.data)
        })
      },

      fetchCurrentUser({ commit, getters, dispatch }){
        if (getters.isLogginedIn) {
          axios({
            // url 수정
            url : '',
            method: 'get',
            headers: getters.authHeader,
          })
          .then(res => {
            commit('SET_CURRENT_USER', res.data)
            dispatch('fetchMypage', res.data.user_id)
          })
          .catch(err => {
            if (err.response.status === 401){
              dispatch('removeToken')
              router.push({ name: 'Login'})
            }
          })
        }
      }
    },

    modules: {
    }
  }
  