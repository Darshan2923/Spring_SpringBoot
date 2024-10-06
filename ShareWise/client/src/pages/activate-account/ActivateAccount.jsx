import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { confirmAccount } from '../../services/api'; // Import your API method

const OTPInput = ({ length, onComplete }) => {
    const [otp, setOtp] = useState(Array(length).fill(''));
    const inputsRef = useRef([]);

    const handleChange = (element, index) => {
        const value = element.value;
        if (/^\d$/.test(value)) {  // Only allow numeric input
            const newOtp = [...otp];
            newOtp[index] = value;
            setOtp(newOtp);

            // Move to the next input field if available
            if (index < length - 1 && value) {
                inputsRef.current[index + 1].focus();
            }

            // Call onComplete if all fields are filled
            if (newOtp.every((digit) => digit !== '')) {
                onComplete(newOtp.join(''));
            }
        } else {
            element.value = ''; // Clear if non-numeric input
        }
    };

    const handleKeyDown = (e, index) => {
        if (e.key === 'Backspace' && otp[index] === '') {
            if (index > 0) {
                inputsRef.current[index - 1].focus();
            }
        }
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            {otp.map((digit, index) => (
                <input
                    key={index}
                    ref={(el) => (inputsRef.current[index] = el)}
                    type="text"
                    maxLength="1"
                    value={digit}
                    onChange={(e) => handleChange(e.target, index)}
                    onKeyDown={(e) => handleKeyDown(e, index)}
                    style={{
                        width: '40px',
                        height: '40px',
                        fontSize: '24px',
                        textAlign: 'center',
                        margin: '0 8px',
                    }}
                />
            ))}
        </div>
    );
};

const ActivateAccount = () => {
    const [message, setMessage] = useState('');
    const [isOkay, setIsOkay] = useState(true);
    const [submitted, setSubmitted] = useState(false);
    const navigate = useNavigate();

    const handleCodeCompleted = async (token) => {
        try {
            await confirmAccount({ token });
            setMessage('Your account has been successfully activated. Now you can proceed to login.');
            setSubmitted(true);
            setIsOkay(true);
        } catch (error) {
            setMessage('Token has expired or is invalid.');
            setSubmitted(true);
            setIsOkay(false);
        }
    };

    const redirectToLogin = () => {
        navigate('/login');
    };

    return (
        <div className="container">
            {submitted ? (
                <div>
                    {isOkay ? (
                        <div className="activation-message">
                            <h2>Activation Successful!</h2>
                            <p>{message}</p>
                            <button className="btn btn-primary" onClick={redirectToLogin}>
                                Go to Login
                            </button>
                        </div>
                    ) : (
                        <div className="activation-error">
                            <h2>Activation Failed!</h2>
                            <p>{message}</p>
                            <button className="btn btn-primary" onClick={() => setSubmitted(false)}>
                                Try again
                            </button>
                        </div>
                    )}
                </div>
            ) : (
                <div className="text-center" style={{ width: '400px' }}>
                    <h2>Enter your activation code</h2>
                    <OTPInput length={6} onComplete={handleCodeCompleted} />
                    <button className="btn btn-link mt-3" onClick={redirectToLogin}>
                        Go to Login
                    </button>
                </div>
            )}
        </div>
    );
};

export default ActivateAccount;
