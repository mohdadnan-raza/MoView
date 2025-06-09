import React from 'react'
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Button';
import ReactStars from 'react-rating-stars-component'
import { useNavigate,Link } from "react-router-dom";
import { BsPlay,BsDot,BsStar} from "react-icons/bs";
import axios from 'axios';
import { useState,useEffect } from 'react'
import { ToastContainer, toast } from 'react-toastify';
import { useRef } from 'react';
export const MovieItem = ({movieName,id,movieCast,movieGenres,movieReleaseYear,movieImage,rating}) => {

    const navigate=useNavigate();
    const reviwes = ()=>{
      
        navigate(`/reviews/${id}`,{
          state:{
            movieName:movieName,
            movieCast:movieCast,
            movieGenres:movieGenres,
            movieReleaseYear:movieReleaseYear,
            movieImage:movieImage
        }
        
          })}
          console.log(rating)
  return (

<div className="custom-card">
      <img src={movieImage} className="movie-box-img" alt="..."/>
      <div className='card-text'>
        <h2 className='movie-title'>{movieName}</h2>
        <div>
        <span className='movie-type'>{movieCast?movieCast.split(",")[0]:null}</span>
        <BsDot/>
        <span className='movie-type'>{movieGenres?movieGenres.split(",")[0]:null}</span>
        <BsDot/>
        <span className='movie-type'>{movieReleaseYear}</span>
        </div>
        <div className='buttons'>
        <button className='sub-btn' onClick={reviwes}>Reviews</button>
        <Link className='watch-btn card-play'>
          <Link className='watch-btn' to={`/view/movie/${id}`}>Info</Link>
          <p></p> 
        <ReactStars activeColor="#ffb43a" isHalf={true} size={10}count={5} edit={false} value={rating?rating:null}/>
        <Link as={Link} to='/free'><BsPlay className='ic'/></Link>
        </Link>
        </div>
      </div>
    </div>
  )

}