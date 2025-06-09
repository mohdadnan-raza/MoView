import Axios from "axios"

export const addReviews=()=>{
    return Axios.post('http://localhost:8203/add/review')
};
export const addRating=()=>{
    return Axios.post('http://localhost:8203/add/rating')
};
export const averageRating=()=>{
    return Axios.get(`http://localhost:8203/average-rating/movie-id/{movie-id}`)
};
// export const getReviews=()=>{
//     return Axios.get('http://localhost:8203/review/list')
// };
// export const getReviewByMovieId=()=>{
//     return Axios.get(`http://localhost:8203/review/movie-id/{id}`)
// };
// export const updateReview=()=>{
//     return Axios.put(`http://localhost:8203/update/review/id/{id}`)
// };
// export const deleteReview=()=>{
//     return Axios.delete(`http://localhost:8203/delete/review/id/{id}`)
// };


// export const getMovie=()=>{
//     return Axios.get('http://localhost:8201/movie/list')
// };
// export const getMovieById=()=>{
//     return Axios.get(`http://localhost:8201/movie/id/{id}`)
// };
// export const addMovie=()=>{
//     return Axios.post('http://localhost:8201/movie/add')
// };
// export const updateMovie=()=>{
//     return Axios.put(`http://localhost:8201/movie/update/id/{id}`)
// };
// export const deleteMovie=()=>{
//     return Axios.delete(`http://localhost:8201/movie/delete/id/{id}`)
// };



// export const getUser=()=>{
//     return Axios.get('http://localhost:8202/user/list')
// };
// export const getUserById=()=>{
//     return Axios.get(`http://localhost:8202/user/id/{id}`)
// };
// export const updateUser=()=>{
//     return Axios.put(`http://localhost:8202/update/user/id/{id}`)
// };
// export const deleteUser=()=>{
//     return Axios.delete(`http://localhost:8202/delete/user/id/{id}`)
// };


export const movieByGenre=()=>{
    return Axios.get(`http://localhost:8201/movie/genres/{genres}`)
}
export const movieByCast=()=>{
    return Axios.get(`http://localhost:8201/movie/cast/{cast}`)
}
export const movieByLang=()=>{
    return Axios.get(`http://localhost:8201/movie/lang/{lang}`)
}
export const movieByDirector=()=>{
    return Axios.get(`http://localhost:8201/movie/director/{director}`)
}


export const movieBySearch=()=>{
    return Axios.get(`http://localhost:8201/movie/search/{search}`)
}

