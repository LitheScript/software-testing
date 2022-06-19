//axios.js
import axios from 'axios'


//创建axios实例
var instance = axios.create({
    timeout: 5000, //请求超过5秒即超时返回错误
    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
});


var url = 'http://101.35.194.132:81/';  //服务器连接
// var url = 'http://localhost:9090/';  //服务器连接

export default {
  //这里export的是axios
  //所以引用的方法是: import axios from '../axios.js';
  getCalendars(){
    return instance.get(url+'queryCalendar');
  },
  getTriangles(){
    return instance.get(url+'queryTriangle');
  },
  getCommissions(){
    return instance.get(url+'queryCommission');
  },
}
