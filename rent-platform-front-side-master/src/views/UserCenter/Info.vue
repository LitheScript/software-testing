<template>
  <div class="user-info">
    <el-tabs type="border-card" tab-position="left" style="border-radius: 25px;">
      <el-tab-pane label="用户资料">
        <el-form
          ref="PersonalData"
          :model="PersonalData"
          :rules="dataRules"
          label-width="20%"
          style="margin-top: 4%"
        >
          <el-form-item label="头像：">
            <el-upload
              class="avatar-uploade"
              name="file"
              action="http://101.35.194.132:9090/picture/upload"
              :on-success="uploadSuccess"
              multiple
              :limit="1"
              :disabled="editflag"
              :on-exceed="handleExceed"
              :on-progress="uploading"
              :file-list="fileList"
              :before-upload="beforeAvatarUpload"
              :show-file-list="false"
              accept=".jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP"
              list-type="picture"
            >
              <el-avatar
                :size="100"
                icon="el-icon-user-solid"
                :src="this.PersonalData.avatar"
              ></el-avatar>
            </el-upload>
          </el-form-item>
          <el-form-item label="昵称：">
            <el-input
              v-model="PersonalData.nickName"
              :disabled="editflag"
              class="input"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="性别：">
            <el-select v-model="PersonalData.gender"
            placeholder="请选择性别" :disabled="editflag" class="input">
              <el-option
                v-for="item in sex"
                :key="item.value"
                :label="item.value"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="电话：" prop="telephone">
            <el-input
              v-model="PersonalData.telephone"
              :disabled="editflag"
              class="input"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="E-mail：" prop="email">
            <el-input
              v-model="PersonalData.email"
              :disabled="editflag"
              class="input"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="所在地区：">
            <el-input
              v-model="PersonalData.zone"
              :disabled="editflag"
              class="input"
            >
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              icon="el-icon-edit"
              round
              v-show="editflag"
              @click="editflag = false"
              >编辑
            </el-button>
            <el-button
              icon="el-icon-check"
              type="success"
              round
              v-show="!editflag"
              @click="editUser('PersonalData')"
            >
              保存
            </el-button>
            <el-button round v-show="!editflag" @click="HandleCancel()"
              >取消
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="账户信息">
        <el-form label-width="20%">
          <el-form-item label="用户 ID："
            >{{ PersonalData.userId }}
          </el-form-item>
          <el-form-item label="密码：">
            <el-input
              v-model="PersonalData.password"
              :disabled="true"
              type="password"
              class="input"
            >
            </el-input>
            <el-button
              type="primary"
              icon="el-icon-edit"
              round
              @click="passwordchange = true"
              style="position: relative; left: 15px"
            >
              修改密码
            </el-button>
          </el-form-item>
          <!-- <el-form-item label="注册时间："
            >{{ PersonalData.registerTime }}
          </el-form-item> -->
          <el-form-item label="用户权限：">
            <span v-if="PersonalData.role == 0">一般用户 </span>
            <span v-else>管理员</span>
          </el-form-item>
          <el-form-item label="信誉度："
            >{{ PersonalData.reputation }}
          </el-form-item>
        </el-form>
        <el-dialog
          title="修改密码"
          :visible.sync="passwordchange"
          width="40%"
          center
        >
          <el-form
            :model="passForm"
            status-icon
            :rules="rules"
            ref="passForm"
            label-width="25%"
            style="margin-right: 5%"
          >
            <el-form-item label="原密码" prop="orgin">
              <el-input v-model="passForm.orgin"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="pass">
              <el-input
                type="password"
                v-model="passForm.pass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input
                type="password"
                v-model="passForm.checkPass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer">
            <el-button type="primary" @click="submitForm('passForm')" round
              >确认</el-button
            >
            <el-button @click="CancelChange" round>取消</el-button>
          </span>
        </el-dialog>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from '../../axios';

// const qs = require('qs');

