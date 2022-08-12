import axios from 'axios'
import _ from 'lodash'
import http from '@/api/http'

const YOUTUBE_API_KEY = 'AIzaSyAF4F4t4ryCtxtxMrF0LgKNNXCITQVyi7E'

export default {
  namespaced: true,
  state: {
    isLogin: false,
    hotJobopenings: [
      {
        id: 1,
        name: '핫한 채용공고 첫 번째',
        context: 'dsgsdsjdgoihwghgerg'
      },
      {
        id: 2,
        name: 'second hot jobopening',
        context: 'asdigaoghrughguiehguigj'
      }
    ],
    recommendJobopenings: [
      {
        id: 1,
        name: '첫 번째 추천 채용공고!',
        context: '삼성'
      },
      {
        id: 2,
        name: '두 번째 추천 채용공고!',
        context: 'LG'
      },
      {
        id: 3,
        name: '세 번째 추천 채용공고!',
        context: '한화'
      },
      {
        id: 4,
        name: '네 번째 추천 채용공고!',
        context: '한화'
      },
    ],
    youtubes: [],
    isYoutube: false,
  },
  getters: {
    isLogin: state => state.isLogin,
    hotJobopenings: state => state.hotJobopenings,
    recommendJobopenings: state => state.recommendJobopenings,
    youtubes: state => state.youtubes,
    isYoutube: state => !_.isEmpty(state.youtubes),
  },
  mutations: {
    IS_LOGIN: (state, value) => state.isLogin = value,
    YOUTUBES: (state, youtubes) => state.youtubes = youtubes,
    HOTJOBOPENINGS: (state, hots) => state.hotJobopenings = hots,
    DUMMY: () => 0,
  },
  actions: {
    async fetchYoutubes({ commit }, keyword) {
      commit('YOUTUBES', [])
      const response = await axios.get('https://www.googleapis.com/youtube/v3/search', {
        params: {
          part: 'snippet',
          type: 'video',
          q: keyword,
          key: YOUTUBE_API_KEY,
        }
      })
      const youtubes = response.data.items
      commit('YOUTUBES', youtubes)
    },
    async fetchHot({ commit }) {
      const response = await http.get('/jobopening/search/viewsDesc')
      commit('HOTJOBOPENINGS', response.data)
    },
    async search({ commit }, keyword) {
      console.log(keyword)
      commit('DUMMY')
    }
  },
}