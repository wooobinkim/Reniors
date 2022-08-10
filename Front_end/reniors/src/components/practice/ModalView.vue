<template>
  <div class="modal">
    <div class="overlay" @click="$emit('close')"></div>
    <div class="modal-card">
        <p>here modal~~</p>
    </div>

  </div>
</template>
<script>

export default{ 
    name:'ModalView',
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
.modal{
    width: 100%;
    height:100%;
    position: fixed;
    left:0;
    top: 0;
}
.overlay{
    opacity: 0.5;
    background-color: black;
}
.modal-card{
    position: relative;
    max-width: 80%;
    margin: 30px;
    padding: 20px;
    background-color: white;
    min-height: 500px;
    z-index: 10;
    opacity: 1;
}
</style>