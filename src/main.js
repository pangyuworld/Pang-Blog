import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import iView from 'iview'; // 导入组件库
import 'iview/dist/styles/iview.css'; // 导入样式
import qs from 'qs'
import Axios from 'axios'
import global_ from './config/global'
import VueDND from 'awe-dnd'
import "mavon-editor/dist/css/index.css";
import mavonEditor from 'mavon-editor'

Vue.prototype.GLOBAL = global_
Vue.prototype.$http = Axios
Vue.prototype.$qs = qs

Vue.use(mavonEditor)
Vue.use(VueDND)
Vue.use(iView);

Vue.config.productionTip = false



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
