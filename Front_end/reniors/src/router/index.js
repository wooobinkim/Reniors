import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/user/LoginView.vue'
import SignupView from '../views/user/SignupView.vue'
import FindPasswordView from '../views/user/FindPasswordView.vue'
import FindUsernameView from '../views/user/FindUsernameView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

  //user
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'Signup',
    component: SignupView
  },
  {
    path: '/find/password',
    name: 'FindPassword',
    component: FindPasswordView
  },
  {
    path: '/find/username',
    name: 'FindUsername',
    component: FindUsernameView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
