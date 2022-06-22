<template>
  <div class='container'>
    <el-container>
      <h1 style="font-size:18px" class="header">Question3: 电脑销售系统</h1>
      <el-footer style="margin-left: 30px">
        <div style="display: flex;justify-content: space-between">
          <el-tabs type="border-card" style="width: 55%; height: 400px">
            <el-tab-pane label="单个测试" name="one">
              <h4>单个测试</h4>
              <el-form ref="form" :inline="true" :model="form" label-width="70px" class="input-form">
                <el-form-item label="主机" class="input-text">
                  <el-input v-model="form.host"></el-input>
                </el-form-item>
                <el-form-item label="显示器" class="input-text">
                  <el-input v-model="form.display"></el-input>
                </el-form-item>
                <el-form-item label="外设" class="input-text">
                  <el-input v-model="form.peripheral"></el-input>
                </el-form-item>
                <el-form-item label="销售额" class="input-text">
                  <el-input v-model="form.sales"></el-input>
                </el-form-item>
                <el-form-item label="后置条件" class="input-text">
                  <el-input v-model="form.after"></el-input>
                </el-form-item>
                <el-form-item label="预期佣金" class="input-text">
                  <el-input v-model="form.commission"></el-input>
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
                  action="http://101.35.194.132:81/uploadCommission"
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
      </el-footer>
    </el-container>
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
      form: {
        host: '',
        display: '',
        peripheral: '',
        commission: '',
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
        host:parseInt(this.form.host),
        display:parseInt(this.form.display),
        peripheral:parseInt(this.form.peripheral),
        sales:parseInt(this.form.sales),
        after:parseInt(this.form.after),
        commission: this.form.commission
      }
      console.log(data);
      axios.getOneCommission(data).then(res=>{
        console.log(res.data.data.Commission);
        this.tableData=[];
        this.tableData.push(res.data.data.Commission);
        console.log(this.tableData[0]);
        if(res.data.data.Commission.pass==1) {
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
// 不能为0，用 -1 表示
// 不能为负数，用-2表示
// 超出范围，用-3表示
// 未输入信息，用-4表示
// 超过计算时间用-5表示
    resultFormat(num){
      switch (num){
        case -1.0:return "不能为0";
        case -2.0:return "不能为负数";
        case -3.0:return "超出范围";
        case -4.0:return "未输入信息";
        case -5.0:return "超过计算时间";
        default:return num;
      }
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
                let temp=res.data;
                let datalist=[]
               for(let i =0; i<temp.length;i++){
                 let item={
                   id:temp[i].id,
                   host:temp[i].host,
                   display:temp[i].display,
                   after:temp[i].after,
                   peripheral:temp[i].peripheral,
                   sales:temp[i].sales,
                   commission:this.resultFormat(temp[i].commission),
                   actualCommission:this.resultFormat(temp[i].actualCommission),
                   pass:temp[i].pass,
                 }
                 datalist.push(item);
               }
                this.tableData=datalist;
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
