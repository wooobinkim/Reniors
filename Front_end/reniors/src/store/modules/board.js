import router from '@/router'
import axios from "axios"
import _ from 'lodash'
import drf from '@/api/drf'


export default{
    state: {
        token: localStorage.getItem('jwt') || '',
        currentUser: {},
        article: {},
        articles: [],
        comment: {},
        comments: [],

    },
    
    getters: {
        isLoggedIn: state => !!state.token,
        authHeader : state => ({
             Authorization : `JWT ${state.token}`,
             "Content-Type": "Application/json"
            }),
        currentUser: state=> state.currentUser,
        article: state => state.article,
        articles: state => state.articles,
        isArticle: state => !_.isEmpty(state.article),
        isAuthor: (state, getters) => {
            return state.article.user?.pk === getters.currentUser.pk
        },
        comment: state => state.comment,
        comments: state => state.comments,
    },

    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_CURRENT_USER: (state, user) => state.currentUser = user,
        SET_ARTICLE: (state, article) => state.article = article,
        SET_ARTICLES: (state, articles) => state.articles = articles,
        SET_COMMENT: (state, comment) => state.comment = comment,
        SET_COMMENTS: (state, comments) => state.comments = comments,

    },

    actions: {
        createArticle({getters, commit}, article){
            axios({
                url: drf.board.new(),
                method: 'post',
                data: {article},
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_ARTICLE', res.data)
                router.push({name:'boardDetail', params:{board_id: getters.article.pk}, query: {timestamp: Date.now()}})
            })
        },
        updateArticle({commit, getters}, {article_pk, title, content}) {
            axios({
                url: drf.board.detail(article_pk),
                method: 'put',
                data: {title, content},
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_ARTICLE', res.data)
                router.push({name: 'boardDetail', params:{ board_id:article_pk}, query: {timestamp: Date.now()}})
            })
        },
        deleteArticle({commit, getters}, {article_pk}) {
            if (confirm('정말 삭제하시겠습니까?')) {
                axios({
                    url: drf.board.detail(article_pk),
                    method: 'delete',
                    headers: getters.authHeader,
                })
                .then(() => {
                    commit ('SET_ARTICLE', {})
                    router.push({name: 'board', params: {}})
                })
            }
        },
        fetchArticles({commit, getters}) {
            axios({
                url: drf.board.get(),
                method: 'post',
                data: JSON.stringify({
                    "categoryId": 1,
                    "boardId": null,
                    "name": null,
                    "title": null
                }),
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_ARTICLES', res.data)
            })
            .catch(err => console.error(err.response))
        },
        fetchArticle({commit, getters}, article_pk) {
            axios({
                url: drf.board.detail(article_pk),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_ARTICLE', res.data)
            })
            .catch(err => console.error(err.response))
        },

        createComment({getters, commit}, {article_pk, content}){
            const comment = {content}
            axios({
                url: drf.board.comment(article_pk),
                method: 'post',
                data: comment,
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_COMMENT', res.data)
                router.push({name:'boardDetail', params:{board_id: getters.article.pk}})
            })
        },
        updateComment({commit, getters}, { article_pk, comment_pk, content}) {
            const comment = {content}
            axios({
                url: drf.board.commentEdit(article_pk, comment_pk),
                method: 'put',
                data: comment,
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_COMMENT', res.data)
            })
        },
        deleteComment({commit, getters}, {article_pk, comment_pk}) {
            if (confirm('정말 삭제하시겠습니까?')) {
                axios({
                    url: drf.board.commentEdit(article_pk, comment_pk),
                    method: 'delete',
                    data: {},
                    headers: getters.authHeader,
                })
                .then(res => {
                    commit ('SET_COMMENT', res.data)
                    router.push({name:'boardDetail', params:{board_id: getters.article.pk}})
                })
            }
        },
        fetchComments({commit, getters}, article_pk) {
            axios({
                url: drf.board.comment(article_pk),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit ('SET_COMMENTS', res.data)
            })
            .catch(err => console.error(err.response))
        },      
        

    },
}