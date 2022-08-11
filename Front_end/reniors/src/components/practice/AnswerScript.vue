<template>
  <div class="total">
    <div v-if="checklist.includes(selectedQ[idx])" class="answer">
            {{answer.answer}}
    </div>
    <div v-if="!checklist.includes(selectedQ[idx])" class="answer">
            <p></p>
    </div>
  </div>
</template>
<script>

import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'AnswerScript',
    components:{},
    props:{
        idx: Number,
        checklist: Array,
        selectedQ: Array
    },  
    data(){
        return{
            sampleData:''
        };
    },
    computed:{
        ...mapGetters(['answer'])
    },
    created(){
        this.fetchAnswer(this.idx+1)
    },

    methods:{
        ...mapActions(['fetchAnswer'])
    },
    watch:{
        idx: function(data){
            this.fetchAnswer(data+1)
        }
    }
}
</script>

<style scoped>
.total{
    display: flex;
    justify-content: center;
    animation: fadeInUp 1s;
    
}
@keyframes fadeInUp{
    from{
        opacity: 0;
        transform: translateY(20px);
    }
    to{
        opacity: 1;
        transform: none;
    }
}
.answer{
    background-color: white;
    overflow: scroll;
    padding: 2px;
    /* position: fixed; */
    bottom: 120px;
    height: 150px;
    width: 344px;
    border: solid #D9D9D9 1px;
    border-radius: 5px;
}
</style>