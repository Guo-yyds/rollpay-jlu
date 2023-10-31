<template>
  <div>
    <el-form ref="form">
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
        <el-radio-group v-model="way">
          <el-radio label="0">Mail</el-radio>
          <el-radio label="1">Pickup</el-radio>
          <el-radio label="2">Deposit</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="地址" v-show="way == 1"><el-input v-model="address"></el-input></el-form-item>
      <el-form-item label="银行名称" v-show="way == 2"><el-input v-model="bankname"></el-input></el-form-item>
      <el-form-item label="银行账号" v-show="way == 2"><el-input v-model="banknumber"></el-input></el-form-item>
    </el-form>
    <!-- 按钮 -->
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="saveData">保存</el-button>
    </div>
  </div>
</template>

<script>
import { adminAdd } from '@/api/admin'
export default {
  data () {
    return {
      // 表单数据
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
    // 重置表单数据
    resetForm () {
      this.$refs.form.resetFields()

      // 重置表单数据
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
      const res = await adminAdd(this.username, this.password, this.name, this.email, this.SSN, this.tax, this.detection, this.telephone, this.kind, this.hoursal, this.sal, this.com, this.hourlimit, this.way, this.address, this.bankname, this.banknumber)
      console.log(res)
      if (res.code !== 1) {
        this.$message(res.msg)
        return
      }
      this.$message('添加成功')
      this.resetForm()
    }
  }
}
</script>

<style>

</style>