export default {
  mounted() {
    const { user } = this.$store.state;
    if (!user) {
      this.$router.push('/');
    } else {
      this.PersonalData.nickName = user.nickName;
      this.nickName = user.nickName;
      this.PersonalData.gender = user.gender;
      this.gender = user.gender;
      this.PersonalData.telephone = user.telephone;
      this.telephone = user.telephone;
      this.PersonalData.email = user.email;
      this.email = user.email;
      this.PersonalData.zone = user.zone;
      this.zone = user.zone;
      this.PersonalData.avatar = user.avatar;
      this.PersonalData.userId = user.userId;
      this.PersonalData.password = user.password;
      this.PersonalData.role = user.role;
      this.PersonalData.reputation = user.reputation;
    }
    console.log(this.PersonalData);
  },
  data() {
    const checkOrgin = (rule, value, callback) => {
      if (value != this.PersonalData.password) {
        return callback(new Error('原密码输入错误！'));
      }
      return callback();
    };
    const validatePass = (rule, value, callback) => {
      if (value == '') {
        callback(new Error('请输入一个新密码！'));
      } else {
        if (this.passForm.checkPass !== '') {
          this.$refs.passForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value == '') {
        callback(new Error('请再次输入密码！'));
      } else if (value !== this.passForm.pass) {
        callback(new Error('两次输入密码不一致！'));
      } else {
        callback();
      }
    };
    const checkTelephone = (rule, value, callback) => {
      const phonereg = 11 && /^((13|14|15|16|17|18|19)[0-9]{1}\d{8})$/;
      if (value == '') {
        callback(new Error('手机号不能为空！'));
      } else if (!phonereg.test(value)) {
        callback(new Error('请输入有效的手机号码！'));
      } else {
        callback();
      }
    };
    const checkEmail = (rule, value, callback) => {
      const emailreg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (value == '') {
        callback(new Error('邮箱不能为空！'));
      } else if (!emailreg.test(value)) {
        callback(new Error('请输入有效的邮箱！'));
      } else {
        callback();
      }
    };
    return {
      PersonalData: {
        userId: -1,
        nickName: '',
        password: '',
        avatar: '',
        gender: '',
        telephone: '',
        email: '',
        zone: '',
        role: 0,
        reputation: 0,
      },
      dataRules: {
        telephone: [{ validator: checkTelephone, trigger: 'blur' }],
        email: [{ validator: checkEmail, trigger: 'blur' }],
      },
      nickName: '',
      gender: '',
      telephone: '',
      email: '',
      zone: '',
      fileList: [],
      editflag: true,
      passwordchange: false,
      passForm: {
        orgin: '',
        pass: '',
        checkpass: '',
      },
      rules: {
        pass: [{ validator: validatePass, trigger: 'blur' }],
        checkPass: [{ validator: validatePass2, trigger: 'blur' }],
        orgin: [{ validator: checkOrgin, trigger: 'blur' }],
      },
      sex: [{
        value: '男',
      },
      {
        value: '女',
      },
      {
        value: '保密',
      }],
    };
  },
  methods: {
    editUser(formName) {
      console.log(this.PersonalData);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios
            .updateUser(this.PersonalData)
            .then((res) => {
              console.log('上传成功');
              console.log(res);
            })
            .catch((err) => {
              console.log(err);
            });
          this.editflag = true;
          return true;
        }
        console.log('输入有误');
        return false;
      });
    },
    HandleCancel() {
      this.PersonalData.nickName = this.nickName;
      this.PersonalData.gender = this.gender;
      this.PersonalData.telephone = this.telephone;
      this.PersonalData.email = this.email;
      this.PersonalData.zone = this.zone;
      this.editflag = true;
    },
    submitForm(formName) {
      console.log(this.PersonalData);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.PersonalData.password = this.passForm.pass;
          console.log(this.PersonalData);
          axios
            .updateUser(this.PersonalData)
            .then(() => {
              console.log('上传成功');
              this.$message({
                message: '修改成功',
                type: 'success',
                duration: 1000,
                offset: 100,
              });
              this.passwordchange = false;
              console.log(this.PersonalData);
            })
            .catch((err) => {
              console.log(err);
            });
          return true;
        }
        console.log('error submit!!');
        return false;
      });
    },
    CancelChange() {
      this.$refs.passForm.resetFields();
      this.passwordchange = false;
    },
    // el-upload相关方法

    uploadSuccess(res) {
      // 图片上传成功后即调用的函数
      console.log(`图片上传成功${JSON.stringify(res)}`);
      if (res.code === 0) {
        // sysUser为form表单对象，当上传成功后，后端会动将图片存储在数据库，数据库会自动为该图片生成一个名字，
        // 提交form表单时需要向后台发送服务器生成的图片名的字段
        // this.sysUser.logoImg = res.data.uploadUrl
        this.PersonalData.avatar = res.data;
        console.log(this.PersonalData.avatar);
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

    handleExceed(files, fileList) {
      // 文件超出个数限制的钩子
      console.log(files);
      console.log(fileList);
      this.$message({
        message: `当前限制选择1个文件，本次选择了 ${
          files.length
        }个文件，共选择了${files.length + fileList.length}个文件`,
        type: 'warning',
        duration: 1000,
        offset: 100,
      });
    },

    uploading() {
      // 文件上传时的钩子
      console.log('正在上传...');
      this.confirmLoadig = true;
    },
    beforeAvatarUpload(file) {
      console.log(file);
    },
  },
};
</script>

<style lang="scss" scoped>
.user-info {
  margin-top: 80px;
  margin-left: 20%;
  margin-bottom: 5%;
  width: 60%;
  height: auto;
  font-size: 30px;
}
.input {
  width: 50%;
}
</style>
