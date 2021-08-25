import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Home from './components/Home'
import People from './components/People'
import Jobs from './components/Jobs'
import PersonDetail from './components/PersonDetail'

Vue.use(VueRouter)

Vue.config.productionTip = false

const routes = [
  { path: '/', component: Home },
  { path: '/people', component: People },
  { path: '/jobs', component: Jobs },
  { path: '/people/:id', component: PersonDetail },
  { path: '/edit/:id', component: Home },
]

const router = new VueRouter({
  mode: 'history',
  routes 
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
