<template>
  <div>
    <el-table :data="items"  border  style="width: 100%">
    <el-table-column  prop="id"  label="序号"> </el-table-column>
    <el-table-column  prop="date"  label="日期" :formatter="formatDate">
    </el-table-column>
    <el-table-column  prop="time"  label="时间"> </el-table-column>
    <el-table-column  prop="projectid"  label="项目id"> </el-table-column>
    <el-table-column  prop="issubmit"  label="是否提交"> </el-table-column>
    <el-table-column label="状态">
      <template slot-scope="scope">
        <el-button @click="openDialog" :disabled="scope.row.issubmit == 1">修改时间</el-button>
        <el-dialog :visible.sync="dialogVisible" title="输入时间">
          <el-input v-model.number="time" placeholder="请输入时间"></el-input>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleModify(scope.row.id)">修改</el-button>
          </span>
        </el-dialog>
        &nbsp;&nbsp;
        <el-button type="primary" @click="submit(scope.row.id)" :disabled="scope.row.issubmit == 1">提交</el-button>
      </template>
    </el-table-column>

  </el-table>
  </div>
  </template>

<script>
import { timecardList, timecardSubmit, timecardUpdate } from '@/api/timecard'
export default {
  data () {
    return {
      items: [],
      dialogVisible: false, // 控制弹窗的显示与隐藏
      time: '' // 用于存储用户输入的时间
    }
  },
  created () {
    this.getData()
  },
  methods: {
    formatDate (row, column) {
      const date = new Date(row.date)
      const year = date.getFullYear()
      const month = String(date.getMonth() + 1).padStart(2, '0')
      const day = String(date.getDate()).padStart(2, '0')
      return `${year}-${month}-${day}`
    },
    async getData () {
      const id = this.$store.state.userInfo.id
      console.log(id)
      const res = await timecardList(this.$store.state.userInfo.id)
      console.log(res)
      if (res.code === 1) {
        this.items = res.data
      }
    },
    async submit (id) {
      console.log(id)
      const res = await timecardSubmit(id)
      console.log(res)
      if (res.code === 1) {
        this.$message({
          message: '提交成功',
          type: 'success'
        })
        this.getData()
      }
    },
    openDialog () {
      this.dialogVisible = true // 打开弹窗
    },
    async handleModify (id) {
      if (this.time === '') {
        this.$message({
          message: '请输入时间',
          type: 'warning'
        })
        return
      }
      if (this.time < 0 || this.time > 24) {
        this.$message({
          message: '请输入正确的时间',
          type: 'warning'
        })
        return
      }
      const res = await timecardUpdate(id, this.time)
      console.log(res)
      this.dialogVisible = false // 关闭弹窗
      this.getData()
    }
  }
}
</script>

  <style>

  </style>
