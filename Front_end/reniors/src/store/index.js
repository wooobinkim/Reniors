import { createStore } from 'vuex'
import { user } from "./modules/user"

import home from './modules/home'
import jobopening from './modules/jobopening'

export default createStore({
  modules: { home, user, jobopening },
})
