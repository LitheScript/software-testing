<template>
          <div class="myobject">
            <object-card
            v-for="item in this.myObject" :key="item.object.object_id"
            :cardInfo="item"
            ></object-card>
          </div>
</template>
<script>
import objectCard from '../../components/ObjectCard.vue';
import axios from '../../axios';

export default {
  components: {
    objectCard,
  },
  data() {
    return {
      myObject: [],
    };
  },
  mounted() {
    axios.getMyObject(this.$store.state.user.userId)
      .then((res) => {
        this.myObject = res.data.data;
        console.log(res.data.data);
      });
  },
};
</script>
<style>
.myobject{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        margin: 5% 5% 5% 14%;
        width:1170px;
}
</style>
