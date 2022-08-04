<template>
  <div>
    <div class="question">
        <p>Q{{idx + 1}}. {{question}}</p>
    </div>
  </div>
</template>
<script>

export default{ 
    name:'QuestionItem',
    components:{},
    props: {
        'question': String,
        'idx' : Number,
    },
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
.question p{
    margin: 0px 8px 0px;
}
</style>