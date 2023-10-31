<template>
  <div>
          <el-table :data="items"  border  style="width: 100%">
            <el-table-column  prop="id"  label="序号"> </el-table-column>
            <el-table-column  prop="username"  label="用户名"> </el-table-column>
            <el-table-column  prop="password"  label="密码"> </el-table-column>
            <el-table-column  prop="name"  label="姓名"> </el-table-column>
            <el-table-column  prop="email"  label="邮箱"> </el-table-column>
            <el-table-column  prop="SSN"  label="社保号"> </el-table-column>
            <el-table-column  prop="tax"  label="税号"> </el-table-column>
            <el-table-column  prop="detection"  label="检测号"> </el-table-column>
            <el-table-column  prop="telephone"  label="电话号码"> </el-table-column>
            <el-table-column  prop="kind"  label="员工类型">
              <template slot-scope="scope">
                <span v-if="scope.row.kind == 0">hour</span>
                <span v-else-if="scope.row.kind == 1">sal</span>
                <span v-else>com</span>
              </template>
            </el-table-column>
            <el-table-column  prop="hoursal"  label="时薪"> </el-table-column>
            <el-table-column  prop="sal"  label="月薪"> </el-table-column>
            <el-table-column  prop="com"  label="佣金"> </el-table-column>
            <el-table-column  prop="hourlimit"  label="小时限制"> </el-table-column>
            <el-table-column  prop="way"  label="支付方式"> </el-table-column>
            <el-table-column  prop="address"  label="地址"> </el-table-column>
            <el-table-column  prop="bankname"  label="银行名称"> </el-table-column>
            <el-table-column  prop="banknumber"  label="银行账号"> </el-table-column>
            <el-table-column  prop="isDelete"  label="删除">
              <template slot-scope="scope">
                <span v-if="scope.row.isDelete == 1">已删除</span>
                <span v-else>未删除</span>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            :current-page="currentPage"
            :page-sizes="pageSizes"
            :page-size="pageSize"
            :total="totalItems"
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
            layout="sizes, prev, pager, next, jumper"
          ></el-pagination>
        </div>
</template>

<script>
import { adminList } from '@/api/admin'
export default {
  data () {
    return {
    // 分页器数据
      currentPage: 1,
      pageSize: 5,
      totalItems: 100,
      pageSizes: [5, 10, 20, 30, 40], // 可选的页面大小
      items: [] // 所有数据
    }
  },
  mounted () {
    // 模拟获取数据
    this.fetchData()
  },
  methods: {
    // 以下是分页器相关的方法
    async fetchData () {
    // 模拟异步获取数据
    // 这里假设从后端获取了100条数据
      const res = await adminList(this.currentPage, this.pageSize)
      console.log(res.data.records)
      this.items = res.data.records
      this.totalItems = res.data.total
    },
    handleCurrentChange (newPage) {
    // 处理页码变化
      this.currentPage = newPage
      this.fetchData()
    },
    handleSizeChange (newSize) {
    // 处理页面大小变化
      this.pageSize = newSize
      this.currentPage = 1
      this.fetchData()
    }
  }

}
</script>

<style>

</style>
