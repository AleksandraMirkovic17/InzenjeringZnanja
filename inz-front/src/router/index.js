import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import Bayes from '../components/BayesComponent.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomePage
    },
      {
        path: '/home',
        name: 'HomePage',
        component: HomePage
      },
      {
        path: '/bayes',
        name: 'Bayes',
        component: Bayes
      }]

      const router = createRouter({
        history: createWebHistory(process.env.BASE_URL),
        routes
      })
            
      export default router