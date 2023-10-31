<template>
  <div>
    <el-input v-model="input" placeholder="请输入待删除的员工id"></el-input>
    <br><br>
    <el-button type="primary" @click="search">查询</el-button>
    <div v-if="id">
      <el-form ref="form" >
      <el-form-item label="用户名"><el-input v-model="username"></el-input></el-form-item>
      <el-form-item label="密码"><el-input v-model="password"></el-input></el-form-item>
      <el-form-item label="姓名"><el-input v-model="name"></el-input></el-form-item>
      <el-form-item label="邮箱"><el-input v-model="email"></el-input></el-form-item>
      <el-form-item label="社保号"><el-input v-model="SSN"></el-input></el-form-item>
      <el-form-item label="税号"><el-input v-model="tax"></el-input></el-form-item>
      <el-form-item label="检测号"><el-input v-model="detection"></el-input></el-form-item>
      <el-form-item label="电话号码"><el-input v-model="telephone"></el-input></el-form-item>
      <el-form-item label="员工类型">
        <el-radio-group v-model="kind">
          <el-radio label="0">Hourly</el-radio>
          <el-radio label="1">Salaried</el-radio>
          <el-radio label="2">Commission</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="时薪" v-show="kind == 0"><el-input v-model="hoursal"></el-input></el-form-item>
      <el-form-item label="月薪" v-show="kind != 0"><el-input v-model="sal"></el-input></el-form-item>
      <el-form-item label="佣金" v-show="kind == 2"><el-input v-model="com"></el-input></el-form-item>
      <el-form-item label="小时限制"><el-input v-model="hourlimit"></el-input></el-form-item>
      <el-form-item label="支付方式">
        <el-input v-model="way" disabled></el-input>
        <el-radio-group v-model="way">
          <el-radio label=0>Mail</el-radio>
          <el-radio label=1>Pickup</el-radio>
          <el-radio label=2>Deposit</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="地址" v-show="way == 1"><el-input v-model="address"></el-input></el-form-item>
      <el-form-item label="银行名称" v-show="way == 2"><el-input v-model="bankname"></el-input></el-form-item>
      <el-form-item label="银行账号" v-show="way == 2"><el-input v-model="banknumber"></el-input></el-form-item>
      </el-form>
      <!-- 按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="showConfirmation">删除</el-button>
      </div>
    </div>
  </div>

</template>

<script>
import { MessageBox } from 'element-ui'
import { adminId, adminDelete } from '@/api/admin'
export default {
  data () {
    return {
      showDialog: false,
      input: '',
      // 表单数据
      id: '',
      username: '',
      password: '',
      name: '',
      email: '',
      SSN: '',
      tax: '',
      detection: '',
      telephone: '',
      kind: '',
      hoursal: '',
      sal: '',
      com: '',
      hourlimit: '',
      way: '',
      address: '',
      bankname: '',
      banknumber: ''
    }
  },
  methods: {
    async search () {
      const res = await adminId(this.input)
      console.log(res.data)
      if (res.code !== 1) {
        this.$message({
          message: '查询失败',
          type: 'error'
        })
      }
      this.id = res.data.id
      this.username = res.data.username
      this.password = res.data.password
      this.name = res.data.name
      this.email = res.data.email
      this.SSN = res.data.SSN
      this.tax = res.data.tax
      this.detection = res.data.detection
      this.telephone = res.data.telephone
      this.kind = res.data.kind
      this.hoursal = res.data.hoursal
      this.sal = res.data.sal
      this.com = res.data.com
      this.hourlimit = res.data.hourlimit
      this.way = res.data.way
      this.address = res.data.address
      this.bankname = res.data.bankname
      this.banknumber = res.data.banknumber
    },
    // 重置表单数据
    resetForm () {
      this.$refs.form.resetFields()

      // 重置表单数据
      this.id = ''
      this.username = ''
      this.password = ''
      this.name = ''
      this.email = ''
      this.SSN = ''
      this.tax = ''
      this.detection = ''
      this.telephone = ''
      this.kind = ''
      this.hoursal = ''
      this.sal = ''
      this.com = ''
      this.hourlimit = ''
      this.way = ''
      this.address = ''
      this.bankname = ''
      this.banknumber = ''
    },
    // 保存数据
    async saveData () {
      // 在这里可以进行数据保存操作
      const res = await adminDelete(this.id)
      console.log(res)
      if (res.code !== 1) {
        this.$message(res.msg)
        return
      }
      this.$message('删除成功')
      this.resetForm()
    },
    showConfirmation () {
      MessageBox.confirm('确认删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
        // 用户点击了确定按钮，执行删除操作
          this.saveData()
        })
        .catch(() => {
        // 用户点击了取消按钮，执行取消操作
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  }
}
</script>

<style>

</style>
