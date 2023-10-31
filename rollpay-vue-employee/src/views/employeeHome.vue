<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">

        <el-menu :default-active="activeMenu" @select="handleMenuSelect">
          <el-menu-item index="employeePayment"><i class="el-icon-document" ></i>修改支付方式</el-menu-item>
        </el-menu>
        <el-submenu index="1" v-show="this.$store.state.userInfo.kind == 2">
          <template slot="title"><i class="el-icon-s-custom"></i>管理订单</template>
          <el-menu :default-active="activeMenu" @select="handleMenuSelect">
            <el-menu-item index="employeeShow">显示订单</el-menu-item>
            <el-menu-item index="employeeInsert">添加订单</el-menu-item>
            <el-menu-item index="employeeUpdate">修改订单</el-menu-item>
            <el-menu-item index="employeeDelete">删除订单</el-menu-item>
          </el-menu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-s-custom"></i>时间卡</template>
          <el-menu :default-active="activeMenu" @select="handleMenuSelect">
            <el-menu-item index="timecardShow">显示时间卡</el-menu-item>
            <el-menu-item index="timecardInsert">添加时间卡</el-menu-item>
          </el-menu>
        </el-submenu>
        <el-menu :default-active="activeMenu" @select="handleMenuSelect">
          <el-menu-item index="employeeReport"><i class="el-icon-document" ></i>生成报告</el-menu-item>
        </el-menu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 16px">
        用户名:
        <span>{{this.$store.state.userInfo.username}}</span>
        <!-- 空格 -->
        &nbsp;&nbsp;
        <el-button @click="logout">退出登录</el-button>
      </el-header>

      <el-main height="100%">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<script>

export default {
  data () {
    return {
      activeMenu: 'show'
    }
  },
  methods: {
    handleMenuSelect (index) {
      if (this.$route.path !== '/' + index) {
        this.activeMenu = index
        this.$router.push({ path: '/' + index })
      }
    },
    logout () {
      this.$store.dispatch('logout')
      this.$router.push('/employeeLogin')
    }
  }
}
</script>
