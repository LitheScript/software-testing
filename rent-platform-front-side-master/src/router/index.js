import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/homepage',
  },
  {
    path: '/homepage',
    name: 'HomePage',
    redirect: '/homepage/home',
    component: () => import('../views/HomePage.vue'), // lazy-load
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/Home.vue'), // lazy-load
      },
      {
        path: 'items',
        name: 'Items',
        component: () => import('../views/ItemList.vue'), // lazy-load
      },
      {
        path: 'ItemDetail/:id',
        name: 'ItemDetail',
        component: () => import('../views/ItemDetail.vue'), // lazy-load
        meta: {
          requiresAuth: true,
        },
      },
      {
        path: 'rent',
        name: 'Rent',
        component: () => import('../views/RentItem.vue'),
        meta: {
          requiresAuth: true,
        },
      },
      {
        path: 'posts',
        name: 'Posts',
        component: () => import('../views/Posts.vue'),
        redirect: '/homepage/posts/forum',
        meta: {
          requiresAuth: true,
        },
        children: [
          {
            path: 'forum',
            name: 'Forum',
            component: () => import('../views/Posts/Forum.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
          {
            path: 'mypost',
            name: 'MyPost',
            component: () => import('../views/Posts/MyPost.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
          {
            path: 'publish',
            name: 'Publish',
            component: () => import('../views/Posts/Publish.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
        ],
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('../views/UserCenter.vue'), // lazy-load，
        redirect: '/homepage/user/info',
        meta: {
          requiresAuth: true,
        },
        children: [
          {
            path: 'info',
            name: 'Info',
            component: () => import('../views/UserCenter/Info.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
          {
            path: 'myorder',
            name: 'MyOrder',
            component: () => import('../views/UserCenter/MyOrder.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
          {
            path: 'myobject',
            name: 'MyObject',
            component: () => import('../views/UserCenter/MyObject.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
          {
            path: 'mycollect',
            name: 'MyCollect',
            component: () => import('../views/UserCenter/MyCollect.vue'), // lazy-load
            meta: {
              requiresAuth: true,
            },
          },
        ],
      },
    ],
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../views/AdminCenter.vue'),
    redirect: '/admin/pendingObj',
    meta: {
      requiresAuth: true,
    },
    //以下子路径其实没有发挥作用，该bug以后再说吧。
    children: [
      {
        path: 'pendingObj',
        name: 'PendingObj',
        component: () => import('../views/AdminCenter/PendingObject.vue'), // lazy-load
        meta: {
          requiresAuth: true,
        },
      },
      {
        path: 'returnOrder',
        name: 'ReturnOrder',
        component: () => import('../views/AdminCenter/ReturnOrder.vue'), // lazy-load
        meta: {
          requiresAuth: true,
        },
      },
    ],
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/adminlogin',
    name: 'AdminLogin',
    component: () => import('../views/AdminLogin.vue'),
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue'),
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const { token } = store.state;
  console.log(token);
  // 判断要去的路由有没有requiresAuth
  if (to.meta.requiresAuth) {
    if (store.state.login) {
      next();// 已经登陆，放行
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath }, // 将刚刚要去的路由path作为参数，方便登录成功后直接跳转到该路由
      });
    }
  } else {
    next();
  }
});

export default router;
