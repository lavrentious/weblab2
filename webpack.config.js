const path = require('path');

module.exports = {
  entry: './app/src/main/webapp/ts/main.ts',
  module: {
    rules: [
      {
        test: /\.ts$/,
        use: 'ts-loader',
        exclude: /node_modules/
      }
    ]
  },
  resolve: {
    extensions: ['.ts', '.js'],
  },
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'app/src/main/webapp/js'),
  },
  mode: 'development'
};
