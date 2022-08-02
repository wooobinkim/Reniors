import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/user/LoginView.vue'
import SignupView from '../views/user/SignupView.vue'
import SignupCompleteView from '../views/user/SignupCompleteView.vue'
import FindPasswordView from '../views/user/FindPasswordView.vue'
import FindUsernameView from '../views/user/FindUsernameView.vue'
import MypageView from '../views/user/MypageView.vue'
import ResumeEditView from '../views/user/ResumeEditView.vue'

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
    path: '/signup/complete',
    name: 'SignupComplete',
    component: SignupCompleteView
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
  {
    path: '/mypage/:user_id',
    name: 'MyPage',
    component: MypageView
  },


  // resume
  {
    path: '/resume/edit',
    name: 'ResumeEdit',
    component: ResumeEditView
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
