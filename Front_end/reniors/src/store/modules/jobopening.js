import axios from 'axios'
import drf from '@/api/drf'


export default {
  namespaced: true,
  state: {
    tags: [ '#서울 송파구', '#연봉 3000', '#주5일', '#웹디자인', '#백엔드' ],
    jobopenings: [],
    selectedJobopening: {
      id: '1',
      companyDto: {
        name: '좋은 회사'
      },
      title: '공고제목',
      contents: '공고 내용',
      createdDate: '2020-02-02',
      finishedDate: '2022-03-03',
      minCareer: '백수',
      lastEdu: '고졸',
      minSalary: '38,000,000',
      jobPosition: '운전병',
      workingDay: '매일',
      numberPeople: '00'
    },
  },
  getters: {
    tags: state => state.tags,
    jobopenings: state => state.jobopenings,
    selectedJobopening: state => state.selectedJobopening,
  },
  mutations: {
    TAGS: (state, tags) => state.tags = tags,
    JOBOPENINGS: (state, jobopenings) => state.jobopenings = jobopenings,
    SELECTJOB: (state, jobopening) => state.selectJobopening = jobopening
  },
  actions: {
    async fetchJobopenings({ commit }) {
      console.log('jobopening fetch execute')
      const response = await axios.get(drf.jobopening.get())
      const data = response.data.content
      console.log(data)
      commit('JOBOPENINGS', data)
    },
    selectJobopening({ state, commit }, id) {
      console.log('select jobopening')
      const data = state.jobopenings.find(jobopening => jobopening.id === id)
      console.log(state.jobopenings)
      console.log(data)
      commit('SELECTJOB', data)
    }
  },
}