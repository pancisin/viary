const SWPrecacheWebpackPlugin = require('sw-precache-webpack-plugin');
const WebpackPwaManifest = require('webpack-pwa-manifest');
const path = require('path');

module.exports = {
  // outputDir: path.resolve(__dirname, 'src/main/resources/static'),
  outputDir: 'dist',
  configureWebpack: {
    entry: [
      'babel-polyfill',
      path.resolve(__dirname, 'src/main/vuejs/main.js')
    ],
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src/main/vuejs'),
        components: path.resolve(__dirname, 'src/main/vuejs/components'),
        assets: path.resolve(__dirname, 'src/main/vuejs/assets')
      }
    },
    plugins: [
      new SWPrecacheWebpackPlugin({
        cacheId: 'viary-webapp',
        filename: 'viary-webapp-sw.js',
        staticFileGlobs: ['dist/**/*.{js,html,css}'],
        minify: true,
        stripPrefix: 'dist/'
      }),
      new WebpackPwaManifest({
        name: 'Viary',
        short_name: 'Viary',
        description: 'Description!',
        background_color: '#FFF',
        theme_color: '#132c39',
        'theme-color': '#132c39',
        start_url: '/',
        icons: [
          {
            src: path.resolve(
              __dirname,
              'src/main/vuejs/assets/img/icons/icon.png'
            ),
            sizes: [96, 128, 192, 256, 384, 512],
            destination: 'img'
          }
        ]
      })
    ],
    devtool: 'source-map'
  },
  chainWebpack: (config) => {
    config.plugin('html').tap((args) => {
      if (args[0] != null) {
        args[0].template = path.resolve(__dirname, 'index.html');
      }
      return args;
    });
  },
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
      },
      '/gs-guide-websocket': {
        target: 'http://localhost:8180',
        secure: false
      }
    }
  }
};
