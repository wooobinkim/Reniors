import router from '@/router'
import axios from "axios"
import _ from 'lodash'


export default{
    state: {
        token: localStorage.getItem('jwt') || '',
        currentUser: {},
        article: {},
        articles: [],


    },
    
    getters: {
        isLoggedIn: state => !!state.token,
        authHeader : state => ({ Authorization : `JWT ${state.token}`}),
        currentUser: state=> state.currentUser,
        article: state => state.article,
        articles: state => state.articles,
        isArticle: state => !_.isEmpty(state.article),
        isAuthor: (state, getters) => {
            return state.article.user?.pk === getters.currentUser.pk
        }
    },

    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_CURRENT_USER: (state, user) => state.currentUser = user,
        SET_ARTICLE: (state, article) => state.article = article,
        SET_ARTICLES: (state, articles) => state.articles = articles,
    },

    actions: {
        createArticle({getters, commit}, {article}){
            axios({
                url: '',
                method: 'post',
                data: article,
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_ARTICLE', res.data)
                router.push({name:'boardDetail', params:{job_parent_category_id:getters.article.pk}, query: {timestamp: Date.now()}})
            })
        },
        updateArticle({commit, getters}, {category_pk, article_pk, title, content}) {
            axios({
                url: '',
                method: 'put',
                data: {title, content},
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_ARTICLE', res.data)
                router.push({name: 'boardDetail', params:{job_parent_category_id: category_pk, job_board_id:article_pk}, query: {timestamp: Date.now()}})
            })
        },
        deleteArticle({commit, getters}, {category_pk, }) {
            if (confirm('정말 삭제하시겠습니까?')) {
                axios({
                    url: '',
                    method: 'delete',
                    headers: getters.authHeader,
                })
                .then(() => {
                    commit ('SET_ARTICLE', {})
                    router.push({name: 'board', params: {job_parent_category_id: category_pk,}})
                })
            }
        },
        fetchArticles({commit, getters}, ) {
            axios({
                url: '',
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_ARTICLES', res.data)
            })
            .catch(err => console.error(err.response))
        },
        fetchArticle({commit, getters}, ) {
            axios({
                url: '',
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_ARTICLE', res.data)
            })
            .catch(err => console.error(err.response))
        },
    },
}