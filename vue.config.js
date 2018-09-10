const path = require('path');
module.exports = {
  // outputDir: path.resolve(__dirname, 'src/main/resources/static'),
  outputDir: 'src/main/resources/static',
  configureWebpack: {
    entry: [
      'babel-polyfill', 
      path.resolve(__dirname, 'src/main/vuejs/main.js')
    ],
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src/main/vuejs'),
        'components': path.resolve(__dirname, 'src/main/vuejs/components'),
        'assets': path.resolve(__dirname, 'src/main/vuejs/assets')
      }
    },
    devtool: 'source-map'
  },
  // chainWebpack: config => {
  //   config
  //     .plugin('html')
  //     .tap(args => {
  //       if (args[0] != null) {
  //         args[0].template = path.resolve(__dirname, 'index.html');
  //       }
  //       return args;
  //     });
  // },
  devServer: {
    open: process.platform === 'darwin',
    host: '0.0.0.0',
    port: 3000,
    https: false,
    hotOnly: false,
    proxy: {
      '/api': {
        target: 'http://localhost:8180',
        secure: false
      },
      '/oauth': {
        target: 'http://localhost:8180',
        secure: false
      }
    }
  }
};
