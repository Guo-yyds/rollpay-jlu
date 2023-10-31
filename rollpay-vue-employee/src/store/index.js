import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  namespaced: true,
  state () {
    return {
      // 个人权证相关
      userInfo: { id: '', token: '', username: '', way: '', kind: '' }
    }
  },
  mutations: {
    // 所有mutations的第一个参数，都是state
    setUserInfo (state, obj) {
      state.userInfo = obj
    },
    setWay (state, way) {
      state.userInfo.way = way
    }
  },
  actions: {
    logout ({ commit }) {
      commit('setUserInfo', { id: '', token: '', username: '', way: '', kind: '' })
    }
  },
  getters: {
    token (state) {
      return state.userInfo.token
    }
  }
})
