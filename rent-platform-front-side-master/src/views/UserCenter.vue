<template>
  <div class="user-page">
    <div class="section-banner">
      <div class="section-banner-main">
        <img class="avatar" :src="user.avatar"/>
        <div class="username">{{user.nickName}}</div>
        <div class="nav-bar">
          <router-link to="/homepage/user/info" class="nav-item">个人资料</router-link>
          <div class="split">|</div>
          <router-link to="/homepage/user/myorder" class="nav-item">订单中心</router-link>
          <div class="split">|</div>
          <router-link to="/homepage/user/myobject" class="nav-item">我的物品</router-link>
          <div class="split">|</div>
          <router-link to="/homepage/user/mycollect" class="nav-item">收藏夹</router-link>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    user() {
      return this.$store.state.user;
    },
  },
};
</script>
<style lang="scss" scoped>
.user-page{
  width:100%;
  .section-banner{
    margin-top: 80px;
    padding-top:70px;
    padding-bottom: 70px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-image: url('../assets/img/bg-banner-article.jpg');
    background-size:cover;
    background-position:50% 50%;
    background-repeat: no-repeat;
    .section-banner-main{
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
      .avatar{
        width:80px;
        height: 80px;
        border-radius: 40px;
        background-color: gray;
        margin-bottom: 10px;
      }
      .username{
        user-select: none;
        font-size: 19px;
        text-align: center;
        color: #222222;
        padding-bottom: 18px;
      }
      .nav-bar{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        .split{
          margin-left:15px;
          margin-right: 18px;
          color:gray;
        }
        .nav-item{
          user-select: none;
          font-size: 15.6px;
          color:#222222;
          transition: all .3s ease;
          text-decoration: none;
          &:hover{
            color:#E63A28;
          }
        }
      }
    }
  }
}
</style>
