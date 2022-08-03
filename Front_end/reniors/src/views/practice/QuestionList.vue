<template>
  <div>
    <div class="head2">
        <router-link  class="mx-3 rl" :to="{name: 'QuestionList'}">답변 작성</router-link>
        <router-link  class="mx-3 rl" :to="{name: 'VideoPracticeList'}">화상 연습</router-link>
    </div>
    <div>
        <p class="my-0" style="text-align:start">
            질문에 대한 답변을 미리 작성해보세요!
        </p>
        <p style="text-align:start">
            화상면접 연습 시 참고할 수 있습니다.
        </p>
    </div>
    <div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
        <div class="question">
            <p>q1. 간단한 자기소개 부탁드립니다.</p>
        </div>
    </div>
  </div>
</template>
<script>

export default{ 
    name:'QuestionList',
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