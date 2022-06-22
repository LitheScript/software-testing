<template>
  <div class='container'>
    <el-container>
      <h1 style="font-size:18px" class="header">Question4: 万年历问题</h1>
      <el-footer style="margin-left: 30px">
        <div style="display: flex;justify-content: space-between">
          <el-tabs type="border-card" style="width: 50%; height: 400px">
            <el-tab-pane label="单个测试" name="one">
              <h4>单个测试</h4>
              <el-form ref="form" :inline="true" :model="form" label-width="110px" class="input-form">
                <el-form-item label="年" class="input-text">
                  <el-input v-model="form.year"></el-input>
                </el-form-item>
                <el-form-item label="月" class="input-text">
                  <el-input v-model="form.month"></el-input>
                </el-form-item>
                <el-form-item label="日" class="input-text">
                  <el-input v-model="form.day"></el-input>
                </el-form-item>
                <el-form-item label="预期结果" class="input-text">
                  <el-input v-model="form.expectResult"></el-input>
                </el-form-item>
                <el-form-item class="button">
                  <el-button type="primary" @click="onClick">测试</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="批量测试" name="second">
              <h4>批量测试</h4>
              <el-upload
                  class="upload-demo"
                  action="http://101.35.194.132:81/uploadCalendar"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  multiple
                  :limit="100"
                  :on-exceed="handleExceed"
                  :on-success="Success"
                  :file-list="fileList">
                <el-button size="small" type="primary">上传测试用例</el-button>
                <div class="el-upload__tip">只能csv文件，且不超过500kb</div>
              </el-upload>
            </el-tab-pane>
          </el-tabs>
          <div style="width: 50%;height: 300px;" id="pieReport"></div>
        </div>
        <div>
          <el-table
              :data="tableData"
              stripe
              height="500"
              style="width: 95%">
            <el-table-column
                prop=id
                label="序号">
            </el-table-column>
            <el-table-column
                prop=year
                label="年">
            </el-table-column>
            <el-table-column
                prop=month
                label="月">
            </el-table-column>
            <el-table-column
                prop=day
                label="日">
            </el-table-column>
            <el-table-column
                prop=expectResult
                label="预期输出">
            </el-table-column>
            <el-table-column
                prop=actualResult
                label="实际输出">
            </el-table-column>
            <el-table-column
                prop=pass
                label="是否通过">
            </el-table-column>

          </el-table>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>


<script>
import axios from '../axios'

export default {
  name: "calender",
  data() {
    return {
      tableData: [],
      fileList: [],
      form: {
        year: '',
        month: '',
        day: '',
        expectResult: '',
      },
      passRate:0,
      charts: "",
      opinion: ["通过用例", "未通过用例"],
      opinionData: [
        { value: 20, name: "通过用例", itemStyle: "#1ab394" },
        { value: 18, name: "未通过用例", itemStyle: "#79d2c0" }
      ],
    };
  },
  methods: {
    drawPie(id) {
      this.charts = echarts.init(document.getElementById(id));
      this.charts.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a}<br/>{b}:{c} ({d}%)"
        },
        legend: {
          bottom: 10,
          left: "center",
          data: this.opinion
        },
        series: [
          {
            name: "状态",
            type: "pie",
            radius: "65%",
            center: ["50%", "50%"],
            avoidLabelOverlap: false,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              },
              color: function(params) {
                //自定义颜色
                var colorList = ["#12a182", "#c27c88"];
                return colorList[params.dataIndex];
              }
            },
            data: this.opinionData
          }
        ]
      });
    },
    onClick() {
      let data={
        year:parseInt(this.form.year),
        month:parseInt(this.form.month),
        day:parseInt(this.form.day),
        expectResult: this.form.expectResult
      }
      console.log(data);
      axios.getOneCalendar(data).then(res=>{
        console.log(res.data.data.Calendar);
        this.tableData=[];
        this.tableData.push(res.data.data.Calendar);
        console.log(this.tableData[0]);
        if(res.data.data.Calendar.pass==1) {
          this.$message({
            message: '测试通过',
            type: 'success'
          });
        }
        else {
          this.$message.error('测试未通过');
        }
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 100 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    // eslint-disable-next-line no-unused-vars
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    // eslint-disable-next-line no-unused-vars
    Success(response, file, fileList) {
      if (file != null) {
        if (response.code != 0) {
          console.log(response.code + response + file)
          // 响应码不为0时，标识后台上传文件出错啦
          file.status = 'error'
          let fileName = file.name
          file.name = file.name + '---上传失败'
          this.$message({
            type: 'error', message: fileName + '上传失败'
          })
        } else {
          file.name = file.name + '---上传成功'
          axios.getCalendars()
              .then(res=>{
                console.log(res.data);
                this.tableData=res.data;
                let _this = this;
                axios.getCalendarPass()
                    .then(res=>{
                      console.log(res.data);
                      _this.passRate=res.data;
                      let len=_this.tableData.length;
                      console.log(_this.opinionData[0]);
                      _this.opinionData[0].value=len*_this.passRate;
                      _this.opinionData[1].value=len*(1-_this.passRate);
                      this.$nextTick(function() {
                        this.drawPie("pieReport");
                      });
                    });
              });
        }
      }
    }
  }
}
</script>

<style scoped>
.header{
  margin-left: 50px;
  margin-bottom: 30px;
  text-align: left;
}
.upload-demo {
  margin-top: 50px;
}
.input-form{
  padding-right: 10px;
}
</style>
