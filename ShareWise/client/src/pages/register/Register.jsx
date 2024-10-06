import React, { useState } from 'react';
import './Register.css';  // Vanilla CSS for styling
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { register } from '../../services/api/index';

const Register = () => {
    const [authRequest, setAuthRequest] = useState({ email: '', firstname: '', lastname: '', password: '' });
    const [errorMsg, setErrorMsg] = useState([]);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setAuthRequest({
            ...authRequest,
            [name]: value,
        });
    };

    const handleRegister = async () => {
        setErrorMsg([]); // Reset error messages

        // Check for empty fields
        if (!authRequest.email) {
            setErrorMsg((prev) => [...prev, "Email shouldn't be empty"]);
            return;
        }

        if (!authRequest.firstname) {
            setErrorMsg((prev) => [...prev, "First name shouldn't be empty"]);
            return;
        }

        if (!authRequest.lastname) {
            setErrorMsg((prev) => [...prev, "Last name shouldn't be empty"]);
            return;
        }

        if (!authRequest.password) {
            setErrorMsg((prev) => [...prev, "Password shouldn't be empty"]);
            return;
        }

        try {
            await register(authRequest); // Call the register API
            navigate('/activate-account'); //Call registration Api
        } catch (err) {
            console.error(err);
            if (err.response && err.response.data) {
                // Display validation errors from backend
                setErrorMsg(err.response.data.validationErrors || [err.response.data.errorMsg]);
            } else {
                setErrorMsg(['Registration failed. Please try again.']);
            }
        }
    };

    const navigateToLogin = () => {
        navigate('/login');
    };

    return (
        <div className="register-container">
            <h3 className="text-center">Register</h3>
            <hr />
            {errorMsg.length > 0 && (
                <div className="alert alert-danger">
                    {errorMsg.map((msg, index) => (
                        <p key={index}>{msg}</p>
                    ))}
                </div>
            )}
            <div className="mb-3">
                <label htmlFor="firstname" className="form-label">First Name</label>
                <input
                    type="text"
                    className="form-control"
                    id="firstname"
                    name="firstname"
                    value={authRequest.firstname}
                    onChange={handleInputChange}
                    placeholder="First Name"
                />
            </div>
            <div className="mb-3">
                <label htmlFor="lastname" className="form-label">Last Name</label>
                <input
                    type="text"
                    className="form-control"
                    id="lastname"
                    name="lastname"
                    value={authRequest.lastname}
                    onChange={handleInputChange}
                    placeholder="Last Name"
                />
            </div>
            <div className="mb-3">
                <label htmlFor="email" className="form-label">Email address</label>
                <input
                    type="email"
                    className="form-control"
                    id="email"
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
                <button onClick={handleRegister} type="button" className="btn btn-primary">
                    <FontAwesomeIcon icon={faUserPlus} /> Register
                </button>
                <div>
                    Already have an account?&nbsp;
                    <button onClick={navigateToLogin} type="button" className="btn btn-link">
                        Login
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Register;
