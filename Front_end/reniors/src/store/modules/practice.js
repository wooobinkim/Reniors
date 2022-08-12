import axios from "axios"
import router from "@/router"

export default{
    state: {
        questions: [],
        answer: {},
        checklist: [],
        records: [],
        selected: [],
        

    },

    getters: {
        questions: state => state.questions,
        answer: state => state.answer,
        checklist: state => {
          var list = []
          for (let index = 0; index < state.checklist.length; index++) {
              list.push(state.checklist[index].questionId)
          }
          console.log(list.sort());
          return list.sort()
        },
        records: state => state.records,
        selected: state => state.selected,
    },

    mutations: {
      CLEAR_QUESTIONS : (state) => {
        state.questions = null},
        SET_QUESTIONS: (state, questions) => {
          state.questions = questions
       },
        SET_ANSWER: (state, answer) => state.answer = answer,
        SET_CHECKLIST: (state, checklist) => state.checklist = checklist,
        SET_RECORDS: (state, records) => state.records = records,
        SET_SELECTED: (state, selected) => {
          state.selected = selected
        },
        PUT_SELECTED: (state, selected) => state.selected.push(selected)

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
        async fetchAnswer({getters, commit}, questionId) {
            await axios({
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
              }).catch((err)=> {
                console.log(err);
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


        pushSelected({commit, getters}, questionId) {
          if(getters.selected.includes(questionId)){
            let filtered = getters.selected.filter((el) => el !== questionId)
            commit("SET_SELECTED", filtered)
          }else{
            commit("PUT_SELECTED", questionId)
          }
          
        },

        saveRecording({getters}, {fileName, URL}) {
          axios({
            url: "https://i7b307.p.ssafy.io/api/recording",
            method: "post",
            data: JSON.stringify({
              fileName: fileName,
              recordURL: URL,
            }),
            headers: getters.authHeader,
          }).then(router.push({ name: "PracticeBox" }));

        },
        fetchRecording({getters, commit}){
          axios({
            url: "https://i7b307.p.ssafy.io/api/recording/list",
            method: "get",
            headers: getters.authHeader,
          })
          .then((res) => {
            commit("SET_RECORDS", res.data)
          
          })
        }

    }
}