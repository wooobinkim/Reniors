<template>
  <div>  
      <form @submit.prevent="onSubmit" class="formGroup" >
        <div class="box">
          <label for="answer"></label>
          <textarea type="text" class="form-control content" id="answer" placeholder="내용을 입력해주세요." v-model="this.answer"></textarea>
        </div>
        <div class="submit">
            <button type="submit" class="Btn">저장</button>
        </div>
      </form>
    </div>
</template>
<script>
import { mapActions } from 'vuex';

export default{ 
    name:'AnswerForm',
    components:{},
    props:{
        answerObject: Object,
        action: String
    },
    data(){
        return{
            answer: this.answerObject.answer,
            questionId: this.$route.params.question_id,
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['createAnswer', 'updateAnswer']),
        onSubmit(){
            if (!this.answer){
                alert('내용을 작성해주세요!')
            }
            if (this.action === 'create' && this.answer) {
                this.createAnswer({ 
                    questionId: this.questionId,
                    content: this.answer
                })
            } else if (this.action === 'update') {
                this.updateAnswer({
                    questionId: this.questionId,
                    content: this.answer,

                })
            }
        }
    }
}
</script>

<style scoped>
.formGroup{
    margin: 8px 4px 0 4px;

}
.box{
    margin: 4px;
    display: flex;
    justify-content: center;
}
.content{
    margin: 8px 4px;
    height: 440px;
    padding: 8px 10px 8px 10px;
}

.Btn {
background-color:var(--color-red-2);
height: 40px;
width: 340px;
margin: 8px 0;
border-radius: 10px;
border: none;
color: white;
font-weight: bold;
font-size: 16px;

/* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
cursor: pointer;
}
</style>