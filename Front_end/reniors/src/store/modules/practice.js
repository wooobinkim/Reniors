import axios from "axios"
import drf from '@/api/drf'
import router from "@/router"

export default{
    state: {
        questions: [],
        answer: {},

    },

    getters: {
        questions: state => state.questions,
        answer: state => state.answer,

    },

    mutations: {
        SET_QUESTIONS: (state, questions) => state.questions = questions,
        SET_ANSWER: (state, answer) => state.answer = answer,

    },

    actions: {
        fetchQuestions({getters, commit}) {
            axios({
                url: drf.question.get(),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_QUESTIONS', res.data)
            })
            .catch(err => console.error(err.response))
        },
        createAnswer({getters, commit}, {questionId, content}) {
            axios({
                url: drf.question.answerPost(questionId),
                method: 'post',
                data: {content},
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_ANSWER', res.data)
                router.push({name: 'QuestionList'})
            })
        },
        fetchAnswer({getters, commit}, {questionId, answerId}) {
            axios({
                url: drf.question.answer(questionId, answerId),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_ANSWER', res.data)
            })
            .catch(err => console.error(err.response))
        },
        updateAnswer({getters, commit}, {questionId, answerId, content}) {
            axios({
                url: drf.question.answer(questionId, answerId),
                method: 'put',
                data: {content},
                headers: getters.authHeader,
            })
            .then(res =>{
                commit('SET_ANSWER', res.data)
                router.push({name: 'QuestionList'})
            })
        },

    }
}