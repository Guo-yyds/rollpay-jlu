<template>
  <div>
    <el-input v-model="input" placeholder="请输入待更新的订单id"></el-input>
    <br><br>
    <el-button type="primary" @click="search">查询</el-button>
    <br><br>
    <div v-if="id">
      <el-form ref="form">
        <div class="block">
          <span class="demonstration">date</span>
          &nbsp;&nbsp;&nbsp;
          <el-date-picker
            v-model="date"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </div>
        <el-form-item label="amount"><el-input v-model="amount"></el-input></el-form-item>
        <el-form-item label="pointcontact"><el-input v-model="pointcontact"></el-input></el-form-item>
        <el-form-item label="billingaddress"><el-input v-model="billingaddress"></el-input></el-form-item>
    </el-form>
      <!-- 按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveData">提交修改</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { employeeGetById, employeeUpdateOrder } from '@/api/employee'
export default {
  data () {
    return {
      input: '',
      id: '',
      date: '',
      amount: '',
      pointcontact: '',
      billingaddress: ''
    }
  },
  methods: {
    async search () {
      console.log(this.input)
      console.log(this.$store.state.userInfo.id)
      const res = await employeeGetById(this.input, this.$store.state.userInfo.id)
      console.log(res)
      if (res.code === 1) {
        this.id = res.data.id
        this.date = res.data.date
        this.amount = res.data.amount
        this.pointcontact = res.data.pointcontact
        this.billingaddress = res.data.billingaddress
      } else {
        this.$message({
          message: '查询失败,请检查id是否正确',
          type: 'error'
        })
      }
    },
    async saveData () {
      const res = await employeeUpdateOrder(
        this.id,
        this.date,
        this.amount,
        this.pointcontact,
        this.billingaddress,
        this.$store.state.userInfo.id
      )
      console.log(res)
      if (res.code === 1) {
        this.$message({
          message: '修改成功',
          type: 'success'
        })
      }
    }

  }
}
</script>

<style>

</style>
