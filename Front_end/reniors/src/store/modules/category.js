import axios from "axios"
import drf from '@/api/drf'


export default{
    state: {
        parents: [],
        childs: [],
        sido: [],
        gugun: [],

    },

    getters: {
        parents: state => state.parents,
        childs: state => state.childs,
        sido: state => state.sido,
        gugun : state => state.gugun

    },

    mutations: {
        SET_PARENTS: (state, parents) => state.parents = parents,
        SET_CHILDS: (state, childs) => state.childs = childs,
        SET_SIDO: (state, sido) => state.sido = sido,
        SET_GUGUN : (state, gugun) => state.gugun = gugun,

    },

    actions: {
        fetchParents({getters, commit}) {
            axios({
                url: drf.category.jobsLarge(),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_PARENTS', res.data)
            })
            .catch(err => console.error(err.response))
        },
        fetchChilds({getters, commit}, parentId) {
            axios({
                url: drf.category.jobsSmall(parentId),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_CHILDS', res.data)
            })
            .catch(err => console.error(err.response))
        },
        fetchSido({getters, commit}) {
            axios({
                url: drf.category.sido(),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_SIDO', res.data)
            })
            .catch(err => console.error(err.response))
        },
        fetchGugun({getters, commit}, sidoId) {
            axios({
                url: drf.category.gugun(sidoId),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_GUGUN', res.data)
            })
            .catch(err => console.error(err.response))
        },
        
    }
}