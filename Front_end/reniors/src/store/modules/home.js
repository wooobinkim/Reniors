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
    ]
  },
  getters: {
    hotJobopenings: state => state.hotJobopenings,
    newJobopenings: state => state.newJobopenings,
    recommendJobopenings: state => state.recommendJobopenings,
  },
  mutations: {
  },
  actions: {
  },
}