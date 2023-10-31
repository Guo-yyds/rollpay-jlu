<template>
  <body id="login-page">
    <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="80px" class="login-container">
      <h3 class="login_title">管理员登录</h3>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
  </body>
</template>

<script>
import { adminLogin } from '@/api/adminLogin'

export default {
  name: 'LoginPage',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 登录
    async login () {
      this.$refs.loginForm.validate(async (valid) => {
        if (valid) {
          // 表单验证通过，执行登录逻辑
          console.log('发送登录请求')

          const res = await adminLogin(this.loginForm.username, this.loginForm.password)
          console.log(res)
          if (res.code !== 1) {
            this.$message(res.msg)
            return
          }
          this.$store.commit('setUserInfo', res.data)
          this.$message('登录成功')

          // 进行判断，看地址栏有无回跳地址
          // 1. 如果有   => 说明是其他页面，拦截到登录来的，需要回跳
          // 2. 如果没有 => 正常去首页
          const url = this.$route.query.backUrl || '/'
          this.$router.replace(url)

          console.log('登录成功')
        } else {
          // 表单验证不通过，提示错误信息
          console.log('表单验证失败')
        }
      })
    }
  }
}
</script>

<style scoped>
#login-page {
  background: url("../assets/bg.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body {
  margin: 0px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
