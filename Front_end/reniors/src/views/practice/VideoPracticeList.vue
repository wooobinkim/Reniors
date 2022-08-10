<template>
  <div class="total">
    <div class="head2">
        <router-link  class="mx-3 rl" :to="{name: 'QuestionList'}">답변 작성</router-link>
        <router-link  class="mx-3 rl" :to="{name: 'VideoPracticeList'}">화상 연습</router-link>
    </div>
    <div class="headsub">
        <p class="my-0" style="text-align:start; font-size: 14px;">
            해당 질문을 눌러
        </p>
        <p style="text-align:start; font-size: 14px;">
            화상면접을 경험해보세요!
        </p>
    </div>

    <video-question
        v-for="(question,idx) in questions"
        :key="idx"
        :question="question.question"
        :idx="idx"
        :id="question.id"
    ></video-question>


    <router-link :to="{name: 'PracticePage'}">
        <div class="submit">
            <button class="Btn">등록</button>
        </div>
    </router-link>
  </div>
</template>
<script>

import VideoQuestion from '@/components/practice/VideoQuestion.vue'
import { mapActions, mapGetters } from 'vuex';
export default{ 
    name:'VideoPracticeList',
    components:{ VideoQuestion },
    data(){
        return{
            sampleData:''
        };
    },
    setup(){},
    created(){
        this.fetchQuestions()
    },
    computed:{
        ...mapGetters(['questions'])
    },
    methods:{
        ...mapActions(['fetchQuestions'])
    }
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
.submit{
    position: fixed;
    bottom: 50px;
    width: 100%;
    margin: 0;
    background-color: white;
    border-top: solid 0.5px #FFEDBF;

}
.Btn {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color:var(--color-red-2);
    height: 40px;
    width: 340px;
    margin: 8px 0;
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 16px;

    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
}
</style>