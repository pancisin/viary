import vue from 'rollup-plugin-vue2';
import babel from 'rollup-plugin-babel';
// import eslint from 'rollup-plugin-eslint';
import bundleSize from 'rollup-plugin-filesize';
import scss from 'rollup-plugin-scss';
import resolve from 'rollup-plugin-node-resolve';
import localResolve from 'rollup-plugin-local-resolve';
import alias from 'rollup-plugin-alias';
import pkg from './package.json';

const path = require('path');

const external = Object.keys(pkg.dependencies);
// const extensions = ['.js', '.vue'];
const isProduction = !process.env.ROLLUP_WATCH;
const globals = { vue: 'Vue' };

// const lintOpts = {
//   extensions,
//   exclude: ['**/*.json'],
//   cache: true,
//   throwOnError: true
// };

const plugins = [
  // resolve({
  //   browser: true
  // }),
  localResolve(),
  alias({
    '@': path.resolve(__dirname, 'src/main/vuejs'),
    'components': path.resolve(__dirname, 'src/main/vuejs/components'),
    'assets': path.resolve(__dirname, 'src/main/vuejs/assets'),
    resolve: ['.js', '/index.js'] 
  }),
  // eslint(lintOpts),
  bundleSize(),
  vue({
    template: {
      isProduction,
      compilerOptions: { preserveWhitespace: false }
    },
    css: true,
  }),
  scss(),
  babel({
    exclude: 'node_modules/**',
    // babelrc: false,
    // presets: [['env', { modules: false }]],
    // plugins: ['external-helpers'],
    // externalHelpers: true
  })
];

export default {
  external,
  plugins,
  input: path.resolve(__dirname, 'src/main/vuejs/main.js'),
  output: {
    globals,
    file: 'dist/bundle.js',
    format: 'umd'
  },
};