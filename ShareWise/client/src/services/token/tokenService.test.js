test


import tokenService from './tokenService';
import * as jwtDecode from 'jwt-decode';

// Mocking the jwtDecode function from the 'jwt-decode' library
jest.mock('jwt-decode');

describe('tokenService', () => {
    beforeEach(() => {
        localStorage.clear();
    });

    it('should store and retrieve token from localStorage', () => {
        const token = 'test-token';
        tokenService.setToken(token);
        expect(localStorage.getItem('token')).toBe(token);
        expect(tokenService.getToken()).toBe(token);
    });

    it('should validate token when not expired', () => {
        const token = 'valid-token';
        const decodedToken = { exp: Math.floor(Date.now() / 1000) + 60 }; // Expires in 60 seconds
        jwtDecode.mockReturnValue(decodedToken);

        tokenService.setToken(token);
        expect(tokenService.isTokenValid()).toBe(true);
    });

    it('should invalidate token when expired', () => {
        const token = 'expired-token';
        const decodedToken = { exp: Math.floor(Date.now() / 1000) - 60 }; // Expired 60 seconds ago
        jwtDecode.mockReturnValue(decodedToken);

        tokenService.setToken(token);
        expect(tokenService.isTokenValid()).toBe(false);
    });

    it('should return user roles from the decoded token', () => {
        const token = 'role-token';
        const decodedToken = { authorities: ['ROLE_ADMIN', 'ROLE_USER'] };
        jwtDecode.mockReturnValue(decodedToken);

        tokenService.setToken(token);
        expect(tokenService.getUserRoles()).toEqual(['ROLE_ADMIN', 'ROLE_USER']);
    });

    it('should return empty array if no roles found in token', () => {
        const token = 'no-role-token';
        const decodedToken = {};
        jwtDecode.mockReturnValue(decodedToken);

        tokenService.setToken(token);
        expect(tokenService.getUserRoles()).toEqual([]);
    });
});
