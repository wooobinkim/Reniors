<template>
  <div>
    <div class="head2">
        <router-link style="text-decoration:none; color: white;" class="mx-3" :to="{name: 'QuestionList'}">답변 작성</router-link>
        <router-link style="text-decoration:none; color: white;" class="mx-3" :to="{name: 'VideoPracticeList'}">화상 연습</router-link>
    </div>
    <div>
        <p class="my-0" style="text-align:start">
            질문에 대한 답변을 미리 작성해보세요!
        </p>
        <p style="text-align:start">
            화상면접 연습 시 참고할 수 있습니다.
        </p>
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

<style>
.head2{
    background-color: #FF843E;
    height: 48px;
}
</style>