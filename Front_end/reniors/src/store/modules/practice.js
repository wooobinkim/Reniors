import axios from "axios"
// import drf from '@/api/drf'
import router from "@/router"

export default{
    state: {
        questions: [],
        answer: {},
        checklist: [],
        question: '',
        id: null,

    },

    getters: {
        questions: state => state.questions,
        answer: state => state.answer,
        checklist: state => {
          var list = []
          for (let index = 0; index < state.checklist.length; index++) {
              list.push(state.checklist[index].questionId)
          }
          return list
        },
        question: state => state.question,
        id: state => state.id,
    },

    mutations: {
        SET_QUESTIONS: (state, questions) => state.questions = questions,
        SET_ANSWER: (state, answer) => state.answer = answer,
        SET_CHECKLIST: (state, checklist) => state.checklist = checklist,
        SET_QUESTION: (state, question) => state.question = question,
        SET_ID: (state, id) => state.id = id,

    },

    actions: {
        fetchQuestions({getters, commit}) {
            axios({
              url: "https://i7b307.p.ssafy.io/api" + "/questions",
              method: "get",
              headers: getters.authHeader,
            })
              .then((res) => {
                commit("SET_QUESTIONS", res.data);
              })
              .catch((err) => console.error(err.response));
        },
        createAnswer({getters, commit}, {questionId, content}) {
            axios({
              url: "https://i7b307.p.ssafy.io/api" + "/questions" + `/${questionId}` + "/answers",
              method: "post",
              data: JSON.stringify({ answer: content }),
              headers: getters.authHeader,
            }).then((res) => {
              commit("SET_ANSWER", res.data);
              router.push({ name: "QuestionList" });
            });
        },
        fetchAnswer({getters, commit}, questionId) {
            axios({
              url:
                "https://i7b307.p.ssafy.io/api" +
                "/questions" +
                `/${questionId}` +
                "/answers",
              method: "get",
              headers: getters.authHeader,
            })
              .then(
                (res) => {
                  commit("SET_ANSWER", res.data);
              })
        },
        updateAnswer({getters, commit}, {questionId, content}) {
            axios({
              url:
                "https://i7b307.p.ssafy.io/api" +
                "/questions" +
                `/${questionId}` +
                "/answers",
              method: "put",
              data: JSON.stringify({ answer: content }),
              headers: getters.authHeader,
            }).then((res) => {
              commit("SET_ANSWER", res.data);
              router.push({ name: "QuestionList" });
            });
        },
        fetchChecklist({getters, commit}) {
          axios({
            url:
              "https://i7b307.p.ssafy.io/api" +
              "/questions" + "/answers" + "/list",
            method: "get",
            headers: getters.authHeader,
          })
          .then((res) => {
            commit("SET_CHECKLIST", res.data)
          })
        },

        setQuestion({commit}, {question, id}) {
          commit("SET_QUESTION", question)
          commit("SET_ID", id)
        },

        // recordStart({getters, commit}){

        // }

    }
}