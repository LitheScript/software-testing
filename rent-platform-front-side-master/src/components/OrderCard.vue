<template>
<!--商品信息卡片 -->
  <div class="order-card" @click="goDetail">
            <img class="picture" :src="cardInfo.picture_url"/>
              <div class="section-featured-card-main">
                <div class="card-row">订单 ID：{{this.cardInfo.order_id}}</div>
                <div class="card-row">创建时间：{{formatTime(this.cardInfo.created_time)}}</div>
                <div class="card-row">借用时间：{{formatTime(this.cardInfo.lentout_time)}}</div>
                <div class="card-row">归还时间：{{formatTime(this.cardInfo.return_time)}}</div>
                <div class="card-row">订单状态：{{this.cardInfo.status}}</div>
              </div>
  </div>
</template>

<script>
export default {
  props: {
    cardInfo: Object(),
    required: true,
  },
  data() {
    return {
    };
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
  },
};
</script>

<style lang="scss" scoped>
    .order-card{
        flex-shrink: 0;
        margin-left: 30px;
        margin-bottom: 30px;
        width:360px;
        height:520px;
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
        height:250px;
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
            margin-top: 10px;
            margin-bottom: 20px;
        }
    }
}
</style>
