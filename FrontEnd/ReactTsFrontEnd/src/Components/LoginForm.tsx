import React, { useState } from 'react';
import axios from 'axios';
import '../StyleSheets/LoginForm.css';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await axios.post('/auth/login', { username, password });
            // Store JWT token in localStorage
            localStorage.setItem('token', response.data.token);
            // Redirect to dashboard
            window.location.href = '/dashboard';
        } catch (err) {
            setError('Invalid username or password');
        }
    };

    return (
        <div className='form'>
        <form onSubmit={handleSubmit}>
            <div className='form-container'>
                <span className='sign-in-text'>Sign In</span>
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className='field'
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className='field'
            />
            <button type="submit" className='login-button'>Login</button>
            <div className='error'>
            {error && <p className='error-text'>{error}</p>}
            </div>
            <div className='register'><a href="">Create an Account</a></div>
            <div className='forgot'><a href="">Forgot My Password</a></div>
            </div>

        </form>
        </div>
    );
};

export default LoginForm;