import axios from 'axios';

const movie_url = "http://localhost:8201";

class MovieService {

    getMovies(){
        return axios.get(movie_url + '/movie/list');
    }

    createMovie(movie){
        return axios.post(movie_url+'/movie/add', movie);
    }

    getMovieById(id){
        return axios.get(movie_url + '/movie/id/' + id);
    }

    updateMovieById(movie, id){
        return axios.put(movie_url + '/movie/update/id/' + id, movie);
    }

    deleteMovie(id){
        return axios.delete(movie_url + '/movie/delete/id/' + id);
    }
}

export default new MovieService()