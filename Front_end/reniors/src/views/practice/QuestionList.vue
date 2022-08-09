<template>
  <div class="total">
    <div class="head2">
        <router-link  class="mx-3 rl" :to="{name: 'QuestionList'}">답변 작성</router-link>
        <router-link  class="mx-3 rl" :to="{name: 'VideoPracticeList'}">화상 연습</router-link>
    </div>
    <div class="headsub">
        <p class="my-0" style="text-align:start; font-size: 14px;">
            질문에 대한 답변을 미리 작성해보세요!
        </p>
        <p style="text-align:start; font-size: 14px;">
            화상면접 연습 시 참고할 수 있습니다.
        </p>
    </div>

    <question-item
        v-for="(question,idx) in questions"
        :key="idx"
        :question="question.question"
        :idx="idx"
        :id="question.id"
        :checklist="checklist"
    ></question-item>
  </div>
</template>
<script> 

import QuestionItem from '@/components/practice/QuestionItem.vue';
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'QuestionList',
    components:{QuestionItem},
    data(){
        return{
            sampleData:''
        };
    },
    computed: {
        ...mapGetters(['questions', 'checklist'])
    },
    setup(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchQuestions', 'fetchChecklist'])
    },
    created(){
        this.fetchQuestions()
        this.fetchChecklist()
    },
}
</script>

<style scoped>
.total{
    background-color: #FFF5F0;
}
.headsub{
    margin: 16px 16px 8px 16px;
}
.rl{
    text-decoration:none;
    color: white;
}
.rl:hover{
    font-weight: bold;
}

.head2{
    background-color: #FF843E;
    height: 48px;
    display: flex;
    align-items: center;
    justify-content: space-around;
}

.head2 a.router-link-exact-active{
    font-weight: bold;
}

.question{
    margin:4px 20px;
    padding-left: 8px;
    height: 88px;
    width: 320px;
    border-radius: 10px;
    border:1px solid #FF843E;
    box-shadow: 1px 1px 1px gray;
    display: flex;
    align-items: center;
}
p{
    margin: 0px
}
</style>