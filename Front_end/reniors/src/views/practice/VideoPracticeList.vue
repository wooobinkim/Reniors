<template>
  <div>
    <div class="head2">
        <router-link  class="mx-3 rl" :to="{name: 'QuestionList'}">답변 작성</router-link>
        <router-link  class="mx-3 rl" :to="{name: 'VideoPracticeList'}">화상 연습</router-link>
    </div>
    <div>
        <p class="my-0" style="text-align:start">
            해당 질문을 눌러
        </p>
        <p style="text-align:start">
            화상면접을 경험해보세요!
        </p>
    </div>

    <question-item
        v-for="(question,idx) in questions"
        :key="idx"
        :question="question.question"
        :idx="idx"
    ></question-item>

  </div>
</template>
<script>

export default{ 
    name:'VideoPracticeList',
    components:{},
    data(){
        return{
            sampleData:''
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{}
}
</script>

<style scoped>
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