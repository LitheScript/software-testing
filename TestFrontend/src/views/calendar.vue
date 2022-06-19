<template>
  <div style="margin-left: 50px;margin-right:50px;text-align: left;width: 100%;">
    <div style="display: flex;justify-content: space-between">
      <div style="width: 28%;">
        <div style="display: flex;justify-content: space-between">
          <h1>Question4: 万年历问题</h1>
          <el-button v-if="isAll" size="small" type="primary" @click="viewAll" style="margin: 10px 0px">所有结果</el-button>
          <el-button v-if="isPart" size="small" type="primary" @click="viewPart" style="margin: 10px 0px">本次结果</el-button>
        </div>
        <el-upload
            drag
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
          <div class="el-upload__text" style="margin-top: 68px">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip">只能上传csv文件，且不超过500kb</div>
        </el-upload>
      </div>
      <div style="width: 28%;height: 300px;" id="pieReport">
      </div>
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
  </div>
</template>

<script>
import axios from '../axios'
import echarts from 'echarts'

export default {
  name: "calender",
  data() {
    return {
      tableData: [],
      fileList: [],
      isAll:true,
      isPart:false,
      allData:[],
      partData:[],
      charts: "",
      opinion: ["及格人数", "未及格人数"],
      opinionData: [
        { value: 12, name: "及格人数", itemStyle: "#1ab394" },
        { value: 18, name: "未及格人数", itemStyle: "#79d2c0" }
      ],
    };
  },
  mounted() {
    axios.getCalendars()
        .then(res=>{
          console.log(res.data);
          this.allData=res.data;
        });
    this.$nextTick(function() {
      this.drawPie("pieReport");
    });
  },
  methods: {
    drawPie(id) {
      this.charts = this.$echarts.init(document.getElementById(id));
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
                var colorList = ["#1ab394", "#79d2c0"];
                return colorList[params.dataIndex];
              }
            },
            data: this.opinionData
          }
        ]
      });
    },
    viewAll(){
      this.isAll=false;
      this.isPart=true;
      this.tableData=this.allData;
      console.log(this.tableData[0])
      console.log(this.allData)
    },
    viewPart(){
      this.isAll=true;
      this.isPart=false;
      this.tableData=this.partData;
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
          this.partData = response.data;
          console.log(response.data)
          file.name = file.name + '---上传成功'
          console.log(file.name)
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
