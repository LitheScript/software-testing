<template>
<div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
@import './assets/font/font.css';
*{
  box-sizing: border-box;
}

html,body{
  padding:0;
  margin:0;
  width:100%;
}

#app{
  font-family: round;
}

.left-slide-enter, .right-slide-leave-to{
    opacity: 0;
    transform: translateX(100%);
}
.left-slide-leave-to, .right-slide-enter{
    opacity: 0;
    transform: translateX(-100%);
}
.left-slide-enter-active, .left-slide-leave-active,
.right-slide-enter-active, .right-slide-leave-active{
  transition: all 1s ease;
}

.left-slide-leave-active,.right-slide-enter-active{
  position: absolute;
}
</style>
