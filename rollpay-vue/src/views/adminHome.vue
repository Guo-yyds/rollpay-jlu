<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-s-custom"></i>管理员工</template>
          <el-menu :default-active="activeMenu" @select="handleMenuSelect">
            <el-menu-item index="adminShow">显示员工</el-menu-item>
            <el-menu-item index="adminInsert">添加员工</el-menu-item>
            <el-menu-item index="adminUpdate">修改员工</el-menu-item>
            <el-menu-item index="adminDelete">删除员工</el-menu-item>
          </el-menu>
        </el-submenu>

        <el-menu :default-active="activeMenu" @select="handleMenuSelect">
          <el-menu-item index="adminReport"><i class="el-icon-document" ></i>生成管理员报告</el-menu-item>
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
      this.$router.push('/adminLogin')
    }
  }
}
</script>
