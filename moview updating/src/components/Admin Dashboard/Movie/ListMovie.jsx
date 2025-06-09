import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import MovieService from "../../../services/MovieService";

export function ListMovie() {
  const navigate = useNavigate();

  const { id } = useParams();
  const [movies, setMovies] = useState([]);

  function deleteMovie(id) {
    MovieService.deleteMovie(id).then(() => {
      setMovies((movies) => movies.filter((user) => user.id !== id));
    });
  }

  useEffect(() => {
    
    listData();
  }, []);

  
  function listData(){
    MovieService.getMovies(id).then((res) => {
    setMovies(res.data);
  });
}


  return (
    <div>
      <h2 className="text-center py-10" style={{marginTop:'50px',color:'#ffb43a'}}>Movies List</h2>
      <br></br>
      <div className="row">
        <table className="table table-bordered" style={{color:'whitesmoke'}}>
          <thead>
            <tr className="text-center">
              {/* <th> Movie Id</th> */}
              <th> Name</th>
              {/* <th> Image</th> */}
              <th> Genre</th>
              <th> Release Year</th>
              <th> Language</th>
              <th> Actions</th>
            </tr>
          </thead>
          <tbody>
            {movies.map((movie) => (
              <tr key={movie.id}className="text-center">
                <td > {movie.movieName} </td>
                {/* <td> {movie.movieImage} </td> */}
                <td> {movie.movieGenres} </td>
                <td> {movie.movieReleaseYear} </td>
                <td> {movie.movieLang} </td>
                <td>
                  <button
                    onClick={() => {
                      navigate(`/update/movie/${movie.id}`);
                    }}
                    className="btn btn-outline-warning"
                  >
                    Update
                  </button>
                  <button
                    style={{ marginLeft: "10px" }}
                    onClick={() => deleteMovie(movie.id)}
                    // onClick={deleteData}
                    className="btn btn-outline-danger"
                  >
                    Delete
                  </button>
                  <button
                    style={{ marginLeft: "10px" }}
                    onClick={() =>
                      navigate(`view/movie/${movie.id}`)
                    }
                    className="btn btn-outline-success"
                  >
                    View
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
