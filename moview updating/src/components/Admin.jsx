import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import { useNavigate } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import {loginDone} from './Login';
export const Admin = () => {
    const navigate = useNavigate();
  return (

      <div className="admin">
        <div className='custom-card admin-card'>
          <img src="/images/amig.svg" alt="" className='admin-img'/>
          <div className='admin-text'>
          <h4 className='admin-title'>View Reviews List</h4>
          <p><span>Hey!</span> Admin You Have <span>Access</span> All The Movie <span>Reviws</span>from Here.</p>
          </div>
          <div>
            <button className='sub-btn main-btn' onClick={()=>{navigate("/list/reviews")}}>View Reviws</button>
          </div>
        </div>

        <div className='custom-card admin-card'>
          <img src="/images/ad7.svg" alt="" className='admin-img'/>
          <div className='admin-text'>
          <h4 className='admin-title'>View Movie List</h4>
          <p>Hey! <span>Admin</span> You Have Access All The Movie Reviws from <span>Here</span>.</p>
          </div>
          <div>
            <button className='sub-btn main-btn' onClick={()=>{navigate("/movie/list")}}>View Movies</button>
          </div>
        </div>
        
        <div className='custom-card admin-card'>
          <img src="/images/ads2.svg" alt="" className='admin-img'/>
          <div className='admin-text'>
          <h4 className='admin-title'>Add Movie</h4>
          <p>Hey! Admin You <span>Have</span> Access <span>All</span> The <span>Movie</span> Reviws from Here.</p>
          </div>
          <div>
            <button className='sub-btn main-btn' onClick={()=>{navigate("/add/movie")}}>Add Movie</button>
          </div>
        </div>
        <div className='custom-card admin-card'>
          <img src="/images/ads1.svg" alt="" className='admin-img'/>
          <div className='admin-text'>
          <h4 className='admin-title'>View Users List</h4>
          <p>Hey! Admin <span>You</span> Have Access All <span>The</span> Movie Reviws from <span>Here.</span></p>
          </div>
          <div>
            <button className='sub-btn main-btn' onClick={()=>{navigate("/users/list")}}>View Users</button>
          </div>
        </div>
    </div>
  )
}


