<template>
  <div style="margin-left: 50px;margin-right:50px;text-align: left;width: 90%;">
    <div style="display: flex;justify-content: space-between">
      <div style="width: 58%;">
        <h1>Question3: 电脑销售系统</h1>
        <el-upload
            drag
            class="upload-demo"
            action="http://localhost:81/uploadCommission"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="100"
            :on-exceed="handleExceed"
            :on-success="Success"
            :file-list="fileList">
          <div class="el-upload__text" style="margin-top: 68px">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip">只能上传csv文件，且不超过500kb</div>
        </el-upload>
      </div>
      <div style="width: 60%;height: 300px;" id="pieReport"></div>
    </div>
    <div>
      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column
            prop=id
            label="序号"
            width="180">
        </el-table-column>
        <el-table-column
            prop=host
            label="主机"
            width="180">
        </el-table-column>
        <el-table-column
            prop=peripheral
            label="外设">
        </el-table-column>
        <el-table-column
            prop=display
            label="显示器">
        </el-table-column>
        <el-table-column
            prop=after
            label="后置输出">
        </el-table-column>
        <el-table-column
            prop=commission
            label="预期输出">
        </el-table-column>
        <el-table-column
            prop=actualCommission
            label="实际输出">
        </el-table-column>
        <el-table-column
            prop=pass
            label="是否通过">
        </el-table-column>

      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "../axios";

export default {
  name: "computerSell",
  data() {
    return {
      tableData: [],
      fileList: [],
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
          axios.getCommissions()
              .then(res=>{
                console.log(res.data);
                this.tableData=res.data;
                let _this = this;
                axios.getCommissionPass()
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

</style>
