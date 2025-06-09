import React, { useState } from 'react'
import { useNavigate } from "react-router-dom";
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import 'react-toastify/dist/ReactToastify.css';
import InputGroup from 'react-bootstrap/InputGroup';
import { BsPersonSquare, BsFillKeyFill, BsDash, BsGoogle, BsTwitter, BsFacebook } from "react-icons/bs";
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import jwt_decode from "jwt-decode";



export const Login = () => {

  const navigate = useNavigate();

  const [usernameOrEmail, setUserNameOrEmail] = useState("");
  const [password, setPassword] = useState("");

  let submitForm = async (e) => {
    try {
      e.preventDefault();
      const r = await axios.post('http://localhost:8202/api/auth/login', { usernameOrEmail:usernameOrEmail,password:password });
      console.log(r.data.jwtToken)
      var token = r.data.jwtToken;
      var decoded = jwt_decode(token);
      console.log(decoded);
      localStorage.setItem('user',JSON.stringify(
        r.data
      ))
      navigate('/home')
      toast.success(`Welcome Back ! ${decoded.sub}`, {
        position: toast.POSITION.TOP_CENTER});
    } catch (error) {
      toast.error(`Invalid Credntials`, {
        position: toast.POSITION.TOP_CENTER});
      console.log(error)
    }

  }
  return (
    <>
      <div className="login-cont">
      <marquee behavior="" direction="up" className='moving-login-left'>
        <img className='login-img-moving' src="/images/logo4.png" alt="" />
        </marquee>
        <marquee behavior="" direction="down" className='moving-login-right'>
        <img className='login-img-moving' src="/images/glasses.png" alt="" />
        </marquee>
      <Form onSubmit={submitForm}>
          <div className="login-component">
            
            <img src="/images/1lgn.jpg" alt="" className='login-img' />
            <div className="login-main">
              <img src="/images/avatar.svg" alt="" className='login-avtar' />
              <h1 className='login-title'>Login</h1>
              <input type="text" className='login-input' placeholder='Enter Your Email' onChange={(e) => setUserNameOrEmail(e.target.value)} value={usernameOrEmail} />
              <input type="password" className='login-input' placeholder='Enter Your Password' onChange={(e) => setPassword(e.target.value)} value={password} />
              <p className='for-pas'>Forgot Password?</p>
              <button className='log-btn hv' type='submit' onClick={submitForm}>Login</button>
              <p className='for-pas'>Not a Member? <Link to='/signup' className='login-sup'>SignUp!</Link></p>
              <div className='login-logo-cont'>
                <div className='login-logo'>
                  <BsGoogle />
                </div>
                <div className='login-logo'>
                  <BsTwitter />
                </div>
                <div className='login-logo'>
                  <BsFacebook />
                </div>
              </div>
            </div>
          </div>
          </Form>
      </div>
      
      <ToastContainer/>


    </>

  )
}
