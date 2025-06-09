import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';

export function UpdateMovie() {

  useEffect(()=>{
    getMovieById();
  },[])
  const navigate = useNavigate();
  const getMovieById = async ()=>{
    try{
      const movieDataOld = await axios.get(`http://localhost:8201/movie/id/${id}`);
      setGetMovie(movieDataOld.data)
    }catch(err){
      toast(err.response.data, {
        position: toast.POSITION.TOP_CENTER});  
    }
  }

  let { id } = useParams();

  const [getMovie,setGetMovie] = useState([]);
  const [movieName, setMovieName] = useState(getMovie.movieName);
  const [movieDescription, setMovieDescription] = useState();
  const [movieImage, setMovieImage] = useState();
  const [movieGenres, setMovieGenres] = useState();
  const [movieReleaseYear, setMovieReleaseYear] = useState();
  const [movieLang, setMovieLang] = useState();
  const [movieCast, setMovieCast] = useState();
  const [movieRunTime, setMovieRunTime] = useState();
  const [movieDirector, setMovieDirector] = useState();





    const update = async (e) => {
      e.preventDefault();
      try{
        const r = await axios.put(`http://localhost:8201/movie/update/id/${id}`,{
          movieName: movieName,
          movieDescription: movieDescription,
          movieImage: movieImage,
          movieGenres: movieGenres,
          movieReleaseYear: movieReleaseYear,
          movieLang: movieLang,
          movieCast: movieCast,
          movieRunTime: movieRunTime,
          movieDirector: movieDirector,
        })
        navigate("/movie/list");
      }catch(err){
        toast(err.response.data, {
          position: toast.POSITION.TOP_CENTER});  
      }
    }


  return (
    <div>
      <br style={{color:'#fff'}}></br>
      <div className="container d-flex justify-content-center align-items-center" style={{marginTop:'35px',minHeight:"98vh"}}>
        <div className="row">
          <div className="card text-center"style={{border:'2px solid #ffb43a',background:'transparent',color:'#fff'}}>
            <div className="text-center">
              <h3 className="pt-10">Update Movie</h3>
            </div>
            <hr style={{color:'#fff'}}/>
            <div className="card-body text-center">
              <form>
                <div className="form-group">
                  <textarea
                    placeholder="Movie Name"
                    name="movieName"
                    value={movieName}
                    onChange={(e) => setMovieName(e.target.value)} style={{width:400}}
                    className='login-input'

                  />
                </div>
                <div className="form-group">
                  <textarea
                    placeholder="Description"
                    name="movieDescription"
                    className="login-input"
                    value={movieDescription}
                    onChange={(e) => setMovieDescription(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  <img src={movieImage} alt=""/>
                  <textarea
                    placeholder="Movie Image"
                    name="movieImage"
                    className="login-input"
                    value={movieImage}
                    onChange={(e) => setMovieImage(e.target.value)} style={{width:400}}
                    />
                </div>
                <div className="form-group">
                  <textarea
                    placeholder="Language"
                    name="movieLang"
                    className="login-input"
                    value={movieLang}
                    onChange={(e) => setMovieLang(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  <textarea
                    placeholder="Cast"
                    name="movieCast"
                    className="login-input"
                    value={movieCast}
                    onChange={(e) => setMovieCast(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  
                  <textarea
                    placeholder="Run Time"
                    name="movieRunTime"
                    className="login-input"
                    value={movieRunTime}
                    type='number'
                    onChange={(e) => setMovieRunTime(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  
                  <textarea
                    placeholder="Director"
                    name="movieDirector"
                    className="login-input"
                    value={movieDirector}
                    onChange={(e) => setMovieDirector(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  
                  <textarea
                    placeholder="Release Year"
                    name="movieReleaseYear"
                    className="login-input"
                    value={movieReleaseYear}
                    onChange={(e) => setMovieReleaseYear(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                 
                  <textarea
                    placeholder="Genres"
                    name="movieGenres"
                    className="login-input"
                    value={movieGenres}
                    onChange={(e) => setMovieGenres(e.target.value)} style={{width:400}}
                  />
                </div>

                <button className="btn btn-outline-success" onClick={update} style={{marginTop:'10px'}}>
                  Save
                </button>
                <button
                  className="btn btn-outline-danger"
                  onClick={() => {
                    navigate(`/admin`);
                  }}
                  style={{ marginLeft: "10px", marginTop:"10px"}}
                >
                  Cancel
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}