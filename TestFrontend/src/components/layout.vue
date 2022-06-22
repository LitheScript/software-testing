<template>
    <el-container style="border: 1px solid #eee;height:100vh">
        <el-header style="height:12%">
            <span><img src="../assets/_logo.svg" alt="" style="width:200px;position:absolute;left:2%"></span>
        </el-header>
        <el-container style="height:88%">
            <el-aside width="200px">
                <el-menu :default-openeds="['1','2']"
                         text-color="black"
                         active-text-color="#12a182;"
                         router=true>
                    <el-submenu index="1">
                        <template #title><i class="el-icon-s-order"></i><span style="color:#c27c88">平时题目</span></template>

                        <el-menu-item index="/triangle">三角形问题</el-menu-item>
                        <el-menu-item index="/telecom">电信收费问题</el-menu-item>
                        <el-menu-item index="/computer">电脑销售系统</el-menu-item>
                        <el-menu-item index="/calendar">万年历问题</el-menu-item>

                    </el-submenu>
                    <el-submenu index="2">
                        <template #title><i class="el-icon-menu"></i><span style="color:#c27c88">期末项目</span></template>
                        <el-menu-item index="/unitTest">单元测试结果</el-menu-item>
<!--                      暂不演示集成测试、系统测试-->
<!--                      <el-menu-item index="/integrationTest">集成测试结果</el-menu-item>-->
<!--                      <el-menu-item index="/systemTest">系统测试结果</el-menu-item>-->
                    </el-submenu>
                </el-menu>
            </el-aside>

            <el-main>
                <i class="el-icon-s-unfold" style="float: left;margin-right: 8px"></i>
                <div class="header-breadcrumb">
                    <el-breadcrumb separator=">">
                        <el-breadcrumb-item v-for="(matched,m) in this.$route.matched" :key="m" :to="matched">
                            <!--假如我们目前的路由是/a/aa-01，那么此时 this.$route.matched匹配到的会是一个数组，包含 '/'，'/a'，'/a/aa-01'，这三个path的路由信息 -->
                            {{matched.name}}
                        </el-breadcrumb-item>
                    </el-breadcrumb>
                </div>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<style>
    .el-header {
        background-color: #12a182;
        color: rgb(244, 252, 245);
        line-height: 60px;
        text-align: right;
        font-size: 12px;
    }

    .header-breadcrumb {
        margin-bottom: 8px;
        padding-bottom: 8px;
        border-bottom: dimgray solid 1px;
        color: white;
    }

    .el-aside {
        border: 0;
        background-color: #12a182;
        color: white;
    }

    .el-aside .el-menu {
        background-color:#12a182;
        color: white;
    }

    .el-aside .el-menu .el-submenu {
        background-color:#e2e1e4;
        color: white;
    }

    .el-aside .el-menu .el-submenu #title {
        background-color: #e2e1e4;
        color: #c27c88;
    }

    .el-aside .el-menu .el-submenu .el-menu-item {
        background-color: #e2e1e4;
        color: white;

    }

    .el-aside .el-menu .el-submenu .el-menu-item:hover {
        background-color: whitesmoke;
    }

    .el-aside .el-menu .el-submenu .el-menu-item:active {
      background-color: rgb(44, 43, 43);
    }
</style>

<script>
    import {
        getCurrentInstance,
        onMounted
    } from 'vue';

    export default {
        name: 'layout',
        setup(){
            const {proxy} = getCurrentInstance()
            onMounted(()=>{
            console.log(proxy.$root.$route.matched)
            })
        },
        data() {
            const item = {
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            };
            return {
                tableData: Array(20).fill(item)
            }
        }
    };
</script>
