<template>
  <div>
    <img
      src="../assets/img/bg-banner-custome.jpg"
      style="z-index: -100; position: fixed; top: 8%; bottom: 0; height: 92%"
    />
    <div style="position: absolute; top: 28%; left: 53%; z-index: 99">
      <div>添加物品图片</div>
      <el-upload
      class="avatar-uploade"
              name="file"
              ref="avatar"
              action="http://101.35.194.132:9090/picture/upload"
              :on-success="uploadSuccess"
              multiple
              :limit="1"
              :on-exceed="handleExceed"
              :on-progress="uploading"
              :file-list="fileList"
              :before-upload="beforeAvatarUpload"
        accept=".jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP"
        list-type="picture"
      >
        <el-button
          style="min-height: 100px; min-width: 100px"
          icon="el-icon-plus"
        />
      </el-upload>
    </div>
    <el-card
      style="
        width: 60%;
        height: 75%;
        position: absolute;
        top: 20%;
        left: 5%;
        border-radius: 35px;
        padding: 50px;
      "
    >
      <el-form
        :model="itemForm"
        :rules="rules"
        ref="itemForm"
        label-width="150px"
        style="width: 80%"
      >
        <el-form-item label="物品名称" prop="name">
          <el-input v-model="itemForm.name"></el-input>
        </el-form-item>
        <el-form-item label="物品种类" prop="tag">
          <el-radio-group v-model="itemForm.tag">
            <el-radio label="电子产品" border>电子产品</el-radio>
            <el-radio label="服饰" border>服饰</el-radio>
            <el-radio label="生活好物" border>生活好物</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="每日租金" prop="rentDaily">
          <el-input-number
            v-model="itemForm.renDdaily"
            controls-position="right"
            :min="1"
            :step="1"
            :precision='0'
            style="width: 50%"
          >
          </el-input-number>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元
        </el-form-item>

        <el-form-item label="押金" prop="deposit">
          <el-input-number
            v-model="itemForm.deposit"
            controls-position="right"
            :min="0"
            :step="1"
            :precision='0'
            style="width: 50%"
          >
          </el-input-number>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元
        </el-form-item>
        <el-form-item label="物品新旧程度" prop="newLevel">
          <el-input v-model="itemForm.newLevel"></el-input>
        </el-form-item>
        <el-form-item label="物品详细描述" prop="description">
          <el-input type="textarea" v-model="itemForm.description"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" round @click="submitForm('itemForm')"
            >提交</el-button
          >
          <el-button round @click="resetForm('itemForm')">重新输入</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from '../axios';

// const qs = require('qs');

export default {
  computed: {
    user() {
      return this.$store.state.user;
    },
  },
  data() {
    return {
      itemForm: {
        userId: this.$store.state.user.userId,
        name: '',
        rentDaily: Number(),
        deposit: '',
        tag: '',
        description: '',
        newLevel: '',
        url: null,
      },
      fileList: [],
      rules: {
        name: [
          { required: true, message: '请输入物品名称', trigger: 'blur' },
          {
            min: 1,
            max: 20,
            message: '物品名称长度在1至20字符内',
            trigger: 'blur',
          },
        ],
        tag: [
          {
            required: true,
            message: '请选择一个种类',
            trigger: 'change',
          },
        ],
        rentDaily: [
          {
            required: true,
            message: '请输入每日租金',
            trigger: 'change',
          },
        ],
        deposit: [
          {
            required: true,
            message: '请输入押金',
            trigger: 'change',
          },
        ],
        description: [
          {
            required: true,
            message: '请输入物品详细描述',
            trigger: 'blur',
          },
        ],
        newLevel: [
          {
            required: true,
            message: '请输入物品新旧程度',
            trigger: 'blur',
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.itemForm);
          // this.$refs.upload.submit();
          axios
            .uploadObject(this.itemForm)
            .then((res) => {
              this.$message({
                type: 'success',
                message: '上传成功！',
                offset: 100,
                duration: 3000,
              });

              console.log('上传成功', res.data.data);
              // var itemId=res.data.data
              this.$router.push({
                path: '/homepage',
              });
            })
            .catch((err) => {
              console.log(err);
            });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      // this.$refs.avatar.clearFiles();
    },
    // el-upload相关方法

    uploadSuccess(res) { // 图片上传成功后即调用的函数
      console.log(`图片上传成功${JSON.stringify(res)}`);
      if (res.code == 0) {
        // sysUser为form表单对象，当上传成功后，后端会动将图片存储在数据库，数据库会自动为该图片生成一个名字，
        // 提交form表单时需要向后台发送服务器生成的图片名的字段
        // this.sysUser.logoImg = res.data.uploadUrl
        this.itemForm.url = res.data;
        console.log(res.data);
        console.log(this.itemForm.url);
        // 图片上传成功之后可以拿到服务器生成的图片名，赋值给表单对象，提交时再传递给后台
        // console.log('上传到服务器照片名' + this.sysUser.logoImg)
        this.$message({
          message: '上传成功',
          type: 'success',
          duration: 1000,
          offset: 100,
        });
        // 上传成功后为当前页面中的img赋值src，即照片回显（回显的地址是后台传递给前端的）
        // this.imgShow = true
        // console.log(this.imgSrc)
      } else {
        this.$message({
          message: res.msg,
          type: 'error',
          duration: 1000,
          offset: 100,
        });
        // this.$message.error(res.msg)
      }
      this.confirmLoading = false;
    },

    handleExceed(files, fileList) { // 文件超出个数限制的钩子
      console.log(files);
      console.log(fileList);
      this.$message({
        message: `当前限制选择1个文件，本次选择了 ${files.length}个文件，共选择了${files.length + fileList.length}个文件`,
        type: 'warning',
        duration: 1000,
        offset: 100,
      });
    },

    uploading() { // 文件上传时的钩子
      console.log('正在上传...');
      this.confirmLoadig = true;
    },
    beforeAvatarUpload(file) {
      console.log(file);
    },
  },
};
</script>
