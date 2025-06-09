import React from 'react'
import { useLocation, useParams } from 'react-router-dom'
import { ToastContainer, toast } from 'react-toastify';
import ReactStars from 'react-rating-stars-component'
import { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate,Link } from "react-router-dom";
import { BsPlay,BsDot,BsStar,BsChevronDoubleRight, BsWindowDock} from "react-icons/bs";
import jwt_decode from "jwt-decode";

export const ViewReview = () => {
  const navigate = useNavigate();
  useEffect(()=>{
    viewReview(); 
    viewMovie();
  },[])

  const [star,setStar]=useState();
  const [movie,setMovie]=useState([]);
  const [review,setReview]=useState([]);
  const [add,setAdd] = useState();
    const {id} = useParams();
    // const user_name;
   const user=JSON.parse(localStorage.getItem('user'))
   if(user){
    var token = user.jwtToken;
      var decoded = jwt_decode(token);
   }

    const ratingChanged =(newRating)=>{
      setStar(newRating)
      localStorage.getItem('user')?(
      toast.dark(`You Have Given This Movie ${newRating} Stars.`, {
        position: toast.POSITION.TOP_CENTER}) 
      ):warn()
    }



    const viewReview = async ()=>{
      try{
        const res = await axios.get(`http://localhost:8203/review/movie-id/${id}`);
        setReview(res.data);
      }catch(err){
        toast(err.response, {
          position: toast.POSITION.TOP_CENTER});  
      }
    }
    const viewMovie = async ()=>{
      try{
        const res = await axios.get(`http://localhost:8201/movie/id/${id}`);
        setMovie(res.data);
      }catch(err){
        toast(err.response.data, {
          position: toast.POSITION.TOP_CENTER});  
      }
    }
    const submitReview = async ()=>{
      try{
        const res = await axios.post(`http://localhost:8203/review/add`,{

          username: decoded.sub,
  
          movieId: id,
  
          rating: star,
  
          comment: add
  
      });
      toast(res.data, {
        position: toast.POSITION.TOP_CENTER}); 
      }catch(err){
        alert(err.nessage)
      }
    }
    function warn() {
      navigate('/login')
      toast.warning('You Have To Login First..', {
              position: toast.POSITION.TOP_CENTER})
    }  
   const handleSubmit=()=>{
      localStorage.getItem('user')?submitReview():warn()
    }
    const handleStar=()=>{
      localStorage.getItem('user')?ratingChanged():warn()
    }

  return (
    <div className='reviews-page'>
      <div className='reviews-left'>
    <div className="custom-card rc">
      <img src={movie.movieImage} className="movie-box-img" alt="..."/>
      <div className='card-text'>
        <h2 className='movie-title'>{movie.movieName}</h2>
        <div>
        <span className='movie-type'>{movie.movieCast?movie.movieCast.split(",")[0]:null}</span>
        <BsDot/>
        <span className='movie-type'>{movie.movieGenres?movie.movieGenres.split(",")[0]:null}</span>
        <BsDot/>
        <span className='movie-type'>{movie.movieReleaseYear}</span>
        </div>
        <div className='buttons'>
        <button className='sub-btn' onClick={handleSubmit}>Save</button>
        <div className='rating-stars'>
        <ReactStars activeColor="#ffb43a" isHalf={true} size={25} onChange={ratingChanged} count={5}/>
        </div>
        </div>
      </div>
      </div>
      <div className="reviews-input">
        <textarea type="text" className='cs-inp' onChange={(e)=>setAdd(e.target.value)} placeholder='Add Your Comments!' value={add}/>

      </div>
    </div>
    <div className="reviews-right">
        {
        review.map((item,index)=>{
            return(
              <>
              <div className='review-item' key={index}>
              <div>
              <img src="/images/avatar.svg" alt="" className='av-img'/>
              </div>
              <div className='rev-title'>
              <div>
            <ReactStars activeColor="#ffb43a" isHalf={true} size={10} value={item.rating} edit={false}/>
            </div>
            <div>
             {item.username}
             </div>
             </div>
             <div className='rv-ic'>
             <BsChevronDoubleRight color='#ffb43a'/>
             </div>
             <div className='rev-text'>
              {item.comment}
             </div>
             </div>
             </>
          )}
        )}
        </div>
      
      <ToastContainer/>
    
    </div>

    
    
  )
}





