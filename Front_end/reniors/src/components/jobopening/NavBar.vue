<template>
  <div class="jobopening-navbar">
    <router-link :to="{ name: 'Jobopening' }" class="jobopening-navbar-left">
      <svg class="navbar-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 0C114.6 0 0 114.6 0 256c0 141.4 114.6 256 256 256s256-114.6 256-256C512 114.6 397.4 0 256 0zM384 288H205.3l49.38 49.38c12.5 12.5 12.5 32.75 0 45.25s-32.75 12.5-45.25 0L105.4 278.6C97.4 270.7 96 260.9 96 256c0-4.883 1.391-14.66 9.398-22.65l103.1-103.1c12.5-12.5 32.75-12.5 45.25 0s12.5 32.75 0 45.25L205.3 224H384c17.69 0 32 14.33 32 32S401.7 288 384 288z"/></svg>
      <h2>채용공고</h2>
    </router-link>
    <div class="jobopening-navbar-right">
      <svg @click="bookmark" class="navbar-svg" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"><path d="M48 0H336C362.5 0 384 21.49 384 48V487.7C384 501.1 373.1 512 359.7 512C354.7 512 349.8 510.5 345.7 507.6L192 400L38.28 507.6C34.19 510.5 29.32 512 24.33 512C10.89 512 0 501.1 0 487.7V48C0 21.49 21.49 0 48 0z"/></svg>
      <router-link to="profile" class="navbar-profile">
        <img src="" alt="">
      </router-link>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  name: 'NavBar',
  setup() {
    const store = useStore()

    const bookmark = () => store.dispatch('jobopening/bookmark')

    return {
      bookmark
    }
  }
}
</script>

<style>
.jobopening-navbar {
  position: sticky;
  top: 0;
  display: flex;
  justify-content: space-between;
  background-color: white;
  padding: 12px;
}

.jobopening-navbar-left, .jobopening-navbar-right {
  display: flex;
  text-decoration: none;
}

.navbar-svg {
  filter: invert(87%) sepia(3%) saturate(6218%) hue-rotate(325deg) brightness(106%) contrast(100%);
  height: 24px;
  width: 24px;
}

.jobopening-navbar-right > .navbar-svg:hover {
  cursor: pointer;
}

.jobopening-navbar h2 {
  color: var(--color-yellow-1);
  font-size: 22px;
  font-weight: bold;
  margin: 0;
  margin-left: 10px;
}

.navbar-profile {
  margin-left: 10px;
  height: 24px;
  width: 24px;
}

.navbar-profile > img {
  border-radius: 5rem;
  height: 100%;
  width: 100%;
}
</style>