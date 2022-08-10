<template>
  <div>
    <h3>Fin!</h3>
    <h2>{{ store.state }}</h2>
    <b-button @click="store.setState('name', 'Carlos')"
      >Cambiar nombre</b-button
    >
    <b-button @click="$emit('set-step', 1)">Ir al paso 1</b-button>
    <div v-for="(user, i) of store.state.users" :key="i">{{ user }}</div>
  </div>
</template>

<script>
export default {
  props: ["store"],
};
</script>

<style></style>
