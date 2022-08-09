<template>
  <div>
    <board-head-slider></board-head-slider>
    <div class="title">
        <router-link :to="{name: 'boardMain', params:{category_id: categoryId}}">
            <i class="bi bi-arrow-left-circle-fill" style="color:#FFD39B; font-size: 24px; margin:0 16px"></i>
        </router-link>
        <p>{{parents[categoryId - 1].name}}</p>
    </div>

    <div class="box">
        <!-- 제목, 글쓴이, 시간 -->
        <div>
            <p  class="title2">{{article.title}}</p>
            <div class="bottom">
                <img class="person" src="https://i7b307.p.ssafy.io/images/user/1" alt="person">
                <p  class="userName">{{article.userName}} |</p>
                <p class="time">{{article.updatedAt.slice(0,4)}}.{{article.updatedAt.slice(5,7)}}.{{article.updatedAt.slice(8,10)}}</p>
            </div>
        </div>
        <!-- 수정 삭제 -->
        <div class="icons">
            <div v-if="isAuthor">
            </div>
                <router-link :to="{ name: 'boardUpdate', parmas:{'category_id': categoryId, 'board_id': articleId}}">
                    <i class="bi bi-pencil-square" style="font-size:20px ;  margin: 4px;color: #6d6d6d ;" ></i>
                </router-link>
            <i class="bi bi-trash-fill" style="font-size:20px; color: #6d6d6d ; margin: 4px;"  @click="deleteArticle({article_pk: articleId, categoryId: categoryId})"></i>
        </div>
    </div>

    <!-- 글 본문 -->
    <div class="contents">
        <p>{{article.contents}}</p>
    </div>

    <!-- 댓글 컴포넌트 -->
    <comment-list></comment-list>
  </div>
</template>
<script>

import BoardHeadSlider from '@/components/board/BoardHeadSlider.vue'
import CommentList from '@/components/board/CommentList.vue';
import { mapActions, mapGetters } from 'vuex';
export default{ 
    name:'BoardDetail',
    components:{ CommentList, BoardHeadSlider },
    data(){
        return{
            'categoryId': this.$route.params.category_id,
            'articleId': this.$route.params.board_id
        }
    },
    computed: {
        ...mapGetters(['article', 'isAuthor', 'currentUser', 'parents'])
    },
    setup(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchArticle', 'deleteArticle'])
    },
    created(){
        this.fetchArticle(this.articleId)
    },
}
</script>

<style scoped>
.header{
    height: 80px;
    display: flex;
    align-items: center;

}
.title{
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #FF843E;
}
.title p{
  font-size: 20px;
  font-weight: bold;
  color: #FFB400;
  margin: 0 16px;

}
.box{
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.box a{
    text-decoration: none;
    color: black;
    display: flex;
    justify-content: start;
    align-items: center;
}
.icons{
    margin: 16px;
    display: flex;
}

.title2{
    margin: 0 8px;
    font-size: 18px;
    display: flex;
    justify-content: start;
    font-weight: bold;

}
.bottom{
    display: flex;
}
.person{
    width: 24px;
    height: 24px;
    border-radius: 50%;
    margin: 0 4px;
}
.userName{
    margin: 0 8px;
    font-size: 14px;
    color: #6D6D6D;
    display: flex;
    justify-content: start;
    align-items: center;

}
.time{
    font-size: 12px;
    color: #6D6D6D;
    margin: 0;
    display: flex;
    justify-content: start;
    align-items: center;

}
.contents{
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: left;
    border-bottom: solid 2px #FF843E;
}
.contents p{
    font-size: 14px;
    margin: 8px 8px 16px 8px;
}
</style>