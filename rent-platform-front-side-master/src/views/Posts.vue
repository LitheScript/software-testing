<template>
<div>
    <img
      src="../assets/img/bg-banner-custome.jpg"
      style="z-index: -100; position: fixed; top: 8%; bottom: 0; height: 92%"
    />
    <el-container class="post-page">
        <el-aside>
        <el-menu  style="border-radius: 30px;">
            <router-link to="/homepage/posts/forum">
            <el-menu-item>
                <i class="el-icon-postcard"/>
                <span slot="title">需求广场</span>
            </el-menu-item>
            </router-link>
            <router-link to="/homepage/posts/publish">
            <el-menu-item>
                <i class="el-icon-plus"/>
                <span slot="title">发布需求</span>
            </el-menu-item>
            </router-link>
            <router-link to="/homepage/posts/mypost">
            <el-menu-item>
                <i class="el-icon-bangzhu"/>
                <span slot="title">已发布的需求</span>
            </el-menu-item>
            </router-link>
        </el-menu>
        </el-aside>
        <el-main>
        <router-view/>
        </el-main>
    </el-container>
</div>
</template>
<script>
export default {

};
</script>
<style>
.post-page{
    width: 90%;
    min-height: 70%;
    background-color: white;
    position: absolute;
    top: 8%;
    margin: 5%;
    border-radius: 25px;
    box-shadow:1px 2px 3px 1px #ccc
}
</style>
