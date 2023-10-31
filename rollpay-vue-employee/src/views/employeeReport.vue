<template>
  <div>

    <div class="block">
      <span class="demonstration">开始日期&nbsp;</span>
      <el-date-picker
        v-model="startTime"
        type="date"
        placeholder="选择日期">
      </el-date-picker>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <span class="demonstration">结束日期&nbsp;</span>
      <el-date-picker
        v-model="endTime"
        type="date"
        placeholder="选择日期">
      </el-date-picker>
    </div>
    <br>
    <br>
    <template>
      <el-radio v-model="kind" label="0">Total Hours Worked</el-radio>
      <el-radio v-model="kind" label="1">Total Hours Worked for a Project</el-radio>
      <el-radio v-model="kind" label="2">Vacation/Sick Leave</el-radio>
      <el-radio v-model="kind" label="3">Total Pay Year-to-Date</el-radio>
    </template>

    <br>
    <br>

    <!-- <el-input v-show="kind == 1" v-model="projectid" placeholder="请输入项目id"></el-input> -->
    <el-select v-show="kind == 1" v-model="projectid" placeholder="请选择项目id">
    <el-option
        v-for="item in options"
        :key="item"
        :label="item"
        :value="item">
      </el-option>
    </el-select>
    <br>
    <br>

    <el-button type="primary" @click="submit">查询</el-button>
    结果是：{{ answer.answer }}
    <br>
    <br>
    <el-button type="primary" @click="downloadFile">下载文件</el-button>
  </div>
</template>

<script>
import { employeeReport, employeeList } from '@/api/employee'
export default {
  data () {
    return {
      // projectid列表
      options: [],

      kind: '0',
      name: this.$store.state.userInfo.username,
      startTime: '',
      endTime: '',
      projectid: '',
      answer: ''
    }
  },
  watch: {
    kind (val) {
      if (val === '1') {
        this.getProjectidList()
      }
    }
  },
  methods: {
    async getProjectidList () {
      if (this.startTime === '' || this.endTime === '') {
        return
      }
      const res = await employeeList(
        this.name,
        this.startTime,
        this.endTime
      )
      console.log(res)
      if (res.code !== 1) {
        this.$message.error(res.msg)
      } else {
        this.options = res.data
      }
    },
    validFn () {
      if (this.kind === '1' && this.projectid === '') {
        this.$message.error('项目id不能为空')
        return false
      }
      if (this.startTime === '') {
        this.$message.error('开始日期不能为空')
        return false
      }
      if (this.endTime === '') {
        this.$message.error('结束日期不能为空')
        return false
      }
      return true
    },
    async submit () {
      if (!this.validFn()) {
        return
      }
      const res = await employeeReport(
        this.kind,
        this.name,
        this.startTime,
        this.endTime,
        this.projectid
      )
      console.log(res)
      if (res.code !== 1) {
        this.$message.error(res.msg)
      } else {
        this.answer = res.data
      }
    },
    async downloadFile () {
      try {
        const opts = {
          types: [
            {
              description: '文件',
              accept: {
                'text/plain': ['.txt'],
                'application/pdf': ['.pdf'],
                'image/jpeg': ['.jpg', '.jpeg'],
                'image/png': ['.png']
              }
            }
          ],
          excludeAcceptAllOption: true
        }

        const handle = await window.showSaveFilePicker(opts) // 打开保存文件对话框
        const writable = await handle.createWritable() // 创建可写入的文件对象

        // 在这里写入文件内容
        await writable.write(JSON.stringify(this.answer))
        await writable.close()

        console.log('文件保存成功')
        this.$message.success('文件保存成功')
      } catch (error) {
        console.error('文件保存失败:', error)
      }
    }
  }
}
</script>

<style>

</style>
