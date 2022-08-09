<template>
  <div>
    <div class="head">
        <router-link :to="{name: 'QuestionList'}">
            <i class="bi bi-arrow-left-circle-fill" style="color:#FFD39B; font-size: 24px; margin:0 16px"></i>
        </router-link>
        <p>답변 작성</p>
    </div>

    <div class="question">
            <p>Q{{question_id}}. {{questions[questionId-1].question}}</p>
    </div>
    <answer-form
        :answerObject="answer"
        action="update"
        
    ></answer-form>
  </div>
</template>
<script>


import AnswerForm from '@/components/practice/AnswerForm.vue'
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'AnswerUpdate',
    components:{ AnswerForm },
    data(){
        return{
            questionId: this.$route.params.question_id,
        };
    },
    setup(){},
    created(){
        this.fetchAnswer(this.questionId)
    },
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchAnswer'])
    },
    computed:{
        ...mapGetters(['answer', 'questions'])
    }
}
</script>

<style scoped>
.head{
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #FF843E;
  margin: 0;
}
.head p{
  font-size: 20px;
  font-weight: bold;
  color: #FFB400;
  margin: 0 16px;

}
.question{
    margin: 16px 8px 4px 8px;
    height: 48px;
    padding-left: 8px;
    display: flex;
    align-items: center;
    background-color: white;
}
.question p{
    margin: 0px 8px 0px;
    font-size: 14px;
    font-weight: bold;
}
</style>