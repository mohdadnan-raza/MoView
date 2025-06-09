import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios';
import { useNavigate,Link } from "react-router-dom";
import { BsPlay,BsDot,BsStar} from "react-icons/bs";
import { ToastContainer, toast } from 'react-toastify';
import { MovieItem } from './MovieItem';
import { useRef } from 'react';


export const Categories = () => {

  const navigate = useNavigate(); 

  const [movies,setMovies]=useState([]);
  const [action,setAction]=useState([])
  const [trending,setTrending]=useState([])
  const [best,setBest]=useState([])
  const [adventure,setAdventure]=useState([])
  const [drama,setDrama]=useState([])
  const avgrt = useRef()
  useEffect(()=>{
    getActionMovie();
  },[])
  const warn = ()=>{
    navigate('/login')
    toast.warning('You Have To Login First..', {
            position: toast.POSITION.TOP_CENTER})
  } 
  const reviwes = (id)=>{
    localStorage.getItem('user')?
      navigate(`/reviews/${id}`):warn()
    }

  const getActionMovie = async ()=>{
    try{
      let gres = await axios.get("http://localhost:8203/average-rating");
      avgrt.current = gres.data
    }catch(err){
      alert(err.response)
    }
    try{
      const res = await axios.get('http://localhost:8201/movie/list');
      setMovies(res.data)
    }catch(err){
      alert(err.response.message)
    }
    
 
  }
  let actionMovies = movies.filter((mov)=>{
    if(mov.movieGenres.toLowerCase().includes('action')){
      return mov;
    }
  })
  let adventureMovies = movies.filter((mov)=>{
    if(mov.movieGenres.toLowerCase().includes('adventure')){
      return mov;
    }
  })
  let dramaMovies = movies.filter((mov)=>{
    if(mov.movieGenres.toLowerCase().includes('drama')){
      return mov;
    }
  })
  return (

    
    <>
    <div className="movie-categories">
    <div className='categories-heading'>
    <h3 className='text-center'><span>Act</span>ion M<span>ovi</span>es</h3>
    <div className='adventure'>
    {
      actionMovies.map((movie)=>{
        return(
          <>
              <MovieItem key={movie.id} {...movie} {...{rating:avgrt.current[movie.id]}}/>
              </>
        )
      })
    }

    </div>
    </div>
    <div className='categories-heading'>
      <h3 className='text-center'>Tre<span>nd</span>ing<span>  Mo</span>vies</h3>
    <div className='adventure'>
    {
      adventureMovies.map((movie)=>{
        return(
          <>
              <MovieItem key={movie.id} {...movie} {...avgrt.current}/>
              </>
        )
      })
    }

    </div>
    </div>
    <div className='categories-heading'>
      <h3 className='text-center'>B<span>es</span>t<span>  Mo</span>vies</h3>
    <div className='adventure'>
    {
      dramaMovies.map((movie)=>{
        return(
      
          <>
          <MovieItem key={movie.id} {...movie} {...avgrt.current}/>
          </>
          
        )
      })
    }

    </div>
    </div>
    <div className='categories-heading'>
      <h3 className='text-center'>Ad<span>ven</span>ture<span>  Mov</span>ies</h3>
    <div className='adventure'>
    {
      adventure.map((movie)=>{
        return(
          <>
              <MovieItem key={movie.id} {...movie} {...avgrt.current}/>
              </>
        )
      })
    }

    </div>
    </div>

    <div className='categories-heading'>
      <h3 className='text-center'> <span>Dra</span>ma Movi<span>es</span></h3>
    <div className='adventure'>
    {
      drama.map((movie)=>{
        return(
          <>
              <MovieItem key={movie.id} {...movie} {...avgrt.current}/>
              </>
        )
      })
    }

    </div>
    </div>
    </div>
    
    </>
  )
}
