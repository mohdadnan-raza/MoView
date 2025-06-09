import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import MovieService from "../../../services/MovieService";

export function AddMovie() {
  const navigate = useNavigate();

  let { id } = useParams();

  const [movieName, setMovieName] = useState("");
  const [movieDescription, setMovieDescription] = useState("");
  const [movieImage, setMovieImage] = useState("");
  const [movieGenres, setMovieGenres] = useState("");
  const [movieReleaseYear, setMovieReleaseYear] = useState("");
  const [movieLang, setMovieLang] = useState("");
  const [movieCast, setMovieCast] = useState("");
  const [movieRunTime, setMovieRunTime] = useState();
  const [movieDirector, setMovieDirector] = useState("");
  const [movie, setMovie] = useState([]);
  const [loading, setLoading] = useState(false);

  const saveMovie = (event) => {
    event.preventDefault();
    const movie = {
      movieName: movieName,
      movieDescription: movieDescription,
      movieImage: movieImage,
      movieGenres: movieGenres,
      movieReleaseYear: movieReleaseYear,
      movieLang: movieLang,
      movieCast: movieCast,
      movieRunTime: movieRunTime,
      movieDirector: movieDirector,
    };
    console.log("movie => " + JSON.stringify(movie));

    MovieService.createMovie(movie).then(() => {
      navigate(`/movie/list`);
    });

   };

  return (
    <div>
      <br style={{color:'#fff'}}></br>
      <div className="container d-flex align-items-center justify-content-center" style={{marginTop:'30px',height:"98vh"}}>
        <div className="row">
          <div className="text-center " style={{border:'2px solid #ffb43a',background:'transparent',color:'#fff',width:'80vw',height:'60vh',overflow:'scroll',borderRadius:'1rem'}}>
            <div className="text-center m-3">
              <h3>Add Movie</h3>
            </div>
            <hr style={{color:'#fff'}}/>
            <div className="text-center d-inline-block">
              <form className="d-flex flex-wrap justify-content-center">
                <div className="form-group">
                  <input
                    placeholder="Movie Name"
                    name="movieName"
                    value={movieName}
                    onChange={(e) => setMovieName(e.target.value)} style={{width:400}}
                    className='login-input m-3'

                  />
                </div>
                <div className="form-group">
                  <input
                    placeholder="Description"
                    name="movieDescription"
                    className="login-input m-3"
                    value={movieDescription}
                    onChange={(e) => setMovieDescription(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  <img src={movie.movieImage} alt=""/>
                  <input
                    placeholder="Movie Image"
                    name="movieImage"
                    className="login-input m-3"
                    value={movieImage}
                    onChange={(e) => setMovieImage(e.target.value)} style={{width:400}}
                    />
                </div>
                <div className="form-group">
                  <input
                    placeholder="Language"
                    name="movieLang"
                    className="login-input m-3"
                    value={movieLang}
                    onChange={(e) => setMovieLang(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  <input
                    placeholder="Cast"
                    name="movieCast"
                    className="login-input m-3"
                    value={movieCast}
                    onChange={(e) => setMovieCast(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  
                  <input
                    placeholder="Run Time"
                    name="movieRunTime"
                    className="login-input m-3"
                    value={movieRunTime}
                    type="number"
                    onChange={(e) => setMovieRunTime(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  
                  <input
                    placeholder="Director"
                    name="movieDirector"
                    className="login-input m-3"
                    value={movieDirector}
                    onChange={(e) => setMovieDirector(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                  
                  <input
                    placeholder="Release Year"
                    name="movieReleaseYear"
                    className="login-input m-3"
                    value={movieReleaseYear}
                    onChange={(e) => setMovieReleaseYear(e.target.value)} style={{width:400}}
                  />
                </div>
                <div className="form-group">
                 
                  <input
                    placeholder="Genres"
                    name="movieGenres"
                    className="login-input m-3"
                    value={movieGenres}
                    onChange={(e) => setMovieGenres(e.target.value)} style={{width:400}}
                  />
                </div>
              </form>
              <button className="btn btn-outline-success" onClick={saveMovie} style={{marginTop:'10px',marginRight:'10px'}}>
                  Save
                </button>
                <button
                  className="btn btn-outline-danger"
                  onClick={() => {
                    navigate(`/admin`);
                  }}
                  style={{ marginTop:'10px'}}
                >
                  Cancel
                </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}