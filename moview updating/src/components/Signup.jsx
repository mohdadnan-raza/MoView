import React ,{useState} from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { BsKeyboardFill,BsKeyboard,BsMailbox,BsPersonFill,BsCalendarDate,BsFillPersonBadgeFill,BsPersonSquare, BsFillKeyFill, BsDash, BsGoogle, BsTwitter, BsFacebook } from "react-icons/bs";
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";




export const SignUp = () => {
  const navigate = useNavigate();

    const [username,setUserName]=useState("");
    const [name,setName]=useState("");
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const [res,setRes]=useState('')
    // const [confirmpassword,setConfirmPassword]=useState("");

    let submitForm = async (e)=>{
        e.preventDefault()
        if(password.length<8){
          toast.warning('Password should not be less than 8 characters',{
            position: toast.POSITION.TOP_CENTER});
          }
          else if(username.length>8){
            toast.warning('Username should be of less than 8 characters',{
              position: toast.POSITION.TOP_CENTER});
          }
          else{
            try{
            const r = await axios.post('http://localhost:8202/api/auth/register',{username:username,name:name,email:email,password:password});
            console.log(r.data)
            setRes(r.data)
            toast.success(r.data+`  PLease Login To Continue.`,{
              position: toast.POSITION.TOP_CENTER}); 
            navigate('/home')
            }
            catch(error){
              toast.warning(error.response.data,{
              position: toast.POSITION.TOP_CENTER}); 
              console.log(error.response)
            }
          }
          }
            

  return (
    <>
    
    <div className="login-cont">
    <marquee behavior="" direction="up" className='moving-login-left'>
        <img className='login-img-moving' src="/images/logo2.png" alt="" />
        </marquee>
        <marquee behavior="" direction="down" className='moving-login-right'>
        <img className='login-img-moving' src="/images/box-open.png" alt="" />
        </marquee>
      <Form onSubmit={submitForm}>
          <div className="login-component">
            
            <img src="/images/3lgn.jpg" alt="" className='login-img' />
            <div className="login-main">
              <img src="/images/avatar.svg" alt="" className='login-avtar' />
              <h1 className='login-title'>Join Us!</h1>
              <input type="text" className='login-input' placeholder='Enter Your Username' onChange={(e) => setUserName(e.target.value)} value={username} />
              <input type="text" className='login-input' placeholder='Enter Your Name' onChange={(e) => setName(e.target.value)} value={name} />
              <input type="email" className='login-input' placeholder='Enter Your Email' onChange={(e) => setEmail(e.target.value)} value={email} />
              <input type="password" className='login-input' placeholder='Enter Your Password' onChange={(e) => setPassword(e.target.value)} value={password} />
              <button className='log-btn hv' type='submit'>Sign Up</button>
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
          <ToastContainer/>
      </div>
      

    </>
  )
}
