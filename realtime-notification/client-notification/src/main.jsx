// index.js or App.js

// Polyfill for global variable in the browser environment
if (typeof global === 'undefined') {
  window.global = window;
}

import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
