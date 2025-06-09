import axios from 'axios';

const review_url = "http://localhost:8203";

class ReviewService {

    getReviews(){
        return axios.get(review_url + '/review/list');
    }
    
    getReviewById(id){
        return axios.get(review_url + '/review/id/' + id);
    }

    getReviewByMovieId(movieId){
        return axios.get(review_url + '/review/movie-id/' + movieId);
    }

    updateReview(review, id){
        return axios.put(review_url + '/update/review/id/' + id, review);
    }

    deleteReview(id){
        return axios.delete(review_url + '/review/delete/id/' +id);
    }
}

export default new ReviewService()