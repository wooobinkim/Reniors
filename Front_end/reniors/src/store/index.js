import { createStore } from 'vuex'
import { user } from "./modules/user"

import home from './modules/home'

export default createStore({
  modules: { home, user },
})
