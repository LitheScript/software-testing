<template>
<!--需求信息卡片 -->
  <div class="post-card">
              <div class="section-featured-card-main">
                <div class="card-title">{{cardInfo.title}}</div>
                <div class="card-description">{{cardInfo.content}}</div>
                <div class="price">
                  <div class="price-title">预期价格</div>
                  <div class="price-content">￥{{cardInfo.expectedPrice}}/天</div>
                </div>
                <div class="line"></div>
                <div class="bottom-part">
                  <div class="time">发布时间&nbsp;&nbsp;
                    <div class="time-content">{{cardInfo.time}}</div>
                  </div>
                </div>
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
};
</script>

<style lang="scss" scoped>
        .post-card{
          flex-shrink: 0;
          margin-left: 30px;
          margin-bottom: 30px;
          width:300px;
          height:420px;
          background-color: rgb(238,242,251);
          border-radius: 20px;
          transition: all .3s ease;
          &:hover{
            box-shadow:  25px 25px 50px #b8b8b8;
          }

          .section-featured-card-main{
            padding:32px;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: flex-start;
            .card-title{
              font-size: 23px;
              color:rgb(51, 51, 51)
            }
            .card-description{
              width:100%;
              height: 200px;
              font-size: 14px;
              margin-bottom: 17px;
              color:gray;
              overflow: hidden;
              // text-overflow:ellipsis;
              // white-space: nowrap;
            }
            .price{
              width: 100%;
              display: flex;
              flex-direction: row;
              justify-content: flex-start;
              align-items: center;
              font-size: 15.6px;
              margin-bottom: 10px;
              .price-title{
                color:gray;
                margin-right: 10px;
              }
              .price-content{
                color:rgb(241, 68, 68);
              }
            }

            .line{
              width:100%;
              height:0;
              border-bottom: #e0e0e0 1px solid;
              margin-top: 10px;
              margin-bottom: 20px;
            }

            .bottom-part{
              width:100%;
              display: flex;
              flex-direction: row;
              justify-content: space-between;
              align-items: center;
              .time{
              width:100%;
              display: flex;
              flex-direction: row;
              justify-content: flex-start;
              align-items: center;
              .time-icon{
                width:40px;
                height:40px;
                border-radius: 20px;
                // background-image: url('../');
                background-color: aqua;
                margin-right: 15px;
              }
              .time-content{
                font-size: 15.6px;
              }
            }
            }
          }
        }
</style>
