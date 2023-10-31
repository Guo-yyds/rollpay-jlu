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
    <el-input v-model.number="time" placeholder="请输入时间"></el-input>
    <br>
    <br>
    <el-select v-model="projectid" placeholder="请选择项目id">
    <el-option
        v-for="item in options"
        :key="item"
        :label="item"
        :value="item">
      </el-option>
    </el-select>
    <br>
    <br>
    <!-- 按钮 -->
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="saveData">添加时间卡</el-button>
    </div>
  </div>
</template>

<script>
import { timecardAdd } from '@/api/timecard'
export default {
  data () {
    return {
      date: '',
      time: '',
      projectid: '',
      options: [1, 2, 3, 4]
    }
  },
  methods: {
    validFn () {
      if (this.date === '') {
        this.$message({
          message: '日期不能为空',
          type: 'warning'
        })
        return false
      }
      if (this.time === '') {
        this.$message({
          message: '时间不能为空',
          type: 'warning'
        })
        return false
      }
      if (this.time < 0 || this.time > 24) {
        this.$message({
          message: '时间必须在0-24之间',
          type: 'warning'
        })
        return false
      }
      if (this.projectid === '') {
        this.$message({
          message: '项目id不能为空',
          type: 'warning'
        })
        return false
      }
      return true
    },
    async saveData () {
      // console.log(this.date, this.time, this.projectid)
      if (!this.validFn()) return
      const res = await timecardAdd(
        this.date,
        this.time,
        this.projectid,
        this.$store.state.userInfo.id
      )
      console.log(res)
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
