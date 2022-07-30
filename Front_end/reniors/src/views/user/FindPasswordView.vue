<template>
  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
    <div class="carousel-indicators">
      <button type="button" style="background-color:red;" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" style="background-color:red;" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" style="background-color:red;" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <h1>ddfsfsdf</h1>
        <input type="text" name="" id="">
      </div>
      <div class="carousel-item">
        <img src="" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="" class="d-block w-100" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" style="background-color:red;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <!-- <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span> -->
    </button>
    <button class="carousel-control-next" style="background-color:red;" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      example: '',
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>