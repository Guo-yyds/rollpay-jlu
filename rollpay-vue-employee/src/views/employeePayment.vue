<template>
  <div>
    <el-form>
      <el-form-item label="支付方式">
        <el-radio-group v-model="way">
          <el-radio label='0'>Mail</el-radio>
          <el-radio label='1'>Pickup</el-radio>
          <el-radio label='2'>Deposit</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="地址" v-show="way == 1"><el-input v-model="address"></el-input></el-form-item>
      <el-form-item label="银行名称" v-show="way == 2"><el-input v-model="bankname"></el-input></el-form-item>
      <el-form-item label="银行账号" v-show="way == 2"><el-input v-model="banknumber"></el-input></el-form-item>
      </el-form>
      <!-- 按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveData">提交修改</el-button>
      </div>
  </div>
</template>

<script>
import { employeePayment } from '@/api/employee'
export default {
  name: 'employeePayment',
  data () {
    return {
      way: '0',
      address: '',
      bankname: '',
      banknumber: ''
    }
  },
  created () {
    this.way = this.$store.state.userInfo.way.toString()
  },
  methods: {
    async saveData () {
      this.$store.commit('setWay', this.way)
      const res = await employeePayment(
        this.$store.state.userInfo.id,
        this.way, this.address, this.bankname, this.banknumber)
      console.log(res)
      if (res.code !== 1) {
        this.$message(res.msg)
        return
      }
      this.$message('修改成功')
    }
  }
}
</script>

<style>

</style>
