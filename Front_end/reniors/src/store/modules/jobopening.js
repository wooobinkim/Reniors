import axios from 'axios'
import drf from '@/api/drf'
import _ from 'lodash'
import http from '@/api/http'


export default {
  namespaced: true,
  state: {
    tags: [ '#서울 송파구', '#연봉 3000', '#주5일', '#웹디자인', '#백엔드' ],
    jobopenings: [],
    selectedJobopening: {},
    bookmarks: [],
    applies: [],
  },
  getters: {
    tags: state => state.tags,
    isJobopenings: state => !_.isEmpty(state.jobopenings),
    jobopenings: state => state.jobopenings,
    selectedJobopening: state => state.selectedJobopening,
    bookmarks: state => state.bookmarks,
    bookmarkId: state => state.bookmarks.find(bookmark => bookmark.jobOpeningResponse.id === state.selectedJobopening.id)?.id,
  },
  mutations: {
    TAGS: (state, tags) => state.tags = tags,
    JOBOPENINGS: (state, jobopenings) => state.jobopenings = jobopenings,
    SELECTJOB: (state, jobopening) => state.selectedJobopening = jobopening,
    BOOKMARKS: (state, bookmarks) => state.bookmarks = bookmarks,
    APPLIES: (state, applies) => state.applies = applies,
  },
  actions: {
    async fetchJobopenings({ commit }) {
      console.log('jobopening fetch execute')
      const response = await axios.get(drf.jobopening.get())
      const data = response.data.content
      console.log(data)
      commit('JOBOPENINGS', data)
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
    },
    async apply(_, jobopeningId) {
      const response = await http.post(`/jobopening/${jobopeningId}/apply`)
      console.log(response)
      alert('지원 성공!')
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