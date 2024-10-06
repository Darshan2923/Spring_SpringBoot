// src/services/token/tokenService.js
import * as jwtDecode from 'jwt-decode';

const tokenService = {
    setToken: (token) => {
        localStorage.setItem('token', token);
    },

    getToken: () => {
        return localStorage.getItem('token');
    },

    isTokenValid: () => {
        const token = tokenService.getToken();
        if (!token) {
            return false;
        }

        try {
            const decodedToken = jwtDecode(token);
            const expiryDate = decodedToken.exp * 1000; // Convert to milliseconds
            if (Date.now() >= expiryDate) {
                localStorage.clear();
                return false;
            }
            return true;
        } catch (error) {
            localStorage.clear();
            return false;
        }
    },

    isTokenNotValid: () => {
        return !tokenService.isTokenValid();
    },

    getUserRoles: () => {
        const token = tokenService.getToken();
        if (token) {
            try {
                const decodedToken = jwtDecode(token);
                return decodedToken.authorities || [];
            } catch (error) {
                console.error('Error decoding token:', error);
                return [];
            }
        }
        return [];
    }
};

export default tokenService;
