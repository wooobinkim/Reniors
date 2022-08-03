<template>
  <div>
    <h1>2.경력사항</h1>
    <resume-career-form></resume-career-form>
  </div>
</template>
<script>
import ResumeCareerForm from '@/components/resume/ResumeCareerForm.vue'

export default {
  name: 'ResumeStepTwo',
  components: { ResumeCareerForm },
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