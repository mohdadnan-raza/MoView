import React from 'react'
import { Crousel } from './Crousel'
import Button from 'react-bootstrap/Button';
import { useNavigate,Link,useLocation} from "react-router-dom";
import Card from 'react-bootstrap/Card';
import { useState,useEffect } from 'react'
import axios from 'axios';
import { MovieItem } from './MovieItem';
import { ToastContainer, toast } from 'react-toastify';
import { BsPlay,BsChevronRight,BsChevronLeft} from "react-icons/bs";
 import {Swiper,SwiperSlide} from 'swiper/react';
 import 'swiper/css';
 import "swiper/css/pagination";
 import { Pagination,Autoplay,EffectCards,Keyboard } from "swiper";
import { faGrinBeamSweat } from '@fortawesome/free-solid-svg-icons';
import { useRef } from 'react';

 

export const Home = () => {
    const navigate = useNavigate();
    const {state} = useLocation();
      const [movies,setMovies]=useState([]);
      const [loading,setLoading]=useState(false);
      const [currentPage,setCurrentPage] = useState(1);
      const [firstPostIndex,setFirstPostIndex]=useState()
      const [postsPerPage,setPostperPage]=useState(12);
      const [pageCount,setPageCount] = useState()
      const avgrt = useRef()

      const getSomeMovies = async ()=>{
        //lastPostIndex = last index of the post which is shown on a specific page
      const lastPostIndex = currentPage*postsPerPage;
      //firstPostIndex = first index of the post which is shown on a specific page
      const firstPostIndex = lastPostIndex- postsPerPage;
      //
      try{
        let gres = await axios.get("http://localhost:8203/average-rating");
        avgrt.current = gres.data
      }catch(err){
        alert(err.response)
      }
        try{
          const res = await axios.get("http://localhost:8201/movie/list");
          setPageCount(Math.ceil(res.data.length/postsPerPage))
          setMovies(res.data.slice(firstPostIndex,lastPostIndex));
          window.scrollTo({top: 0})
        }catch(err){
          toast.warn(`No Movies found..`, {
            position: toast.POSITION.TOP_CENTER});
        }
  
    }
      let pages = [];
  for(let i = 1;i<=pageCount;i++){
    pages.push(i)
  }
  useEffect(()=>{
    getSomeMovies();
  },[currentPage])


    
  return (
    <>
    <div className='container'>
      <Swiper pagination={{clickable:true}} loop={true} modules={[Autoplay,Pagination,EffectCards,Keyboard]} autoplay={{
          delay: 3000,
          disableOnInteraction: false,
        }} effect={"cards"} keyboard={{
          enabled: true,
        }} className="mySwiper">
        <SwiperSlide><img src='/images/wolv.jpg' alt="" className='home-img' />
      <div className="home-text">
        <h1 className='home-title'>X-<span>Men</span> New : <br /> Mutants</h1>

        <p>Releasing Feb 2023</p>
        <Link className='watch-btn'>
        <BsPlay className='ic'/>
          Watch The Trailer
        </Link>
        <img src="/images/ticket.png" alt="" className='home-logo-tic'/>
       
      </div></SwiperSlide>
      <SwiperSlide><img src='/images/crrs.jpg' alt="" className='home-img' />
      <div className="home-text">
        <h1 className='home-title'>Dune<span> : 2</span></h1> <br />

        <p>Releasing in 2023</p>
        <Link className='watch-btn'>
        <BsPlay className='ic'/>
          Watch The Trailer
        </Link>
        <img src="/images/ticket.png" alt="" className='home-logo-tic'/>
       
      </div></SwiperSlide>
      <SwiperSlide><img src='/images/4408082.jpeg' alt="" className='home-img' />
      <div className="home-text">
        <h1 className='home-title'><span>Avatar</span> : 2</h1><br />

        <p>Releasing on 16 Dec 2022</p>
        <Link className='watch-btn'>
        <BsPlay className='ic'/>
          Watch The Trailer
        </Link>
        <img src="/images/ticket.png" alt="" className='home-logo-tic'/>
       
      </div></SwiperSlide>
      
      </Swiper>
  
      
    
    <div className='movie-container'>
        {
          movies.map((movie)=>{
            return(
              <>
              <MovieItem key={movie.id}{...movie}{...{rating:avgrt.current[movie.id]}}/>
              </>
            )
          })
        }
        </div>
        <div className='pagination'>

        <BsChevronLeft className='page-ic'/>
          {
            pages.map((num,index)=>{
              return(
              <>
                <button className={num === currentPage ?"page-btn-active":'page-btn'}onClick={()=>setCurrentPage(num)} key={index}>{num}</button>
              </>
              )
            }
          )}
          <BsChevronRight className='page-ic'/>
        </div>
        
        <ToastContainer/>
        </div>
    </>
  )
}

