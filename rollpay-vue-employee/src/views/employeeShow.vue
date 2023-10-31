<template>
<div>
  <el-table :data="items"  border  style="width: 100%">
  <el-table-column  prop="id"  label="序号"> </el-table-column>
  <el-table-column  prop="date"  label="日期"> </el-table-column>
  <el-table-column  prop="amount"  label="金额"> </el-table-column>
  <el-table-column  prop="pointcontact"  label="联系方式"> </el-table-column>
  <el-table-column  prop="billingaddress"  label="地址"> </el-table-column>
  </el-table>
</div>
</template>

<script>
import { employeeShow } from '@/api/employee'
export default {
  data () {
    return {
      items: []
    }
  },
  mounted () {
    this.getData()
  },
  methods: {
    async getData () {
      const id = this.$store.state.userInfo.id
      console.log(id)
      const res = await employeeShow(this.$store.state.userInfo.id)
      console.log(res)
      if (res.code === 1) {
        this.items = res.data
      }
    }
  }
}
</script>

<style>

</style>
