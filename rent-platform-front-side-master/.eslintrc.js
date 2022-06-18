module.exports = {
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  extends: [
    'plugin:vue/essential',
    'airbnb-base',
  ],
  parser: 'vue-eslint-parser',
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: [
    'vue',
  ],
  rules: {
    'vue/require-prop-type-constructor': 1,
    'vue/multi-word-component-names': 1,
    eqeqeq: 1,
    'no-param-reassign': 1,
    'linebreak-style': [0, 'error', 'window'],
  },
};
