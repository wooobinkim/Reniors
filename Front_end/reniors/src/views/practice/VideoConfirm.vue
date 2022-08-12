<template>
  <div>
    <video autoplay :src='this.record.recordURL' controls></video>
  </div>
</template>
<script>

import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'VideoConfirm',
    components:{},
    data(){
        return{
            myrecords: [],
            idx : this.$route.params.videoId,
            record: {}
        };
    },
    computed:{
        ...mapGetters(['records'])
    },
    created(){
        this.fetchRecording()
    },
    watch:{
        records: function(data){
            this.myrecords = { ...data }
            this.record = {...data}[this.idx]
        },
    },
    methods:{
        ...mapActions(['fetchRecording']),

    }
        
}
</script>