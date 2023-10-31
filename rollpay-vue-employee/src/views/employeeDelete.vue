<template>
  <div>
    <el-input v-model="input" placeholder="请输入待删除的订单id"></el-input>
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
        <el-button type="primary" @click="showConfirmation">删除</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { MessageBox } from 'element-ui'
import { employeeGetById, employeeDeleteOrder } from '@/api/employee'
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
      const res = await employeeGetById(this.input, this.$store.state.userInfo.id)
      console.log(res)
      if (res.code === 1) {
        this.id = res.data.id
        this.date = res.data.date
        this.amount = res.data.amount
        this.pointcontact = res.data.pointcontact
        this.billingaddress = res.data.billingaddress
      } else {
        this.$message(res.msg)
      }
    },
    async saveData () {
      const res = await employeeDeleteOrder(
        this.id,
        this.$store.state.userInfo.id
      )
      console.log(res)
      if (res.code === 1) {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
      }
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
