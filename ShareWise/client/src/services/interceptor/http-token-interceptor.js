// src/services/interceptors/http-token-interceptor.js
import axios from 'axios';
import tokenService from '../token/tokenService';

// Create an Axios interceptor
const setupAxiosInterceptors = () => {
    axios.interceptors.request.use(
        (config) => {
            const token = tokenService.getToken();
            if (token) {
                // Inject the Authorization header with the Bearer token
                config.headers['Authorization'] = `Bearer ${token}`;
            }
            return config;
        },
        (error) => {
            // Handle request error
            return Promise.reject(error);
        }
    );
};

export default setupAxiosInterceptors;
