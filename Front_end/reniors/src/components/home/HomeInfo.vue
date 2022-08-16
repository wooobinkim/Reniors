<template>
    <router-link class="home-info" :to="{ name: 'companyjobopening' }">
      <p>시니어를 위한</p>
      <p>공고 보러가기 <i class="bi bi-arrow-right-square-fill"></i></p>
      
    </router-link>
</template>

<script>
export default {
  name: 'HomeInfo',
}
</script>

<style scoped>
.home-info {
  background:linear-gradient(
    90deg,
    var(--color-red-3) -12%,
    var(--color-red-2) 37%,
    var(--color-orange-1) 72%,
    var(--color-orange-2) 110%
  );
  border-radius: 0.3rem;
  margin: auto;
  width: 100%;
  padding: 40px;
  display: flex;
  justify-content: center;
  align-content: center;
  text-decoration: none;
}
p{
  margin: 0;
  color: white;
  font-size: 26px;
  font-weight: bold;
}
.bi-arrow-right-square-fill{
  font-size: 26px;
  color: white;
  margin-left: 5px;
}
@media(max-width:760px){
  .home-info{
    display: block;
    padding: 20px;
    width: 90%;
    margin: 0 5%;
  }
}
</style>