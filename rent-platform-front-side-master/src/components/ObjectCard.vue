<template>
  <div class="object-card">
                  <img class="picture" :src="cardInfo.url"/>
              <div class="section-featured-card-main">
                <div class="card-title">{{cardInfo.object.name}}</div>
                <div class="card-description">{{cardInfo.object.description}}</div>
                <div class="price">
                  <div class="price-title">租金</div>
                  <div class="price-content">￥{{cardInfo.object.rent_daliy}}/天</div>
                </div>
                <div class="line"></div>
                <div class="bottom-part">
                  <div class="status">
                    <div class="status-name">{{cardInfo.object.status}}</div>
                  </div>
                <div>
                    <el-button type="danger" icon="el-icon-delete" @click="delobject()">删除
                    </el-button>
                </div>
                </div>
              </div>
  </div>
</template>

<script>
import axios from '../axios';

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
    delobject() {
      axios.deleteObject(this.cardInfo.object.object_id)
        .then(() => {
          this.$message({
            message: '删除成功',
            type: 'success',
            offset: 100,
          });
          axios.getMyObject(this.$store.state.user.userId)
            .then((res) => {
              this.$parent.myObject = res.data.data;
              console.log(res.data.data);
            });
        });
    },
  },
};
</script>

<style lang="scss" scoped>
        .object-card{
          flex-shrink: 0;
          margin-left: 30px;
          margin-bottom: 30px;
          width:360px;
          height:520px;
        background-color: rgb(212,234,255);
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
              font-size: 14px;
              margin-bottom: 17px;
              color:gray;
              overflow: hidden;
              text-overflow:ellipsis;
              white-space: nowrap;
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
              .status{
              width:100%;
              display: flex;
              flex-direction: row;
              justify-content: flex-start;
              align-items: center;
              .status-name{
                font-size: 15.6px;
              }
            }
            }
          }
        }
</style>
