<template>
  <!--商品信息卡片 -->
  <div class="commodity-card" @click="goDetail">
    <img class="picture" :src="cardInfo.picture_url"/>
    <div class="section-featured-card-main">
      <div class="card-grid">
        <div class="card-title">{{ cardInfo.name }}</div>
        <div class="card-tag">{{ cardInfo.tag }}</div>
        <div class="card-tag">{{ cardInfo.new_level }}</div>
      </div>
      <div class="card-description">{{ cardInfo.description }}</div>
      <div class="price">
        <div class="price-title">租金</div>
        <div class="price-content">￥{{ cardInfo.rent_daliy }}/天</div>
        <div style="width: 100px;height: 50px"></div>
        <div class="price-title">押金</div>
        <div class="price-content">￥{{ cardInfo.deposit }}/天</div>
      </div>
      <div class="line"></div>
      <div class="bottom-part">
        <div class="owner">
          <img class="owner-icon" :src="cardInfo.avatar"/>
          <div class="owner-name">{{ cardInfo.nick_name }}</div>
          <div class="check">
            <div><a class="uk-button uk-button-normal" @click="checkProved()">通过审核</a></div>
            <div><a class="uk-button uk-button-danger" @click="checkFailed()">下架物品</a></div>
          </div>
        </div>

      </div>

    </div>
  </div>
</template>

<script>
import axios from '../axios';

export default {
  props: {
    cardInfo: Object,
    required: true,
  },
  data() {
    return {
      heartColor: 'black',
      collected: false,

    };
  },
  methods: {
    goDetail() {
    },
    checkProved() {
      axios.checkPass(this.cardInfo.object_id)
        .then(() => {
        });
    },
    checkFailed() {
      axios.removeObj(this.cardInfo.object_id)
        .then(() => {
        });
    },

  },
};
</script>

<style lang="scss" scoped>
.uk-button-normal {
  background-color: #2B5AE1;
  color: white;
}

.uk-button-danger {
  background-color: #E63A28;
}

.uk-button {
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

.commodity-card {
  flex-shrink: 0;
  margin-left: 30px;
  margin-bottom: 30px;
  width: 380px;
  height: 520px;
  background-color: whitesmoke;
  border-radius: 20px;
  transition: all .3s ease;

  &:hover {
    box-shadow: 25px 25px 50px #b8b8b8;

    .picture {
      filter: brightness(100%);
    }

  }

  .picture {
    width: 100%;
    height: 250px;
    object-fit: cover;
    border-radius: 20px;
    transition: all .3s ease;
    filter: brightness(85%);
  }

  .section-featured-card-main {
    padding: 32px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;

    .card-grid {
      width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      font-size: 15px;
      margin-bottom: 10px;

      .card-title {
        font-size: 23px;
        color: rgb(51, 51, 51)
      }

      .card-tag {
        border-style: solid;
        border-width: 1px;
        border-color: grey;
        border-radius: 40px;
        font-size: 12px;
        color: grey;
      }
    }

    .card-description {
      width: 100%;
      font-size: 14px;
      margin-bottom: 17px;
      color: gray;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }

    .price {
      width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      font-size: 15.6px;
      margin-bottom: 10px;

      .price-title {
        color: gray;
        margin-right: 10px;
      }

      .price-content {
        color: rgb(241, 68, 68);
      }
    }

    .line {
      width: 100%;
      height: 0;
      border-bottom: #e0e0e0 1px solid;
      margin-top: 10px;
      margin-bottom: 20px;
    }

    .bottom-part {
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;

      .check {
        width: 100%;
        display: flex;
        justify-content: flex-end;
        flex-direction: row;
      }

      .owner {
        width: 100%;
        display: flex;
        justify-content: flex-start;
        flex-direction: row;

        align-items: center;

        .owner-icon {
          width: 40px;
          height: 40px;
          border-radius: 20px;
          // background-image: url('../');
          background-color: aqua;
          margin-right: 15px;
        }

        .owner-name {
          font-size: 15.6px;
        }
      }
    }
  }
}
</style>
