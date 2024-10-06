import axios from 'axios';
// Importing jwtDecode only if needed elsewhere in this file
// import jwtDecode from 'jwt-decode';

const API = axios.create({ baseURL: `http://localhost:8088/api/v1` });

// Authentication
export const authenticate = async ({ email, password }) => {
    return await API.post('/auth/authenticate', { email, password });
};

export const register = async ({ email, firstname, lastname, password }) => {
    return await API.post('/auth/register', { email, firstname, lastname, password });
};
