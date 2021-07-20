import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router/index'
import store from './store/index'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App),
  store
}).$mount('#app')
