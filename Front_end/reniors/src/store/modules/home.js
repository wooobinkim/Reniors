import axios from 'axios'
import drf from '@/api/drf'
import _ from 'lodash'

const YOUTUBE_API_KEY = 'AIzaSyAF4F4t4ryCtxtxMrF0LgKNNXCITQVyi7E'

export default {
  namespaced: true,
  state: {
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
    newJobopenings: [
      {
        id: 1,
        name: 'new 채용공고 첫 번째',
        context: 'dsgsdsjdgoihwghgerg'
      },
      {
        id: 2,
        name: 'second new jobopening',
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
    isJobopenings: false,
    isYoutube: false,
  },
  getters: {
    hotJobopenings: state => state.hotJobopenings,
    newJobopenings: state => state.newJobopenings,
    recommendJobopenings: state => state.recommendJobopenings,
    isJobopenings: state => !_.isEmpty(state.hotJobopenings) && !_.isEmpty(state.newJobopenings) && !_.isEmpty(state.recommendJobopenings),
    youtubes: state => state.youtubes,
    isYoutube: state => !_.isEmpty(state.youtubes),
  },
  mutations: {
    JOBOPENINGS: (state, jobopenings) => {
      state.hotJobopenings = jobopenings.hotJobopenings
      state.newJobopenings = jobopenings.newJobopenings
      state.recommendJobopenings = jobopenings.recommendJobopenings
    },
    YOUTUBES: (state, youtubes) => state.youtubes = youtubes,
    DUMMY: () => 0,
  },
  actions: {
    async fetchHome({ commit, dispatch }, keyword) {
      console.log('home fetch execute')
      // 어떤 요청을 보내야 hot, new를 받을 수 잇는지 모르겠어서 임시로 전체 공고 불러옴
      const hotJobopenings = (await axios.get(drf.jobopening.get())).data.content
      const newJobopenings = (await axios.get(drf.jobopening.get())).data.content
      const recommendJobopenings = (await axios.get(drf.jobopening.get())).data.content

      console.log(hotJobopenings)
      commit('JOBOPENINGS', { hotJobopenings, newJobopenings, recommendJobopenings })      
      // youtube dispatch
      dispatch('fetchYoutubes', keyword)

    },
    async fetchYoutubes({ commit }, keyword) {
      console.log('fetch execute')
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
      console.log(youtubes)
      commit('YOUTUBES', youtubes)
    },
    async search({ commit }, keyword) {
      console.log(keyword)
      commit('DUMMY')
    }
  },
}