<template>
    <div class="post-card">
        <div v-if="Posts.length!=0" class="post-card">
        <post-card
         v-for="item in this.Posts" :key="item.postID"
            :cardInfo="item"/>
        </div>
        <div  v-else>
          <img src="@/assets/empty.png">
            <p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;暂无需求！</p>
        </div>
    </div>
</template>
<script>
import PostCard from '../../components/PostCard.vue';
import axios from '../../axios';

export default {
  data() {
    return {
      Posts: [{
        // postID: 0,
        // userId: 0,
        // title: '还没有人发布过需求',
        // content: null,
        // time: null,
        // expectedPrice: 0,
      }],
    };
  },
  components: {
    PostCard,
  },
  mounted() {
    axios.getPosts()
      .then((res) => {
        console.log('post', res.data.data.records);
        this.Posts = res.data.data.records;
      });
  },
};
</script>
<style scoped>
.post-card{
  margin:4%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}
</style>
