import React, { useState , useEffect } from 'react'
import axios from 'axios'
import { useNavigate, useParams } from 'react-router-dom';
import ReviewService from '../../../services/ReviewService'

export function ListReview() {
    const navigate = useNavigate();


    const [reviews, setReviews] = useState([])
    const id = reviews.id;
   

    function deleteReview(id){
        ReviewService.deleteReview(id).then( res => {
            setReviews({reviews: reviews.filter(review => review.id !== id)});
        });
    }

    const listReviewData = async () => {
        const res = await axios.get("http://localhost:8203/review/list");
        setReviews(res.data);
        console.log(res.data)
    };
    
    useEffect(() => {
      listReviewData();
    }, [])
    

   
    return (
        <div>
              <h2 className="text-center py-10" style={{marginTop:'50px',color:'#ffb43a'}}>Reviews List</h2>
             <br></br>
             <div className = "row">
                    <table className = "table table-bordered text-center" style={{color:'#ffff'}}>
                        <thead className='text-center'>

                            <tr style={{color:'#ffb43a'}}>
                                <th> Movie Id</th>
                                <th> User Name</th>
                                <th> Review Comment</th>
                                <th> Rating</th>
                                <th> Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                reviews.map(
                                    review => 
                                    <tr key = {review.id}>
                                         <td> { review.username} </td>  
                                         <td> { review.movieId} </td>  
                                         <td> { review.rating} </td> 
                                         <td> { review.comment} </td>  
                                         <td>
                                             <button style={{margin: "10px"}} onClick={ () => navigate(`/update/review/id/${id}`)} className="btn btn-outline-warning">Update</button>
                                             <button style={{margin: "10px"}} onClick={ () => deleteReview(review.id)} className="btn btn-outline-danger">Delete</button>
                                         </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
             </div>
        </div>
    )
}