<template>
  <div>
    <ul class="home-calendar-list">
      <li class="home-calendar-item" v-for="todo, index in todos" :key="index" :id="'homeCalendarItem'+index">
        <div>{{ todo.title }}</div>
        <div>{{ todo.started_at }}</div>
        <div>{{ todo.finished_at }}</div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HomeCalendarList',
  setup() {
    const todos = [
      {
        title: '관심 채용공고 제목 1 공고기간 끝남',
        contents: '관심 채용공고 내용111111',
        contents_img_path: 'https://i.pinimg.com/736x/b5/1f/dd/b51fdd7fca9aa714db29141ebd84f453.jpg',
        started_at: '2022-07-20',
        finished_at: '2022-07-30'
      },
      {
        title: '관심 채용공고 제목 2',
        contents: '관심 채용공고 222222222222',
        contents_img_path: 'https://i.pinimg.com/736x/b5/1f/dd/b51fdd7fca9aa714db29141ebd84f453.jpg',
        started_at: '2022-07-28',
        finished_at: '2022-08-04'
      },
      {
        title: '관심 채용공고 제목 3',
        contents: '관심 채용공고 내용3',
        contents_img_path: 'https://i.pinimg.com/736x/b5/1f/dd/b51fdd7fca9aa714db29141ebd84f453.jpg',
        started_at: '2022-08-01',
        finished_at: '2022-08-20'
      },
      {
        title: '관심 채용공고 제목 4 미래',
        contents: '관심 채용공고44444',
        contents_img_path: 'https://i.pinimg.com/736x/b5/1f/dd/b51fdd7fca9aa714db29141ebd84f453.jpg',
        started_at: '2022-08-10',
        finished_at: '2022-08-20'
      },
    ]

    return {
      todos
    }
  },
  mounted() {
    const activeItem = document.querySelector('#homeCalendarItem1')
    activeItem.classList.add('active')
  }
}
</script>

<style>
.home-calendar-list {
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
}

.home-calendar-item {
  background-color: var(--color-orange-4);
  border: 1px solid var(--color-orange-1);
  border-radius: 0.3rem;
  margin: 10px;
  padding: 10px;
  color: black;
  list-style: none;
  flex: 0 0 auto;
}

.home-calendar-item.active {
  background-color: var(--color-orange-1);
  color: white;
}
</style>