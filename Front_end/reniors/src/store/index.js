import { createStore } from 'vuex'

import home from './modules/home'
import board from './modules/board'

export default createStore({
  modules: { home,  board},
})
