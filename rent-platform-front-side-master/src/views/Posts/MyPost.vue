<template>
    <div class="post-card">
        <div v-if="this.MyPost.length!=0" class="post-card">
        <my-publish
         v-for="item in this.MyPost" :key="item.postID"
            :cardInfo="item"/>
        </div>
        <div  v-else>
          <img src="@/assets/empty.png">
            <p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;暂无需求！</p>
        </div>
    </div>
</template>
<script>
import axios from '../../axios';
import MyPublish from '../../components/MyPublish.vue';

export default {
  data() {
    return {
      MyPost: [{
        // post: {
        //   expectedPrice: 0,
        //   postId: -1,
        //   title: '你还没有发布过需求',
        //   userId: this.$store.state.user.userId,
        //   content: '',
        // },
        // avator: '',
      }],
    };
  },
  components: {
    MyPublish,
  },
  mounted() {
    axios.getMyPost(this.$store.state.user.userId)
      .then((res) => {
        this.MyPost = res.data.data;
        console.log('"mypost"', this.MyPost);
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
