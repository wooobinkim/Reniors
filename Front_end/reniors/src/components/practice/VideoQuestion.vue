<template>
  <div class="total">
    <div >
        <router-link :to="{name: 'PracticePage'}" @click="this.setQuestion({'question': this.question, 'id': this.id})">
            <div class="question">
                <p>Q{{idx + 1}}. {{question}}</p>
            </div>
        </router-link>
    </div>

  </div>
</template>
<script>
import { mapActions } from 'vuex';


export default{ 
    name:'QuestionItem',
    components:{},
    props: {
        'question': String,
        'idx' : Number,
        'id': Number,
    },
    data(){
        return{
        };
    },
    methods:{
        ...mapActions(['setQuestion'])
    },
    
    

}
</script>

<style scoped>
.total{
    display: flex;
    justify-content: center;
    
}
a{
    text-decoration: none;
    color: black;
}
a:hover{
    color: #FF843E;
}
.question{
    margin:4px;
    padding-left: 8px;
    height: 88px;
    width: 336px;
    border-radius: 10px;
    border:1px solid #FF843E;
    box-shadow: 1px 1px 1px gray;
    display: flex;
    align-items: center;
    background-color: white;
}
.question p{
    margin: 0px 8px 0px;
}
.question2{
    margin:4px;
    padding-left: 8px;
    height: 88px;
    width: 336px;
    border-radius: 10px;
    border:1px solid #FF843E;
    box-shadow: 1px 1px 1px gray;
    display: flex;
    align-items: center;
    background-color: white;
    display: flex;
    justify-content: space-between;
}
.question2 p{
    margin: 0px 8px 0px;
}
</style>