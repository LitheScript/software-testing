<template>
  <div style="margin-left: 50px;margin-right:50px;text-align: left;width: 95%;">
    <div style="display: flex;justify-content: space-between">
      <h1>单元测试结果</h1>
      <el-button type="primary" @click="viewCoverage" style="margin: 10px 0px">查看覆盖率</el-button>
      <el-button type="primary" @click="viewRaw" style="margin: 10px 0px">查看测试结果</el-button>
      </div>

    <div v-if="raw" style="display: flex;justify-content: space-between">
      <iframe src="static/ObjectControllerTest.html" style="width: 48%;height: 600px" frameborder="0">
      </iframe>
      <iframe src="static/UserControllerTest.html" style="width: 50%;height: 600px" frameborder="0">
      </iframe>
    </div>
    <div v-if="coverage" style="display: flex;justify-content: space-between">
      <iframe src="static/objectControllerCover/index.html" style="width: 48%;height: 600px" frameborder="0">
      </iframe>
      <iframe src="static/userControllerCover/index.html" style="width: 50%;height: 600px" frameborder="0">
      </iframe>
    </div>
  </div>
</template>

<script>
export default {
  name:"unitTest",
  data(){
    return {
      src:'../../public/static/ObjectControllerTest.html',
      raw:true,
      coverage:false,
    };
  },
  methods:{
    viewRaw(){
      this.raw=true;
      this.coverage=false;
    },
    viewCoverage(){
      this.coverage=true;
      this.raw=false;
    }
  }
}
</script>
