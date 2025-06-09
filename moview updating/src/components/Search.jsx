import React from 'react'
import { useState, useEffect } from 'react'
// import { useNavigate } from "react-router-dom";
// import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom'
import axios from 'axios';
import Card from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Form';
import { useNavigate, useParams } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import { MovieItem } from './MovieItem';
import { ToastContainer, toast } from 'react-toastify';

export const Search = () => {
  const navigate = useNavigate();

  useEffect(() => {
    getSearchedMovies();
  }, [])

  let { search } = useParams();

  const [items, setItems] = useState([]);
  const [rate, setRate] = useState([]);
  const [loading, setLoading] = useState(false);

  const getSearchedMovies = async () => {
    try {
      const res = await axios.get(`http://localhost:8201/movie/search/${search}`);
      setItems(res.data);
    } catch (err) {
      toast.warning(err.response.data,{
        position: toast.POSITION.TOP_CENTER}); 
    }
  }
  return (
    <>
      <div className='ser-comp'>
    <h1 className='categories-heading'>Your Search Results </h1>
    <div className='search-items'>
        {
          items.map((item) => {
            return (
              <MovieItem key={item.id} {...item}/>

            )

          })
        }
        </div>
      </div>


    </>
  )
}
