<template>
  <div class="total">
    <div >
        <div class="question" @click="onCheck(id)">
            <p>Q{{idx + 1}}. {{question}}</p>
            <i v-if="selected.includes(id)" class="bi bi-check-circle-fill" style="color:#37BF99; margin: 0 16px;"></i>
        </div>
    </div>

  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';


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
        ...mapActions(['pushSelected']),
        onCheck(id){
            this.pushSelected(id)
        }
    },
    computed:{
        ...mapGetters(['selected'])
    }
    
    

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
    display: flex;
    justify-content: space-between;
}
.question p{
    margin: 0px 8px 0px;
}

</style>