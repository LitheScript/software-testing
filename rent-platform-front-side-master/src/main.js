import Vue from 'vue';
import ElementUI from 'element-ui';
import App from './App.vue';
import router from './router';
import store from './store';
// +引入组件库及相关样式
import 'element-ui/lib/theme-chalk/index.css';
import 'animate.css';
import './assets/sakura';

Vue.use(ElementUI);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
