import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:null,
    username:null
  },
  mutations: {
    getToken(state,userInfo){
      state.token=userInfo.token,
      state.username=userInfo.username
    } 
  },
  actions: {

  }
})
