import axios from 'axios'
import drf from '@/api/drf'
import _ from 'lodash'
import http from '@/api/http'
import router from '@/router'
import dayjs from 'dayjs'


export default {
  namespaced: true,
  state: {
    tags: [ '#서울 송파구', '#연봉 3000', '#주5일', '#웹디자인', '#백엔드' ],
    jobopenings: [],
    selectedJobopening: {},
    bookmarks: [],
    applies: [],
    interview: []
  },
  getters: {
    tags: state => state.tags,
    isJobopenings: state => !_.isEmpty(state.jobopenings),
    jobopenings: state => state.jobopenings,
    selectedJobopening: state => state.selectedJobopening,
    bookmarks: state => state.bookmarks,
    bookmarkId: state => state.bookmarks.find(bookmark => bookmark.jobOpeningResponse.id === state.selectedJobopening.id)?.id,
    applies: state => state.applies,
    isApply: state => {
      if (state.applies.find(apply => apply.jobOpeningId === state.selectedJobopening.id) === undefined) return false
      else return true
    },
    interview: state => state.interview
  },
  mutations: {
    TAGS: (state, tags) => state.tags = tags,
    JOBOPENINGS: (state, jobopenings) => state.jobopenings = jobopenings,
    SELECTJOB: (state, jobopening) => state.selectedJobopening = jobopening,
    BOOKMARKS: (state, bookmarks) => state.bookmarks = bookmarks,
    APPLIES: (state, applies) => state.applies = applies,
    INTERVIEW: (state, applies) => {
      state.interview = []
      applies.forEach((apply) => {
        const object = { title: apply.jobOpeningTitle, date: dayjs(apply.interviewDate).format("YYYY-MM-DD") }
        // object.replace (/"/g,'')
        console.log(object)
        // const json = JSON.stringify(object)
        // const unquoted = object.replace(/"([^"]+)":/g, '$1:')
        state.interview.push(object)
        

      })
    }
  },
  actions: {
    // all jobopenings
    async fetchJobopenings({ commit }) {
      const response = await axios.get(drf.jobopening.get())
      const data = response.data.content
      console.log(data)
      commit('JOBOPENINGS', data)
    },

    // applied jobopenings
    async fetchApplied({ commit }) {
      commit('JOBOPENINGS', [])
      const response = await http.get('/jobopening/apply')
      console.log(response)
      commit('JOBOPENINGS', response.data)
    },
    
    // bookmarked jobopenings
    async fetchBookmarked({ commit }) {
      commit('JOBOPENINGS', [])
      const response = await http.get('/jobopening/bookmark')
      commit('JOBOPENINGS', response.data)
    },
    async selectJobopening({ commit }, id) {
      console.log('select jobopening')
      const response = await axios.get(drf.jobopening.detail(id))
      const data = response.data
      console.log(data)
      commit('SELECTJOB', data)
    },
    async fetchApply({ commit }) {
      const response = await http.get('/jobopening/apply')
      console.log(response)
      commit('APPLIES', response.data)
      commit('INTERVIEW', response.data)
    },
    async apply(_, jobopeningId) {
      const response = await http.post(`/jobopening/${jobopeningId}/apply`)
      console.log(response)
      alert('지원 성공!')
      router.go(0)
    },
    async fetchBookmark({ commit }) {
      const response = await http.get('/jobopening/bookmark')
      commit('BOOKMARKS', response.data)
    },
    async addBookmark({ dispatch }, id) {
      const response = await http.post(`/jobopening/bookmark/${id}`)
      console.log(response)
      dispatch('fetchBookmark')
    },
    async deleteBookmark({ dispatch }, id) {
      const response = await http.delete(`/jobopening/bookmark/${id}`)
      console.log(response)
      dispatch('fetchBookmark')
    }
  },
}