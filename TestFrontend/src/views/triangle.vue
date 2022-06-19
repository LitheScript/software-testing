<template>

 <div class='container'>
   <el-container>
    <h1 style="font-size:18px" class="header">Question1:判断三角形类型</h1>
    <el-footer style="margin-left: 30px">
      <div style="display: flex;justify-content: space-between">
       <el-tabs type="border-card" style="width: 50%; height: 350px">
        <el-tab-pane label="单个测试" name="first">
          <h4>单个测试</h4>
          <el-form ref="form" :model="form" label-width="80px" class="input-form">
            <el-form-item label="edge1" class="input-text">
              <el-input v-model="form.edge1"></el-input>
            </el-form-item>
            <el-form-item label="edge2" class="input-text">
              <el-input v-model="form.edge2"></el-input>
            </el-form-item>
            <el-form-item label="edge3" class="input-text">
              <el-input v-model="form.edge3"></el-input>
            </el-form-item>
            <el-form-item class="button">
              <el-button type="primary" @click="onClick">提交</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="批量测试" name="second">
          <h4>批量测试</h4>
          <el-upload
            class="upload-demo"
            action="http://localhost:81/uploadTriangle"
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
          style="width: 100%">
          <el-table-column
              prop=id
              label="序号"
              width="180">
          </el-table-column>
          <el-table-column
              prop=a
              label="输入第一条边"
              width="180">
          </el-table-column>
          <el-table-column
              prop=b
              label="输入第二条边"
              width="180">
          </el-table-column>
          <el-table-column
              prop=c
              label="输入第三条边"
              width="180">
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
import axios from "../axios";

export default {
  name: "triangle",
  data() {
    return {
      uploadActionUrl:'',
      tableData: [],
      fileList: [],
      form: {
        edge1: '',
        edge2: '',
        edge3: '',
      },
      passRate:0,
      charts: "",
      opinion: ["通过用例", "未通过用例"],
      opinionData: [
        { value: 20, name: "通过用例", itemStyle: "#1ab394" },
        { value: 18, name: "未通过用例", itemStyle: "#79d2c0" }
      ],
    }
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
      var a = parseFloat(this.form.edge1)
      var b = parseFloat(this.form.edge2)
      var c = parseFloat(this.form.edge3)
      if (a < 0)
          this.$message.warning ("a不能为0")
      if (b < 0)
          this.$message.warning ("b不能为0")
      if (c < 0)
          this.$message.warning ("c不能为0")
      if (a >= 800)
          this.$message.warning ("a不在取值范围内")
      if (b >= 800)
          this.$message.warning ("b不在取值范围内")
      if (c >= 800)
          this.$message.warning ("c不在取值范围内")
      if (a + c > b && a + b > c && b + c > a)
      {
          if (a == b && b == c)
              this.$message.warning ("等边三角形")
          else if( a == b || b == c || a == c)
              this.$message.warning ("等腰三角形")
          else
              this.$message.warning ("普通三角形")
      }
      else
          this.$message.warning ("不是三角形")
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
          axios.getTriangles()
              .then(res=>{
                console.log(res.data);
                this.tableData=res.data;
                let _this = this;
                axios.getTrianglePass()
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
  padding-right: 20px;
}

</style>
