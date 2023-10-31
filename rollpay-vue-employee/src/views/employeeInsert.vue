<template>
  <div>
    <div class="block">
      <span class="demonstration">日期</span>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <el-date-picker
        v-model="date"
        type="date"
        placeholder="选择日期">
      </el-date-picker>
    </div>
    <br>
    <el-input v-model.number="amount" placeholder="请输入订单数量"></el-input>
    <br>
    <br>
    <el-input v-model="pointcontact" placeholder="请输入合同点"></el-input>
    <br>
    <br>
    <el-input v-model="billingaddress" placeholder="请输入账单地址"></el-input>
    <br>
    <br>

    <!-- 按钮 -->
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="saveData">添加订单</el-button>
    </div>
  </div>
</template>

<script>
import { employeeAddOrder } from '@/api/employee'
export default {
  data () {
    return {
      date: '',
      amount: '',
      pointcontact: '',
      billingaddress: ''
    }
  },
  methods: {
    async saveData () {
      const res = await employeeAddOrder(
        this.date,
        this.amount,
        this.pointcontact,
        this.billingaddress,
        this.$store.state.userInfo.id
      )
      // console.log(res)
      if (res.code === 1) {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
      }
    }
  }
}
</script>

<style>

</style>
