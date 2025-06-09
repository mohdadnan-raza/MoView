import React from 'react'
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { useState,useEffect } from 'react'
import { useNavigate } from "react-router-dom";
import { BsSearch } from "react-icons/bs";
import { ToastContainer, toast } from 'react-toastify';
import axios from 'axios';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from "react-router-dom";


export const Header = () => {
  const [search,setSearch]=useState([]);
  const [data,setData] = useState([])
  const navigate = useNavigate();
  useEffect( ()=>{
    getSomeData();
  },[])

  const results = ()=>{
    navigate(`/search/${search}`)
  }
  const getSomeData= async ()=>{
    const results = await axios.get("http://localhost:8201/movie/list");
    setData(results.data)
  }
  const out = ()=>{
    toast.dark("You Have Been Logged Out Successfully....", {
      position: toast.POSITION.TOP_CENTER,
    className:'toast-message'});
    localStorage.clear();
    navigate('/home')
  }
  const auth = localStorage.getItem('user')?localStorage.getItem('user'):null
  console.log(auth)

  return (
      <>
      <Navbar expand="lg" className='header fixed-top'>
      <Container fluid>
        <div className='logo'>Mo<span>View</span></div>
        <div>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <div
            className="nav"
            navbarScroll
          >
            <Link as={Link} to ="/home" className='lin'>Home</Link>
            <Link as={Link} to ="/categories" className='lin'>Categories</Link>
            
            {
            (auth)?
            <Link as={Link} to ="/admin" className='lin'>Admin Dashboard</Link>
            :
            null
}

            {
            localStorage.getItem('user')?
            <>
            <Link as={Link} to='/home' className='lin' onClick={out}>Logout</Link>
            </>
            :
            <>
            <Link as={Link} to ="/login" className='lin' >Login</Link>
            </>
            }
          </div>
          <div className='search-box'>
          <Form onSubmit={results}>
            <input
              type="search"
              placeholder="Search Movie"
              onChange={(e) =>setSearch(e.target.value)}
              className='input'
            />
            <Link className='search-btn'><BsSearch className='ic'/></Link>
          </Form>
          </div>
        </Navbar.Collapse>
        </div>
      </Container>
    </Navbar>
    <ToastContainer/>
    {/* <div className='dropdown'>
            {
              data.filter((item)=>
                (item.movieName.toLowerCase().includes(search) || item.movieLang.toLowerCase().includes(search) ||
                item.movieDirector.toLowerCase().includes(search) || item.movieCast.toLowerCase().includes(search)
                )
              ).map((mov)=>(
                <div className="dropdown-row">
                  {mov.movieName}
                </div>
              ))
            }
    </div> */}
    </>

  )
}
