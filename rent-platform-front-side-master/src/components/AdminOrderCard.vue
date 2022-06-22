<template>
<!--商品信息卡片 -->
  <div class="order-card">
    <img class="picture"  @click="goDetail" :src="cardInfo.picture_url"/>
    <div class="section-featured-card-main">
      <div class="card-row">订单 ID：{{this.cardInfo.order_id}}</div>
      <div class="card-row">创建时间：{{formatTime(this.cardInfo.created_time)}}</div>
      <div class="card-row">借用时间：{{formatTime(this.cardInfo.lentout_time)}}</div>
      <div class="card-row">归还时间：{{formatTime(this.cardInfo.return_time)}}</div>
      <div class="card-row">订单状态：{{this.cardInfo.status}}</div>
    </div>
    <div class="line"></div>
<a class="uk-button" @click="checkReturned()" :style="{backgroundColor:this.buttonColor}">确认归还</a>
  </div>
</template>

<script>
import axios from "../axios";

export default {
  props: {
    cardInfo: Object(),
    required: true,
  },
  data() {
    return {
    buttonColor: '#2B5AE1',
    };
  },
  created() {
    if(this.cardInfo.status=="待评价") {
      this.buttonColor="gray"
    }
  },
  methods: {
    goDetail() {
      this.$router.push({
        path: `/homepage/ItemDetail/${this.cardInfo.object_id}`,
      });
    },
    formatTime(time) {
      return time.replace('T', ' ');
    },
    checkReturned() {
      axios.returnObject(this.cardInfo.order_id)
          .then((res) => {
            console.log(res)
            this.$message({
              message: res.data.msg,
              type: 'success',
              offset: 100,
            });
            console.log("成功归还")
          });
    },
  },
};
</script>

<style lang="scss" scoped>
    .order-card{
        flex-shrink: 0;
        margin-left: 30px;
        margin-bottom: 30px;
        width:360px;
        height:500px;
        background-color: whitesmoke;
        border-radius: 20px;
        transition: all .3s ease;
        &:hover{
        box-shadow:  25px 25px 50px #b8b8b8;
        .picture{
            filter: brightness(100%);
        }

        }

        .picture{
        width:100%;
        height:200px;
        object-fit: cover;
        border-radius: 20px;
        transition: all .3s ease;
        filter: brightness(85%);
        margin-bottom: 10px;
        }

        .section-featured-card-main{
        padding:32px;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: flex-start;
        .card-row{
            font-size: 15px;
            color:gray;
            margin-bottom:10px;
        }
        .card-title{
            font-size: 23px;
            color:rgb(51, 51, 51)
        }
        .line{
            width:100%;
            height:0;
            border-bottom: #e0e0e0 1px solid;
            margin-top: 0px;
            margin-bottom: 10px;
        }
    }
}


    .uk-button {
      //background-color: #2B5AE1;
      margin-left: 135px;
      color: white;
      -webkit-transition: 0.5s;
      -o-transition: 0.5s;
      height: 50px;
      padding: 0 1.67em;
      border-radius: 1.39em;
      font-size: 14px;
      letter-spacing: -0.025em;
      line-height: 50px;
      text-transform: none;
      transition: 0.5s;
    }

</style>
