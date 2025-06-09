import React from 'react'
import { useEffect,useState } from 'react';
import {  useParams } from 'react-router-dom'
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import Table from 'react-bootstrap/Table';

export const MoviePage = () => {
  const {id} = useParams();
    useEffect(()=>{
        viewMovie();
      },[])
      console.log(id)
      const[movie,setMovie]=useState([]);
    const viewMovie = async ()=>{
        try{
          const res = await axios.get(`http://localhost:8201/movie/id/${id}`);
          console.log(res)
          setMovie(res.data);
        }catch(err){
          toast(err.response.data, {
            position: toast.POSITION.TOP_CENTER});  
        }
      }
      console.log(movie)
  return (
    <div className='reviews-page'>
      <div className='reviews-left'>
    <div className="custom-card rc">
      <img src={movie.movieImage} className="movie-box-img" alt="..."/>
      </div>
      <div className="reviews-input">
        <h3 className='movie-page-heading'> Description </h3>
        <div className='cs-inp movie-des-box'>
          {movie.movieDescription}

        </div>

      </div>
    </div>
    <div className="reviews-right">
      <h1 className='categories-heading'>{movie.movieName}</h1>
      <div className='movie-page-text'>
      <Table className='movie-table-body'>

      <tbody >
      <tr>
        <td className='table-column-one'>Cast</td>
        <td className='table-column-two'>:</td>
        <td className='table-column-three'>{movie.movieCast}</td>
      </tr>
      <tr>
        <td className='table-column-one'>Director</td>
        <td className='table-column-two'>:</td>
        <td className='table-column-three'>{movie.movieDirector}</td>
      </tr>
      <tr>
        <td className='table-column-one'>Genres</td>
        <td className='table-column-two'>:</td>
        <td className='table-column-three'>{movie.movieGenres}</td>
      </tr>
      <tr>
        <td className='table-column-one'>Release Year</td>
        <td className='table-column-two'>:</td>
        <td className='table-column-three'>{movie.movieReleaseYear}</td>
      </tr>
      <tr>
        <td className='table-column-one'>Runtime</td>
        <td className='table-column-two'>:</td>
        <td className='table-column-three'>{movie.movieRunTime}</td>
      </tr>
      <tr>
        <td className='table-column-one'>Language</td>
        <td className='table-column-two'>:</td>
        <td className='table-column-three'>{movie.movieLang}</td>
      </tr>

      </tbody>
    </Table>


      </div>
      

       
        </div>
      
    
    </div>
  )
}
