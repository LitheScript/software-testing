<template>
  <div id="home-page">
    <!-- 头部导航栏选择绝对布局 -->
    <div class="head-box-container" ref="head">
      <div class="head-box">
        <div class="head-box-left"></div>
        <div class="head-box-right">
          <div class="head-box-menu">
            <router-link to="/" class="head-box-menu-item"
              ><i class="el-icon-s-home" />首页</router-link
            >
            <router-link
              :to="{ path: '/homepage/items', query: { mode: 0 } }"
              class="head-box-menu-item"
              ><i class="el-icon-s-goods" />出租品</router-link
            >
            <router-link to="/homepage/posts" class="head-box-menu-item"
              ><i class="el-icon-s-order" />需求中心</router-link
            >
            <router-link to="/homepage/user" class="head-box-menu-item"
              ><i class="el-icon-user-solid" />个人主页</router-link
            >
          </div>
          <router-link to="/login" class="sign-in" v-show="login"
            >登录</router-link
          >
          <router-link to="/homepage/rent">
            <div class="head-box-btn">
              <div class="head-box-btn-content">我要出租</div>
            </div>
          </router-link>
          <div class="user-active" v-show="!login">
            <el-dropdown style="margin-top: 8px">
              <span>
                <el-avatar :src="user.avatar" :size="50" />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-button
                    icon="el-icon-switch-button"
                    type="text"
                    @click="logout()"
                  >
                    退出登录
                  </el-button>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    login() {
      return !this.$store.state.login;
    },
    user() {
      return this.$store.state.user || this.userDefault;
    },
  },
  data() {
    return {
      option: false,
      searchInput: '',
      userDefault: {
        userId: -1,
        nickName: '',
        password: '',
        avatar: '',
        gender: '',
        telephone: '',
        email: '',
        zone: null,
        registerTime: null,
        role: 0,
        reputation: 0,
      },
    };
  },
  methods: {
    logout() {
      this.$store.commit('LOGOUT');
      this.$router.push('/');
    },
  },
};
</script>

<style lang="scss" scoped>
.head-box-container {
  position: fixed;
  background-color: white;
  top: 0;
  height: 80px;
  width: 100%;
  padding: 0 40px;
  z-index: 99999;
  transition: all 0.5s ease;

  .head-box {
    height: 100%;
    width: 100%;
    padding: 30px 0;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .head-box-left {
      height: 60px;
      width: 187px;
      background-image: url("../assets/img/logo.png");
    }

    .head-box-right {
      display: flex;
      flex-direction: row;
      justify-content: flex-end;
      align-items: center;

      .head-box-menu {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        padding-right: 35px;

        .head-box-menu-item {
          user-select: none;
          width: 90px;
          height: 60px;
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: center;
          transition: all 0.3s ease;
          text-decoration: none;
        }

        .head-box-menu-item:hover {
          color: rgba(245, 42, 42, 0.616);
        }
      }

      .sign-in {
        user-select: none;
        text-decoration: underline;
        margin-right: 53px;
        display: flex;
        flex-direction: row;
        align-items: center;

        &:before {
          content: "";
          display: inline-block;

          width: 20px;
          height: 20px;

          background-image: url("../assets/Login.svg");
          background-size: cover;
          background-repeat: no-repeat;
          margin-right: 3px;
        }
      }

      .sign-in:hover {
        color: rgba(245, 42, 42, 0.616);
      }

      .head-box-btn {
        user-select: none;
        font-size: 16px;
        background-color: rgb(43, 90, 225);
        color: white;
        width: 150px;
        height: 50px;
        border-radius: 25px;
        margin-right: 30px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        background-image: url("../assets/add.png");
        background-repeat: no-repeat;
        background-size: 25px 25px;
        background-position: left 20px center;
        transition: all 0.3s ease;

        .head-box-btn-content {
          flex-shrink: 0;
          margin-left: 30px;
        }
      }

      .head-box-btn:hover {
        background-color: rgb(34, 69, 175);
      }

      .user-active {
        display: flex;
        flex-direction: row;
        align-items: center;
        .message {
          display: flex;
          flex-direction: row;
          height: auto;
          margin-left: 20px;
          margin-right: 20px;
          .red-point-box {
            width: 0;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            .red-point {
              border: 3.6px solid red; /*设置红色*/
              border-radius: 3.6px; /*设置圆角*/
            }
          }
        }
      }
    }
  }
}
</style>
