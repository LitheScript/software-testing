<template>
    <div style="margin:4%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;">
        <post-card
         v-for="item in this.Posts" :key="item.postID"
            :cardInfo="item"/>
    </div>
</template>
<script>
import PostCard from '../../components/PostCard.vue';
import axios from '../../axios';

export default {
  data() {
    return {
      Posts: [{
        postID: 0,
        userId: 0,
        title: '还没有人发布过需求',
        content: null,
        time: null,
        expectedPrice: 0,
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
