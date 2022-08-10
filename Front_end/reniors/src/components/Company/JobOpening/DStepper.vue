<template>
  <div class="d-stepper">
    <div class="d-stepper-header d-flex justify-content-around">
      <div
        class="step-number-content text-center"
        :class="{ active: step == i }"
        v-for="(stepItem, i) in steps"
        :key="i"
      >
        <div
          class="step-number align-items-center justify-content-center mx-auto"
          :class="stepNumberClasses(i)"
        >
          <i v-if="step > i" class="fas fa-check"></i>
          <i
            v-else-if="step === i && fatalError"
            class="fas fa-exclamation"
          ></i>
          <span v-else>{{ i + 1 }}</span>
        </div>
        <div class="mt-1 small">{{ stepItem.name }}</div>
      </div>
    </div>

    <b-card
      class="my-4 overflow-hidden"
      bg-variant="light"
      no-body
      :class="{ 'border-danger': error, 'shake-error': shake }"
      v-loading="loading"
    >
      <b-card-body class="d-flex">
        <div v-if="steps[step].icon" class="d-none d-sm-block">
          <i class="fas fa-fw fa-3x mr-4" :class="iconClasses"></i>
        </div>
        <div>
          <h3>{{ step + 1 }}. {{ steps[step].name }}</h3>
          <p class="text-muted">{{ steps[step].desc }}</p>

          <div v-if="!fatalError">
            <transition :name="effect" mode="out-in">
              <keep-alive>
                <component
                  :store="store"
                  :state="state"
                  :step="step"
                  :setState="setState"
                  ref="step"
                  :is="stepComponent"
                  @loading="loadingAction"
                  @error="errorHandler"
                  @fatal-error="blockStepper"
                  @can-continue="nextStepAction"
                  @set-step="setStep"
                />
              </keep-alive>
            </transition>
          </div>
          <div v-else>{{ fatalErrorMsg }}</div>
        </div>
      </b-card-body>
    </b-card>

    <div class="footer d-flex justify-content-end" v-if="!fatalError">
      <b-button
        v-if="step > 0"
        variant="light"
        :disabled="loading"
        class="text-primary"
        @click="backStep"
      >
        <i class="fas fa-angle-double-left"></i> Atrás
      </b-button>

      <b-button
        v-if="step < steps.length - 1"
        variant="success"
        class="ml-2"
        @click="nextStep"
        :disabled="loading"
      >
        Siguiente
        <i class="fas fa-angle-double-right"></i>
      </b-button>

      <b-button
        v-if="steps[step].confirm"
        variant="success"
        class="ml-2"
        @click="$emit('confirm')"
        >{{ steps[step].confirm }}</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "DStepper",
  props: {
    steps: { type: Array, default: () => [] },
    initialState: { type: Object, default: () => ({}) },
  },
  data() {
    return {
      store: {
        state: this.initialState,
        setState: this.setState,
        resetState: this.resetState,
      },
      step: 0,
      loading: false,
      error: false,
      fatalError: false,
      fatalErrorMsg: "",
      effect: "in-out-translate-fade",
      shake: false,
    };
  },
  computed: {
    activeStep() {
      return this.steps[this.step];
    },
    stepComponent() {
      return this.steps[this.step].component;
    },
    iconClasses() {
      if (!this.activeStep.icon) return "";
      else if (/\s/.test(this.activeStep.icon)) return this.activeStep.icon;
      return `fas ${this.activeStep.icon}`;
    },
  },
  methods: {
    setStep(step) {
      if (step >= 1 && step <= this.steps.length) this.step = step - 1;
    },
    resetState() {
      this.store.state = {
        ...this.initialState,
      };
    },
    setState(key, value) {
      this.store.state = {
        ...this.store.state,
        [key]: value,
      };
    },
    errorHandler(payload) {
      this.error = payload;
      this.shake = payload;
      setTimeout(() => {
        this.shake = !payload;
      }, 750);
    },
    blockStepper(msg) {
      this.resetParams();
      this.fatalErrorMsg = msg;
      this.fatalError = true;
    },
    resetParams() {
      this.error = false;
      this.loading = false;
      this.fatalErrorMsg = "";
      this.fatalError = false;
    },
    stepNumberClasses(i) {
      return {
        "bg-primary text-white": this.step === i && !this.fatalError,
        "bg-success text-white": this.step > i && !this.fatalerror,
        "bg-danger text-white": this.fatalError && this.step === i,
        "text-primary": this.step < i,
      };
    },
    nextStep() {
      if (!this.$refs.step.nextStep) return this.nextStepAction();

      if (this.$refs.step.nextStep()) {
        if (!this.loading) {
          this.nextStepAction();
        }
      }
    },
    nextStepAction() {
      this.effect = "in-out-translate-fade";
      this.resetParams();
      if (this.step < this.steps.length - 1) this.step++;
    },
    backStep() {
      this.effect = "out-in-translate-fade";
      this.resetParams();
      if (this.step > 0) this.step--;
    },
    loadingAction(status) {
      this.loading = status;
      //if (!status) this.nextStepAction();
    },
  },
};
</script>

<style>
.d-stepper .d-stepper-header {
  max-width: 600px;
  margin: 0 auto;
  position: relative;
}

.d-stepper .d-stepper-header::before {
  position: absolute;
  width: 100%;
  height: 1px;
  background: #ddd;
  top: 20px;
  left: 0;
  content: " ";
}

.d-stepper .step-number {
  background: #e9e9e9;
  border-radius: 50%;
  text-align: center;
  height: 40px;
  width: 40px;
  display: flex;
}
.d-stepper .step-number-content {
  transition: transform 0.2s;
  z-index: 2;
  width: 68px;
}

.d-stepper .step-number-content div {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.d-stepper .step-number-content.active {
  transform: scale(1.25);
}

.in-out-translate-fade-enter-active,
.in-out-translate-fade-leave-active {
  transition: all 0.15s;
}
.in-out-translate-fade-enter,
.in-out-translate-fade-leave-active {
  opacity: 0;
}
.in-out-translate-fade-enter {
  transform: translateX(100px);
}
.in-out-translate-fade-leave-active {
  transform: translateX(-100px);
}

.out-in-translate-fade-enter-active,
.out-in-translate-fade-leave-active {
  transition: all 0.15s;
}
.out-in-translate-fade-enter,
.out-in-translate-fade-leave-active {
  opacity: 0;
}
.out-in-translate-fade-enter {
  transform: translateX(-100px);
}
.out-in-translate-fade-leave-active {
  transform: translateX(100px);
}
</style>
