// 缓存刷新配置
fis.match('*.{js,css,png}', {
    useHash: true
});


// css 图片合并
fis.match('*.css', {
    useSprite: true
});

// 资源压缩
fis.match('*.js', {
    optimizer: fis.plugin('uglify-js')
});

fis.match('*.css', {
    optimizer: fis.plugin('clean-css')
});

fis.match('*.png', {
   optimizer: fis.plugin('png-compressor')
});

// less 预处理
fis.match('app/styles/less/*.less', {
    parser: fis.plugin('less'),
    rExt: '.css'
});

//  to do package
/*fis.match('::package', {
 spriter: fis.plugin('csssprites')
 });*/