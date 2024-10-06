import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { BrowserRouter } from 'react-router-dom'
import setupAxiosInterceptors from './services/interceptor/http-token-interceptor.js'


// Initializa Axios interceptors before rendering the app
setupAxiosInterceptors();
createRoot(document.getElementById('root')).render(

  <BrowserRouter>
    <App />
  </BrowserRouter>

)
