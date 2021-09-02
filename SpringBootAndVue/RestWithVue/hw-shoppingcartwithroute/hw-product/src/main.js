import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ProductDetail from './pages/ProductDetail.vue'
import Cart from './pages/Cart.vue'
import Product from './pages/Product.vue'
import Success from './pages/Success.vue'

Vue.use(VueRouter)

Vue.config.productionTip = false
const routes = [
  { path: '/', component: Product },
  { path: '/products', component: Product },
  { path: '/cart', component: Cart },
  { path: '/product-detail/:id', component: ProductDetail },
  { path: '/success', component: Success },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

// const app = new Vue({
//   router
// }).$mount('#app')

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
