<template>
  <div>
    <p>연습함</p>
    
    <div v-for="(record, index) in records" :key="index" >
        <!-- 비디오섬네일 -->
        <video :src='record.recordURL'></video>
        
        <!-- 비디오재생 -->
        <video autoplay :src='record.recordURL' controls></video>
        <!-- <video preload="metadata" src={{url}}></video> -->
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'PracticeBox',
    components:{},
    data(){
        return{
            sampleData:''
        };
    },
    computed:{
        ...mapGetters(['records'])
    },
    created(){
        this.fetchRecording()
    },
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchRecording'])
    }
}
</script>