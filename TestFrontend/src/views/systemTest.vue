<template>
  <div style="margin-left: 50px;margin-right:50px;text-align: left;width: 90%;">
      <h1>系统测试结果</h1>
    <div>
      <iframe ref="tempHtml" style="width: 100%;height: 500px">
      </iframe>
    </div>

  </div>
</template>

<script>
export default {
  name:"unitTest",
  data(){
    return {
      ifeData:require('../../public/static/ObjectControllerTest.html'),
    };
  },
  created() {
    this.$refs.tempHtml.contentDocument.documentElement.innerHTML = this.ifeData;
  }
}
</script>
