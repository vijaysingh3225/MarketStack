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
            {error && <p>{error}</p>}
            </div>
        </form>
        </div>
    );
};

export default LoginForm;