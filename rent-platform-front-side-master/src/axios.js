//axios.js
import axios from 'axios'
import store from './store'
import router from './router'

//创建axios实例
var instance = axios.create({
    timeout: 5000, //请求超过5秒即超时返回错误
    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
});
//request拦截器
instance.interceptors.request.use(
    config => {
        //判断是否存在token，如果存在的话，则每个http header都加上token
        if (store.state.token) {
            config.headers.Authorization = `Bearer ${store.state.token}`;
        }
        return config;
    }
);
 var url = 'http://101.35.194.132:9090/';  //服务器连接
// var url = 'http://localhost:9090/';  //服务器连接
export default {
  //这里export的是axios
  //所以引用的方法是: import axios from '../axios.js';
  //用户注册
  userEnable(data){
    return instance.post(url+'/auth/enable', data);
  },
  //用户登录(只是拿到token)
  userLogin(data){
    return instance.post(url+'/user/login', data);
  },
  //获取用户
  getUserByToken(data){
    return instance.post(url+'/users/userByToken',data);
  },
  //删除用户
  delUser(data){
    return instance.post('/api/delUser', data);
  },
  //获取课程
  getCourses(data){
    return instance.post(url+'/course/myCourses',data);
  },
  getProjects(data){
    return instance.post(url+'/project/myProjects',data);
  },
  getRecommendList(){
    return instance.get(url+'/object/recommend');
  },
  getListByType(data){
    return instance.get(url+'/object/getObjectListByType?'+'type='+data.type+'&page='+data.page);
  },
  searchListByName(data){
    return instance.get(url+'/object/searchObject?'+'name='+data.name+'&page='+data.page);
  },
  getObjectDetail(id){
    return instance.get(url+'/object/getObjectDetail?'+'object_id='+id);
  },
  getUserByToken(data){
    return instance.post(url+'user/getUserByToken',data);
  },
  uploadObject(data){
    return instance.post(url+'object/uploadObject?'+'user_id='+data.user_id+'&name='+data.name+'&description='+data.description+'&deposit='+data.deposit+'&tag='+data.tag+'&rent_daily'+data.rent_daily+'&new_level='+data.new_level+'&url='+data.url);
  },
  uploadPicture(data){
    return instance.post(url+'picture/upload',data);
  },
  getOrderList(id){
    return instance.get(url+'/order/getOrderList?user_id='+id);
  },
  getMyObject(id){
    return instance.get(url+'object/viewOwnObjectList?user_id='+id);
  },
  deleteObject(id){
    return instance.get(url+'object/removeObject?object_id='+id);
  },
  getPosts(){
    return instance.get(url+'/post/viewAllPosts');
  },
  getMyPost(id){
    return instance.get(url+'/post/viewMyPosts?user_id='+id);
  },
  publishPost(data){
    return instance.post(url+'/post/publishPost?user_id='+data.user_id+'&title='+data.title+'&content='+data.content+'&time='+data.time);
  },
  deletePost(id){
    return instance.post(url+'/post/deleteMypost?post_id='+id);
  },
  register(data){
    return instance.post(url+'/user/insert',data);
  },
  getCommentByItemId(id){
    return instance.get(url+'/remarks/getRemarkList?object_id='+id);
  },
  createOrder(data){
    return instance.post(url+'/order/generateOrder?object_id='+data.objectId+'&borrower_id='+data.userId+'&lentout_time='+data.time1+'&return_time='+data.time2+'&campus='+data.zone);
  },
  cancleOrder(id){
    return instance.get(url+'/order/cancelOrder?order_id='+id);
  },
  pay(id){
    return instance.get(url+'/order/pay?order_id='+id);
  },
  comment(data){
    return instance.put(url+'/remarks/giveRemark?object_id='+data.objectId+'&user_id='+data.userId+'&content='+data.content+'&grade='+data.star+'&type=1');
  },
  collect(id){
    return instance.post(url+'/fav/collect?fav_id=1&object_id='+id);
  },
  getCollect(){
    return instance.get(url+'/fav/viewFavouriteCart?fav_id=1');
  },
  updateUser(data){
    return instance.post(url+'user/modifyUserInfo?'+'user_id='+data.userId+'&nick_name='+data.nick_name+'&password='+data.password+'&avatar='+data.avatar+'&gender='+data.gender+'&telephone='+data.telephone+'&email='+data.email+'&zone='+data.zone);
  },
  getPendingObj() {
      return instance.get(url + 'object/viewPendingObject?page=1');
  },
  checkPass(id){
      return instance.get(url + 'object/verifyObject?object_id='+id +'&check=true');
  },
  removeObj(id){
      return instance.get(url + 'object/removeObject?object_id='+id );
  }

}
