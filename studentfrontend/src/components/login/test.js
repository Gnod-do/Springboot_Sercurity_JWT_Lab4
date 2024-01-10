import React, { useState } from 'react';

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('your_API_URL/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        // Xử lý khi đăng nhập thành công
        console.log('Đăng nhập thành công!');
      } else {
        // Xử lý khi đăng nhập không thành công
        console.error('Đăng nhập thất bại');
      }
    } catch (error) {
      console.error('Lỗi: ', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>
          Tên đăng nhập:
          <input type="text" value={username} onChange={handleUsernameChange} />
        </label>
      </div>
      <div>
        <label>
          Mật khẩu:
          <input type="password" value={password} onChange={handlePasswordChange} />
        </label>
      </div>
      <button type="submit">Đăng nhập</button>
    </form>
  );
};

export default LoginForm;
