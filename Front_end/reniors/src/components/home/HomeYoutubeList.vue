<template>
  <div>
    <h2 class="youtube-type">{{ type }}</h2>
    <ul class="youtube-list">
      <li class="youtube-item" v-for="(item, index) in items" :key="index">
        <a :href="item.url">
          <img :src="item.thumbnail">
          <div>
            <p class="hi">{{ item.title }}</p>
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HomeYoutubeList',
  props: {
    type: String,
    youtubes: Array,
  },
  setup(props) {
    const YOUTUBE_URL = 'https://www.youtube.com/watch?v='
    const items = []
    props.youtubes.map(youtube => {
      const item = {
        title: youtube.snippet.title,
        thumbnail: youtube.snippet.thumbnails.default.url,
        url: YOUTUBE_URL + youtube.id.videoId
      }
      items.push(item)
    })

    return {
      items,
    }
  }
}
</script>

<style>
.youtube-type {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
}

.youtube-list {
  display: flex;
  margin: 0;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
}

.youtube-item {
  background-color: var(--color-black-4);
  border-radius: 0.3rem;
  width: 300px;
  margin: 10px;
  padding: 10px;
  list-style: none;
  flex: 0 0 auto;
}

.youtube-item > a {
  display: flex;
}

.youtube-item p {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  margin: 0;
}
</style>