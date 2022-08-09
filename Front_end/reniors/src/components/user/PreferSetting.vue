<template>
  <div>
    {{ parents }}

  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "PreferSetting",
  components: {},
  data() {
    return {
      example: '',
    }
  },
  computed: {
    ...mapGetters(['parents'])
  },
  setup() {},
  created() {
    this.fetchParents()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchParents'])
  }
}
</script>