import router from '@/router'
import axios from "axios"
import _ from 'lodash'
import drf from '@/api/drf'


export default{
    state: {
        currentUser: {},
        article: {},
        articles: [],
        comment: {},
        comments: [],

    },
    
    getters: {
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
        fetchArticles({commit, getters}, categoryId) {
            axios({
                url: drf.board.get(),
                method: 'post',
                data: JSON.stringify({
                    "categoryId": categoryId,
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

        createArticle({getters, commit, dispatch}, {categoryId, contents, title}){
            axios({
                url: drf.board.new(),
                method: 'post',
                data: {
                    'categoryId':categoryId, 
                    'contents' : contents, 
                    'title' :title
                },
                headers: getters.authHeader,
            })
            .then(res => {
                dispatch('fetchArticle', res.data.boardId)
            })
            .then(res => {
                commit('SET_ARTICLE', res.data)
                router.push({name:'boardDetail', params:{'category_id': categoryId, 'board_id': getters.article.boardId}})
            })
        },
        updateArticle({getters, dispatch, commit}, {categoryId, article_pk, title, contents}) {
            axios({
                url: drf.board.detail(article_pk),
                method: 'put',
                data: {title, contents},
                headers: getters.authHeader,
            })
            .then(
                dispatch('fetchArticle', article_pk)
            )
            .then(res => {
                commit('SET_ARTICLE', res.data)
                router.push({name:'boardDetail', params:{'category_id': categoryId, 'board_id': getters.article.boardId}})
            })
        },
        deleteArticle({commit, getters}, {article_pk, categoryId}) {
            if (confirm('정말 삭제하시겠습니까?')) {
                axios({
                    url: drf.board.detail(article_pk),
                    method: 'delete',
                    headers: getters.authHeader,
                })
                .then(() => {
                    commit ('SET_ARTICLE', {})
                    router.push({name: 'boardMain', params: {'category_id': categoryId}})
                })
            }
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