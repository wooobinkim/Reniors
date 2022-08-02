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
    ]
  },
  getters: {
    isLogin: state => state.isLogin,
    hotJobopenings: state => state.hotJobopenings,
    newJobopenings: state => state.newJobopenings,
    recommendJobopenings: state => state.recommendJobopenings,
  },
  mutations: {
    IS_LOGIN: (state, value) => state.isLogin = value,
  },
  actions: {
    login({ commit }) {
      console.log('login')
      commit('IS_LOGIN', true)
    },
    logout({ commit }) {
      console.log('logout')
      commit('IS_LOGIN', false)
    }
  },
}