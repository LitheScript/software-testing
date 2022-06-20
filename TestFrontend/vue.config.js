module.exports={
    publicPath:'./',
    lintOnSave:false,   //配置关闭eslint
    chainWebpack: config => {
        config.module
            .rule("html")
            .test(/\.html$/)
            .use("html-loader")
            .loader("html-loader");
    }
}
