<template>
    <div style="margin:4%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;">
        <my-publish
         v-for="item in this.MyPost" :key="item.postID"
            :cardInfo="item"/>
    </div>
</template>
<script>
import axios from '../../axios';
import MyPublish from '../../components/MyPublish.vue';

export default {
  data() {
    return {
      MyPost: [{
        post: {
          expectedPrice: 0,
          postId: -1,
          title: '你还没有发布过需求',
          userId: this.$store.state.user.userId,
          content: '',
        },
        avator: '',
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
      });
  },
};
</script>
