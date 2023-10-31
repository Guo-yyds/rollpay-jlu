<template>
  <div>
    <template>
      <el-radio v-model="kind" label="0">Total Hours Worked</el-radio>
      <el-radio v-model="kind" label="1">Pay Year-to-Date</el-radio>
    </template>
    <br>
    <br>
    <el-input v-model="name" placeholder="请输入姓名"></el-input>

    <br>
    <br>
    <div class="block">
      <span class="demonstration">开始日期&nbsp;</span>
      <el-date-picker
        v-model="startDate"
        type="date"
        placeholder="选择日期">
      </el-date-picker>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <span class="demonstration">结束日期&nbsp;</span>
      <el-date-picker
        v-model="endDate"
        type="date"
        placeholder="选择日期">
      </el-date-picker>
    </div>
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
import { adminReport } from '@/api/admin'
export default {
  data () {
    return {
      kind: '1',
      name: '',
      startDate: '',
      endDate: '',
      answer: ''
    }
  },
  methods: {
    validFn () {
      if (this.name === '') {
        this.$message.error('姓名不能为空')
        return false
      }
      if (this.startDate === '') {
        this.$message.error('开始日期不能为空')
        return false
      }
      if (this.endDate === '') {
        this.$message.error('结束日期不能为空')
        return false
      }
      return true
    },
    async submit () {
      if (!this.validFn()) {
        return
      }
      const res = await adminReport(
        this.kind,
        this.name,
        this.startDate,
        this.endDate
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
