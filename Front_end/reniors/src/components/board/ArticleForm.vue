<template>

    <div class="container">
      
      <form @submit.prevent="onSubmit" class="form-group row-cols-8" >
      <br>
      <br>
        <div>
          <label for="title" style="text-align:start">제목</label>
          <input type="text" class="form-control my-0" id="title" placeholder="제목을 입력해주세요." v-model="newArticle.title">
        </div>
        <div class="form-group">
          <label for="content">내용</label>
          <input style="height: 150px; vertical-align:text-top ;" type="text" class="form-control" id="content" placeholder="내용을 입력해주세요." v-model="newArticle.contents">
        </div>
        <br>
        <br>
        <button type="submit" class="Btn">등록</button>
      </form>
    </div>

</template>
<script>
import { mapActions } from 'vuex';

export default{ 
    name:'ArticleForm',
    components:{},
    props: {
        article: Object,
        action: String,
        category_pk: null
    },
    data(){
        return{
            newArticle: {
                title: this.article.title,
                contents: this.article.content,
            }
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['createArticle', 'updateArticle']),
        onSubmit(){
            if (this.action === 'create') {
                this.createArticle({ article: this.newArticle})
            } else if (this.action === 'update') {
                const payload = {
                    article_pk : this.article_pk,
                    ...this.newArticle
                }
                this.updateArticle(payload)
            }
        }
    }
}
</script>

<style>
.forminfo {
    color: #8A8A8A;
    font-weight: 400;
    font-size: 13px;
    margin-bottom: 5px;
    text-align: left;
  }

  .Btn {
    background-color: var(--color-red-2);
    width: 100%;
    height: 40px;
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;

    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }
  
  </style>