<template>
  <div class="total">
    <div >
        <div v-if="!selected.includes(id)" class="question" @click="onCheck(id)">
            <p>Q{{idx + 1}}. {{question}}</p>
        </div>
        <div v-if="selected.includes(id)" class="question" @click="onCheck(id)">
            <p>Q{{idx + 1}}. {{question}}</p>
            <div class="number">
                <p style="color:white; font-size:16px; font-weight: bold;">{{selected.indexOf(id)+1}}</p>
            </div>
            <!-- <i  class="bi bi-check-circle-fill" ></i> -->
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
.number{
    background-color: #37BF99; 
    border-radius: 30px;
    margin: 0 16px;
    width: 22px;
    height: 22px;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: gray 1px 1px 1px;
}
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
.question:hover{
    color: #FF843E;;
}

</style>