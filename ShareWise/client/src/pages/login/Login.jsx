import React, { useState } from 'react';
import './Login.css';  // Vanilla CSS for styling
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSignInAlt } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { authenticate } from '../../services/api';
import tokenService from '../../services/token/tokenService';

const Login = () => {
    const [authRequest, setAuthRequest] = useState({ email: '', password: '' });
    const [errorMsg, setErrorMsg] = useState([]);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setAuthRequest({
            ...authRequest,
            [name]: value,
        });
    };

    const login = async () => {
        setErrorMsg([]);

        try {
            const res = await authenticate(authRequest); // Assuming this returns an object with a token
            tokenService.setToken(res.data.token); // Save the token using your tokenService
            navigate('/notes'); // Navigate to the notes page
        } catch (err) {
            console.error(err);
            if (err.response && err.response.data) {
                setErrorMsg(err.response.data.validationErrors || [err.response.data.errorMsg]);
            } else {
                setErrorMsg(['An unknown error occurred']);
            }
        }
    };

    const register = () => {
        navigate('/register');
    };

    return (
        <div className="login-container">
            <h3 className="text-center">Login</h3>
            <hr />
            {errorMsg.length > 0 && (
                <div className="alert alert-danger">
                    {errorMsg.map((msg, index) => (
                        <p key={index}>{msg}</p>
                    ))}
                </div>
            )}
            <div className="mb-3">
                <label htmlFor="login" className="form-label">Email address</label>
                <input
                    type="email"
                    className="form-control"
                    id="login"
                    name="email"
                    value={authRequest.email}
                    onChange={handleInputChange}
                    placeholder="name@example.com"
                />
            </div>
            <div className="mb-3">
                <label htmlFor="password" className="form-label">Password</label>
                <input
                    type="password"
                    className="form-control"
                    id="password"
                    name="password"
                    value={authRequest.password}
                    onChange={handleInputChange}
                    placeholder="Password"
                />
            </div>
            <div className="d-flex justify-content-between mb-3">
                <button onClick={login} type="button" className="btn btn-primary">
                    <FontAwesomeIcon icon={faSignInAlt} /> Sign in
                </button>
                <div>
                    Don't have an account?&nbsp;
                    <button onClick={register} type="button" className="btn btn-link">
                        Register
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Login;
