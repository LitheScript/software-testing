<template>
  <div class="register">
    <div class="login-box wow fadeInRight">
      <div class="left">
        <div class="head-bar">
          <div class="icon" @click="goBack()"></div>
        </div>
        <div class="main">
          <div class="main-title">注册</div>
          <div class="main-form">
            <el-input placeholder="用户名" v-model="name"/>
            <el-input placeholder="密码"
            type="password" v-model="psw"
            maxlength="15" show-password/>
            <el-input placeholder="再次输入密码"
            type="password" v-model="checkpsw"
            maxlength="15" show-password/>
          </div>
          <div class="main-lower">
            <div class="login-btn" @click="Register()">注册</div>
            <div class="go-login" @click="goLogin()">登录</div>

          </div>
        </div>
        <div class="bottom">
          <div class="bottom-text"></div>
        </div>
      </div>
      <div class="right">
        <div class="title">找到你想要的</div>
        <div class="content">Rent what you want<br>Can find almost all common items</div>
      </div>
    </div>
  </div>
</template>

<script>
import { WOW } from 'wowjs';
import axios from '../axios';

const qs = require('qs');
// 格式化数据用的，很重要
export default {
  mounted() {
    this.$nextTick(() => {
      // 在dom渲染完后,再执行动画
      const wow = new WOW({
        live: false,
      });
      wow.init();
    });
  },
  data() {
    return {
      name: '',
      psw: '',
      checkpsw: '',
    };
  },
  methods: {
    goBack() {
      this.$router.push('/');
    },
    goLogin() {
      this.$router.push('/login');
    },
    Register() {
      // ....逻辑代码
      if (this.psw !== this.checkpsw)
        alert('两次输入密码不一致');
      else {
        const data = {
          nickName: this.name,
          password: this.psw,
        };
        console.log('data', qs.stringify(data));
        axios.register(qs.stringify(data))
          .then((res) => {
            console.log(res.data)
            if(res.data.code==-1)  alert(res.data.msg)
            else if(res.data.code==0){
              alert("注册成功")
              this.$router.push('/login');
            }
             else alert("注册失败，原因未知。")
          });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.register{
  position:absolute;
  background-color: whitesmoke;
  top:0;
  bottom:0;
  left:0;
  right:0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .login-box{
    height:600px;
    width:960px;
    border-radius: 15px;
    background-color: gray;
    display: flex;
    flex-direction: row;
    .left{
      height:100%;
      width:35%;
      border-top-left-radius: 15px;
      border-bottom-left-radius: 15px;
      background-color: white;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      .head-bar{
        width:100%;
        height:35px;
        padding-top: 10px;
        padding-left: 10px;
        .icon{
          height:25px;
          width:25px;
          background-image: url('../assets/back.png');
          background-size:cover ;
          background-position:center;
          background-repeat: no-repeat;
          transition: all .3s ease;

          &:hover{
            transform: scale(1.2);
          }
        }
      }

      .main{
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        padding-left:12%;
        padding-right: 12%;
        margin-bottom: 50px;
        .main-title{
          font-size: 34px;
          color:rgb(49, 49, 49);
          //margin-bottom: 20px;
        }
        .main-form{
          display: flex;
          flex-direction: column;
          align-items: center;
          width:100%;
        }

        .main-lower{
          //padding-left: 10px;
          padding-right: 10px;
          margin-top: 20px;
          width:100%;
          display: flex;
          flex-direction: row;
          justify-content: flex-start;
          align-items: center;
          .go-login{
            user-select: none;
            font-size: 13px;
            color:gray;
            transition: all .3s ease;

            &:hover{
              color: #276ac2;
            }
          }
          .login-btn{
            user-select: none;
            width:90px;
            height:36px;
            border-radius: 18px;
            font-size: 15.6px;
            background-color: #2f80eb;
            color:white;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            margin-right: 20px;
            transition: all .3s ease;

            &:hover{
              background-color: #276ac2;
            }
          }
        }
      }

      .bottom{
        width:100%;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        padding-left:12%;
        padding-right: 12%;
        padding-bottom: 15px;
        .bottom-text{
           user-select: none;
            font-size: 13px;
            color:gray;
            transition: all .3s ease;

            &:hover{
              color: #276ac2;
            }
        }
      }
    }
    .right{
      height:100%;
      width:65%;
      border-top-right-radius: 15px;
      border-bottom-right-radius: 15px;
      background-color: lightblue;
      background-image: url('../assets/img/bg-banner-custome.jpg');
      background-size:cover ;
      background-position:80% 50%;
      background-repeat: no-repeat;
      padding:30px;
      padding-left: 40px;
      .title{
        font-size: 30px;
        margin-top: 20%;
      }
      .content{
        margin-top: 25px;
        color:gray;
        font-size: 15.6px;
        width:300px;
      }
    }
  }
}
 ::v-deep .el-input__inner {
         width:100%;
            height:44px;
            border-radius: 22px;
            outline: none;
            border: 1px solid #e5e5e5;
            margin-top: 10px;
            margin-bottom: 10px;
            transition: .2s ease-in-out;
            font-size: 15px;
            color:#222222;
            padding-left: 20px;
            padding-right: 10px;

            &:focus{
              border:#2f80eb 1px solid;
            }
      }
</style>
