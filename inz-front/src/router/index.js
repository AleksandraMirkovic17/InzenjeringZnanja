import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import Bayes from '../components/BayesComponent.vue'
import SuitabilityPage from '../components/SuitabilityPage.vue'

const routes = [
      {
        path: '/home',
        name: 'HomePage',
        component: HomePage
      },
      {
        path: '/bayes',
        name: 'Bayes',
        component: Bayes
      },
      {
        path: '/suitability',
        name: 'Suitability',
        component: SuitabilityPage
      }]

    const router = createRouter({
      history: createWebHistory(process.env.BASE_URL),
      routes
    })
          
    export default router