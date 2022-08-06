<template>
  <div>
    <ul class="jobList">
        <li class="jobItem" v-for="(job, index) in parents" :key="index" :job="job">
            <router-link :to="{name: boardMain, params:{category_id: job.id}}">
                <p>{{ job.name }}</p>
            </router-link>
        </li>
    </ul>
  </div>
</template>

<script> 
import { mapActions, mapGetters} from 'vuex';

export default{ 
    name:'BoardHeadSlider',
    components:{},
    data(){
        return{
            sampleData:''
        };
    },
    setup(){},
    created(){
        this.fetchParents(),
        this.fetchInterest()
    },
    computed:{
        ...mapGetters(['interest', 'parents'])
        
    },
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchInterest', 'fetchParents'])
    }
}
</script>

<style scoped>
.jobList{
    background-color:#FF843E ;
    display: flex; 
    margin: 0; 
    padding: 0; 
    flex-wrap: no-wrap; 
    overflow-x: scroll;
    overflow-y: hidden;
    align-content: center;
}

.jobItem{    
    margin: 10px;
    padding: 10px; 
    list-style: none; 
    flex: 0 0 auto;
}
.jobItem p{
    margin: 0px;
    color:#FFEDBF;
    font-size: 16px;
;
}
.jobItem a{
    text-decoration: none;
}
.jobItem a.router-link-exact-active p{
    color: white;
    font-weight: bold;
    font-size: 16px;


}

</style>>

