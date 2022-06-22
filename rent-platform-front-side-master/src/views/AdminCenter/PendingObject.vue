<template>
  <div class="item-list">
    <div class="section-featured">
      <div class="section-featured-container">
        <div class="section-featured-main">
          <commodityReviewCard
              v-for="item in this.myCollects"
              :key="item.object_id"
              :cardInfo="item"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { WOW } from 'wowjs';
import axios from '../../axios';
import commodityReviewCard from "../../components/CommodityReviewCard";

export default {
  components: {
    commodityReviewCard,
  },
  data() {
    return {
      myCollects: [],
    };
  },
  mounted() {
    this.$nextTick(() => {
      // 在dom渲染完后,再执行动画
      const wow = new WOW({
        live: false,
      });
      wow.init();
    });
    axios.getPendingObj().then((res) => {
      this.myCollects = res.data.data.records;
    });
  },
};
</script>
<style lang="scss" scoped>
.item-list {
  width: 100%;
  height: auto;

.section-featured {
  width: 100%;
.section-featured-container {
  width: 100%;
  padding: 120px 40px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
.section-featured-title {
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 60px;
.section-title {
//这一块的实现需要学习
user-select: none;
  font-size: 15.6px;
  position: relative;
  color: gray;
  display: inline-block;
  margin-bottom: 18px;
&:before {
   display: block;
   position: absolute;
   top: 50%;
   width: 37px;
   height: 1px;
   background-color: #e63a28;
   right: calc(100% + 23px);
   content: "";
 }
&:after {
   display: block;
   position: absolute;
   top: 50%;
   width: 37px;
   height: 1px;
   background-color: #e63a28;
   left: calc(100% + 23px);
   content: "";
 }
}
.section-title-main {
  font-size: 45px;
  font-weight: 700;
}
.section-title-p {
  margin-top: 15px;
  margin-bottom: 0;
  color: gray;
  font-family: Quicksand;
  font-size: 15.4px;
  font-style: normal;
  font-weight: normal;
  letter-spacing: -0.025em;
  line-height: 1.63;
  text-align: center;
  width: 520px;
}
}

.section-featured-main {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  margin-left: -30px;
  width: 1470px;
.section-featured-card {
  flex-shrink: 0;
  margin-left: 30px;
  margin-bottom: 30px;
  width: 360px;
  height: 535px;
  background-color: lightpink;
  border-radius: 20px;
  transition: all 0.3s ease;
&:hover {
   box-shadow: 25px 25px 50px #b8b8b8;
 }
}
}

.load-more {
  user-select: none;
  margin-top: 70px;
  height: 50px;
  color: white;
  background-color: #e63a28;
  border-radius: 25px;
  font-size: 17.2px;
  font-weight: 400;
  width: 150px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: all 0.3s ease;
&:hover {
   background-color: #c43323;
 }
}
}
}
}
</style>
